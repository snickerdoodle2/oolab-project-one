package agh.ics.oop.Plants;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.utility.Vector2D;

public abstract class PlantGenerator {
    private WorldMap map;

    public PlantGenerator(WorldMap map) {
        this.map = map;
    }

    /**
     * Calculate position of new plant, depending on type of plant generation scheme. (Equator, Toxic)
     *
     *
     *
     * @return Position for new plant.
     */
    public abstract Vector2D generatePlant();
}
