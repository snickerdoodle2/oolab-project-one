package agh.ics.oop.Maps;

import agh.ics.oop.Animal.Animal;
import agh.ics.oop.Animal.AnimalTypesList;
import agh.ics.oop.Animal.ObedientAnimal;
import agh.ics.oop.MapElements.Plant;
import agh.ics.oop.Plants.EquatorPlant;
import agh.ics.oop.Plants.PlantGenerator;
import agh.ics.oop.Utility.Directions;
import agh.ics.oop.Utility.Options;
import agh.ics.oop.Utility.Vector2D;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.*;


public abstract class WorldMap {
    protected final int width;
    protected final int height;
    private final PlantGenerator plantGenerator;
    private final int energyPerPlant;

    private final AnimalTypesList animalType;
    private final Options options;

    private final Map<Vector2D, Plant> plants = new HashMap<>();

    private final Map<Vector2D, TreeSet<Animal>> animals = new HashMap<>();


    private int animalsAlive = 0;
    private int day = 0;


    public WorldMap(Options options){
        this.options = options;
        this.width = options.mapWidth;
        this.height = options.mapHeight;

        this.animalType = options.animalType;
        this.plantGenerator = switch (options.plantType) {
            case EQUATOR -> new EquatorPlant(this, 5 );
            case TOXIC -> null;
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
        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: " + this.plantGenerator.getCellBgColor(new Vector2D(x, y)));
                if (plantAt(new Vector2D(x, y))){
                    pane.setStyle("-fx-background-color: red");
                }
                if (animalAt(new Vector2D(x, y))){
                    pane.setStyle("-fx-background-color: blue");
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

        for (int y = 0; y <= this.height; y++){
            grid.getRowConstraints().add(new RowConstraints(heightPixelSize));
        }
        return grid;
    }

    public boolean plantAt(Vector2D position) {
        return this.plants.containsKey(position);
    }
    private boolean animalAt(Vector2D position) {
        TreeSet<Animal> tmp = this.animals.get(position);
        if (tmp == null ) return false;
        return tmp.size() > 0;
    }

    private void generatePlant(){
        Vector2D position = this.plantGenerator.generatePlant();
        this.plants.put(position, new Plant(position));
    }

    protected abstract Vector2D calculateNewPosition(Vector2D position, Directions direction);

    private void newAnimal(){
        Animal animal = switch (animalType) {
            case OBIDIENT -> new ObedientAnimal(options, this);
            case CRAZY -> null;
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
        for (int i = 0; i < options.initialAnimals; i++ ){
            newAnimal();
        }
    }

    public void moveAnimal(Animal animal, Directions direction){
        Vector2D newPosition = this.calculateNewPosition(animal.getPosition(), direction);
        TreeSet<Animal> animalsList = animals.get(animal.getPosition());
        animalsList.remove(animal);
        animal.setPosition(newPosition);
        addAnimal(animal);
    }

    private void removeDead() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            for (Animal animal : new ArrayList<>(animalList)){
                if (animal.isDead()) {
                    TreeSet<Animal> animalsList = animals.get(animal.getPosition());
                    animalsList.remove(animal);
                    this.animalsAlive--;
                }
            }
        }
    }

    private void commendMove() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            for (Animal animal : new ArrayList<>(animalList)){
                animal.move();
            }
        }
    }

    private void commendEat() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            if (animalList.size() > 0){
                Animal animal = animalList.last();
                if (plantAt(animal.getPosition())) {
                    animal.eat(energyPerPlant);
                    plants.remove(animal.getPosition());
                }
            }
        }
    }

    private void commendBreed() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            if (animalList.size() >= 2) {
                Animal parent1 = animalList.pollLast();
                Animal parent2 = animalList.pollLast();

                if (parent1.getEnergy() >= options.minToBreed && parent2.getEnergy() >= options.minToBreed){
                    Animal kid = switch (animalType) {
                        case OBIDIENT -> new ObedientAnimal(options, this, parent1, parent2);
                        case CRAZY -> null;
                    };
                    addAnimal(kid);
                    this.animalsAlive++;
                }

                parent1.breed(options.energyToBreed);
                parent2.breed(options.energyToBreed);

                animalList.add(parent1);
                animalList.add(parent2);
            }
        }
    }

    private void decrementEnergy() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            for (Animal animal : new ArrayList<>(animalList)){
                animal.decrementEnergy();
            }
        }
    }

    public boolean simulateDay() {
        removeDead();
        commendMove();
        commendEat();
        commendBreed();
        generatePlant();
        decrementEnergy();
        this.day++;
        return this.animalsAlive == 0;
    }
}
