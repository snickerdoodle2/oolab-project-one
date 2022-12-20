package agh.ics.oop.Maps;

import agh.ics.oop.MapElements.Plant;
import agh.ics.oop.Plants.Equator;
import agh.ics.oop.Plants.PlantGenerator;
import agh.ics.oop.Plants.PlantGeneratorsList;
import agh.ics.oop.Utility.Vector2D;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.HashMap;
import java.util.Map;


public abstract class WorldMap {
    private int width;
    private int height;
    private PlantGenerator plantGenerator;

    private Map<Vector2D, Plant> plants = new HashMap<>();

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

    public void generatePlant(){
        Vector2D position = this.plantGenerator.generatePlant();
        this.plants.put(position, new Plant(position));
    }
}
