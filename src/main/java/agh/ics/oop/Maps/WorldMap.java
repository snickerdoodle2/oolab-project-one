package agh.ics.oop.Maps;

import agh.ics.oop.Plants.Equator;
import agh.ics.oop.Plants.PlantGenerator;
import agh.ics.oop.Plants.PlantGeneratorsList;

public abstract class WorldMap {
    private int width;
    private int height;
    private PlantGenerator plantGenerator;

    public WorldMap(int width, int height, PlantGeneratorsList plantGenerator){
        this.width = width;
        this.height = height;
        this.plantGenerator = switch (plantGenerator) {
            case EQUATOR -> new Equator(this, 2);
            case TOXIC -> null;
        };
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
