package agh.ics.oop.Maps;

import agh.ics.oop.Animal.Animal;
import agh.ics.oop.Animal.AnimalTypesList;
import agh.ics.oop.Animal.Obedient;
import agh.ics.oop.MapElements.Plant;
import agh.ics.oop.Plants.Equator;
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


    public WorldMap(Options options){
        this.options = options;
        this.width = options.mapWidth;
        this.height = options.mapHeight;

        this.animalType = options.animalType;
        this.plantGenerator = switch (options.plantType) {
            case EQUATOR -> new Equator(this, 5 );
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
    public boolean animalAt(Vector2D position) {
        TreeSet<Animal> tmp = this.animals.get(position);
        if (tmp == null ) return false;
        return tmp.size() > 0;
    }

    public void generatePlant(){
        Vector2D position = this.plantGenerator.generatePlant();
        this.plants.put(position, new Plant(position));
    }

    protected abstract Vector2D calculateNewPosition(Vector2D position, Directions direction);

    public void newAnimal(){
        Animal animal = switch (animalType) {
            case OBIDIENT -> new Obedient(options, this);
            case CRAZY -> null;
        };
        addAnimal(animal);
    }

    private void addAnimal(Animal animal) {
        TreeSet<Animal> animalsList = animals.get(animal.getPosition());
        if (animalsList == null) {
            TreeSet<Animal> newAnimalsList = new TreeSet<Animal>(new Comparator<Animal>() {
                @Override
                public int compare(Animal o1, Animal o2) {
                    int energy1 = o1.getEnergy();
                    int energy2 = o2.getEnergy();
                    return Integer.compare(energy1, energy2);
                }
            });

            newAnimalsList.add(animal);
            animals.put(animal.getPosition(), newAnimalsList);
        } else {
            animalsList.add(animal);
        }
    }

    public void moveAnimal(Animal animal, Directions direction){
        Vector2D newPosition = this.calculateNewPosition(animal.getPosition(), direction);
        TreeSet<Animal> animalsList = animals.get(animal.getPosition());
        animalsList.remove(animal);
        animal.setPosition(newPosition);
        addAnimal(animal);
    }

    public void removeDead() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            for (Animal animal : animalList){
                if (animal.isDead()) {
                    TreeSet<Animal> animalsList = animals.get(animal.getPosition());
                    animalsList.remove(animal);
                }
            }
        }
    }

    public void commendMove() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            for (Animal animal : animalList){
                animal.move();
            }
        }
    }

    public void commendEat() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            if (animalList.size() > 0){
                Animal animal = animalList.first();
                if (plantAt(animal.getPosition())) {
                    System.out.println(animal.getEnergy());
                    animal.eat(energyPerPlant);
                    System.out.println(animal.getEnergy());
                    plants.remove(animal.getPosition());
                }
            }
        }
    }

    public void decrementEnergy() {
        for (TreeSet<Animal> animalList: new ArrayList<>(animals.values())) {
            for (Animal animal : animalList){
                animal.decrementEnergy();
            }
        }
    }

    public void simulateDay() {
        removeDead();
        commendMove();
        commendEat();

        generatePlant();
        decrementEnergy();
    }
}
