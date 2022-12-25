package agh.ics.oop.Maps;

import agh.ics.oop.Animal.Animal;
import agh.ics.oop.Animal.AnimalTypesList;
import agh.ics.oop.Animal.CrazyAnimal;
import agh.ics.oop.Animal.ObedientAnimal;
import agh.ics.oop.MapElements.MapElement;
import agh.ics.oop.MapElements.Plant;
import agh.ics.oop.Plants.EquatorPlant;
import agh.ics.oop.Plants.PlantGenerator;
import agh.ics.oop.Plants.ToxicPlant;
import agh.ics.oop.Utility.Directions;
import agh.ics.oop.Utility.Options;
import agh.ics.oop.Utility.Vector2D;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public abstract class WorldMap {
    protected final int width;
    protected final int height;
    private final PlantGenerator plantGenerator;
    private final int energyPerPlant;


    private final AnimalTypesList animalType;
    private final Options options;

    private final Map<Vector2D, Plant> plants = new HashMap<>();

    private final Map<Vector2D, TreeSet<Animal>> animals = new HashMap<>();

    private final Map<Vector2D, Integer> deadAnimalsMap = new HashMap<>();


    private int animalsAlive = 0;

    private int numberOfPlants = 0;
    private int day = 0;

    private List<Integer> deadAnimalsAge = new ArrayList<>();

    private List<Integer> aliveAnimalsEnergy = new ArrayList<>();

    private List<String> genes = new ArrayList<>();

    private MapElement getObject(Vector2D position) {
        MapElement animal = null;
        if (animalAt(position)) {
            animal = animals.get(position).last();
        }
        if (animal != null) return animal;
        return plants.get(position);
    }


    public WorldMap(Options options) {
        this.options = options;
        this.width = options.mapWidth;
        this.height = options.mapHeight;

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                deadAnimalsMap.put(new Vector2D(i, j), 0);
            }
        }

        this.animalType = options.animalType;
        this.plantGenerator = switch (options.plantType) {
            case EQUATOR -> new EquatorPlant(this, options.mapHeight / 10);
            case TOXIC -> new ToxicPlant(this);
        };
        this.energyPerPlant = options.energyPerPlant;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GridPane toGridPane(int mapSize) {

        GridPane grid = new GridPane();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Pane pane = new Pane();
                MapElement mapElement = getObject(new Vector2D(x, y));
                if (mapElement == null) {
                    pane.setStyle("-fx-background-color: " + this.plantGenerator.getCellBgColor(new Vector2D(x, y)));
                } else {
                    pane.setStyle("-fx-background-color: " + mapElement.getColor());
                }


                GridPane.setHalignment(pane, HPos.CENTER);
                grid.add(pane, x, y, 1, 1);
            }
        }

        int widthPixelSize = mapSize / (this.width + 1);
        int heightPixelSize = mapSize / (this.height + 1);

        for (int x = 0; x < this.width; x++) {
            grid.getColumnConstraints().add(new ColumnConstraints(widthPixelSize));
        }

        for (int y = 0; y < this.height; y++) {
            grid.getRowConstraints().add(new RowConstraints(heightPixelSize));
        }

        grid.setStyle("-fx-border-width: 3;" +
                "-fx-border-color: " + switch (this.options.mapType) {
            case EARTH -> "#82AAE3";
            case PORTALS -> "#F07DEA";
        });
        return grid;
    }

    public boolean plantAt(Vector2D position) {
        return this.plants.containsKey(position);
    }

    private boolean animalAt(Vector2D position) {
        TreeSet<Animal> tmp = this.animals.get(position);
        if (tmp == null) return false;
        return tmp.size() > 0;
    }

    private void generatePlant() {
        Vector2D position = this.plantGenerator.generatePlant();
        if (position == null) return;
        this.plants.put(position, new Plant(position));
        numberOfPlants++;
    }

    protected abstract Vector2D calculateNewPosition(Vector2D position, Directions direction);

    private void newAnimal() {
        Animal animal = switch (animalType) {
            case OBEDIENT -> new ObedientAnimal(options, this);
            case CRAZY -> new CrazyAnimal(options, this);
        };
        addAnimal(animal);
        this.animalsAlive++;
    }

    private void addAnimal(Animal animal) {
        TreeSet<Animal> animalsList = animals.get(animal.getPosition());
        if (animalsList == null) {
            TreeSet<Animal> newAnimalsList = new TreeSet<Animal>(new Comparator<Animal>() {
                @Override
                public int compare(Animal o1, Animal o2) {
                    int energy1 = o1.getEnergy();
                    int energy2 = o2.getEnergy();
                    int tmp = Integer.compare(energy1, energy2);
                    return tmp != 0 ? tmp : (o1.equals(o2) ? 0 : 1);
                }
            });

            newAnimalsList.add(animal);
            animals.put(animal.getPosition(), newAnimalsList);
        } else {
            animalsList.add(animal);
        }


    }

    public void init() {
        for (int i = 0; i < options.initialAnimals; i++) {
            newAnimal();
        }
        for (int i = 0; i < options.initialPlants; i++) {
            generatePlant();
        }
    }

    public void moveAnimal(Animal animal, Directions direction) {
        Vector2D newPosition = this.calculateNewPosition(animal.getPosition(), direction);
        TreeSet<Animal> animalsList = animals.get(animal.getPosition());
        animalsList.remove(animal);
        animal.setPosition(newPosition);
        addAnimal(animal);
    }

    private void removeDead() {
        for (TreeSet<Animal> animalList : new ArrayList<>(animals.values())) {
            for (Animal animal : new ArrayList<>(animalList)) {
                if (animal.isDead()) {
                    TreeSet<Animal> animalsList = animals.get(animal.getPosition());
                    animalsList.remove(animal);
                    Integer field = deadAnimalsMap.getOrDefault(animal.getPosition(), 0);
                    deadAnimalsAge.add(animal.getAge());
                    deadAnimalsMap.put(animal.getPosition(), field.intValue() + 1);
                    this.animalsAlive--;
                }
            }
        }
    }

    private void commendMove() {
        aliveAnimalsEnergy.clear();
        genes.clear();
        for (TreeSet<Animal> animalList : new ArrayList<>(animals.values())) {
            for (Animal animal : new ArrayList<>(animalList)) {
                aliveAnimalsEnergy.add(animal.getEnergy());
                genes.add(animal.getGenes().toString());
                animal.move();
            }
        }
    }

    private void commendEat() {
        for (TreeSet<Animal> animalList : new ArrayList<>(animals.values())) {
            if (animalList.size() > 0) {
                Animal animal = animalList.last();
                if (plantAt(animal.getPosition())) {
                    numberOfPlants--;
                    animal.eat(energyPerPlant);
                    plants.remove(animal.getPosition());
                }
            }
        }
    }

    private void commendBreed() {
        for (TreeSet<Animal> animalList : new ArrayList<>(animals.values())) {
            if (animalList.size() >= 2) {
                Animal parent1 = animalList.pollLast();
                Animal parent2 = animalList.pollLast();

                if (parent1.getEnergy() >= options.minToBreed && parent2.getEnergy() >= options.minToBreed) {
                    Animal kid = switch (animalType) {
                        case OBEDIENT -> new ObedientAnimal(options, this, parent1, parent2);
                        case CRAZY -> new CrazyAnimal(options, this, parent1, parent2);
                    };
                    parent1.breed(options.energyToBreed);
                    parent2.breed(options.energyToBreed);

                    addAnimal(kid);
                    this.animalsAlive++;
                }

                animalList.add(parent1);
                animalList.add(parent2);
            }
        }
    }

    private void decrementEnergy() {
        for (TreeSet<Animal> animalList : new ArrayList<>(animals.values())) {
            for (Animal animal : new ArrayList<>(animalList)) {
                animal.decrementEnergy();
            }
        }
    }

    private void incrementAge() {
        for (TreeSet<Animal> animalList : new ArrayList<>(animals.values())) {
            for (Animal animal : new ArrayList<>(animalList)) {
                animal.incrementAge();
            }
        }
    }

    public boolean simulateDay() {
        removeDead();
        commendMove();
        commendEat();
        commendBreed();
        for (int i = 0; i < options.plantsPerDay; i++) {
            generatePlant();
        }
        incrementAge();
        decrementEnergy();
        this.day++;
        return this.animalsAlive == 0;
    }

    public Map<Vector2D, Integer> getDeadAnimalsMap() {
        return deadAnimalsMap;
    }

    public int getAnimalsAlive() {
        return animalsAlive;
    }

    public int getNumberOfPlants() {
        return numberOfPlants;
    }

    public int getDay() {
        return day;
    }

    public double getAverageAge() {
        return Math.round(deadAnimalsAge.stream().mapToDouble(e -> e).average().orElse(0)*100) / 100.0;
    }

    public double getAverageEnergy() {
        return Math.round(aliveAnimalsEnergy.stream().mapToDouble(e -> e).average().orElse(0) * 100) / 100.0;
    }

    public int getEmptyCells() {
        int sum = 0;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (!animalAt(new Vector2D(x, y))) sum++;
            }
        }
        return sum;
    }

    public String getMostCommonGene(){
        Optional<Map.Entry<String, Long>> mostCommonOpt = genes.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        if (mostCommonOpt.isPresent()) {
            Map.Entry<String, Long> mostCommon = mostCommonOpt.get();

            return mostCommon.getKey() + " x " + mostCommon.getValue();
        }
        return "O-O";
    }
}
