package agh.ics.oop.Maps;

import agh.ics.oop.Animal.Animal;
import agh.ics.oop.MapElements.Plant;
import agh.ics.oop.Plants.Equator;
import agh.ics.oop.Plants.PlantGenerator;
import agh.ics.oop.Plants.PlantGeneratorsList;
import agh.ics.oop.Utility.Directions;
import agh.ics.oop.Utility.Vector2D;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.HashMap;
import java.util.Map;


public abstract class WorldMap {
    protected final int width;
    protected final int height;
    private final PlantGenerator plantGenerator;

    private final Map<Vector2D, Plant> plants = new HashMap<>();

    private final Map<Vector2D, Animal> animals = new HashMap<>();

    public WorldMap(int width, int height, PlantGeneratorsList plantGenerator){
        this.width = width;
        this.height = height;
        this.plantGenerator = switch (plantGenerator) {
            case EQUATOR -> new Equator(this, 5 );
            case TOXIC -> null;
        };
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
        return this.animals.containsKey(position);
    }

    public void generatePlant(){
        Vector2D position = this.plantGenerator.generatePlant();
        this.plants.put(position, new Plant(position));
    }

    protected abstract Vector2D calculateNewPosition(Vector2D position, Directions direction);

    public void addAnimal(Animal animal){
        animals.put(animal.getPosition(), animal);
    }

    public Vector2D moveAnimal(Vector2D position, Directions direction){
        Vector2D newPosition = this.calculateNewPosition(position, direction);
        Animal animal = animals.get(position);
        animals.remove(position);
        animals.put(newPosition, animal);

        return newPosition;
    }
}
