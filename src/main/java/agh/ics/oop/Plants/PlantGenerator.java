package agh.ics.oop.Plants;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.utility.Vector2D;

import java.util.Random;

public abstract class PlantGenerator {
    protected WorldMap map;

    protected static final Random random = new Random();
    public PlantGenerator(WorldMap map) {
        this.map = map;
    }

    protected abstract Vector2D generatePreferred();
    protected abstract Vector2D generateDisPreferred();


    /**
     * Calculate position of new plant, depending on type of plant generation scheme. (Equator, Toxic)
     *
     *
     *
     * @return Position for new plant.
     */
    public Vector2D generatePlant() {
        if (random.nextDouble() < .8) { //INSIDE PREFERRED
            return this.generatePreferred();
        }
        else { //OUTSIDE PREFERRED
            return this.generateDisPreferred();
        }
    }

    public abstract String getCellBgColor(Vector2D position);
}
