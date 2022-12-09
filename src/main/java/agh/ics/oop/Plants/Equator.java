package agh.ics.oop.Plants;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.utility.Vector2D;

public class Equator extends PlantGenerator {

    private Vector2D equatorLL;
    private Vector2D equatorUR;
    public Equator(WorldMap map, int equatorSize) {
        super(map);
        int mapHeight = map.getHeight();
        int mapWidth = map.getWidth();
        switch (mapHeight % 2){
            case 0: // EVEN
                if (equatorSize % 2 == 1) equatorSize--;
                this.equatorUR = new Vector2D(mapWidth-1, mapHeight/2 + equatorSize/2 - 1);
                break;
            case 1: // ODD
                if (equatorSize % 2 == 0) equatorSize--;
                this.equatorUR = new Vector2D(mapWidth-1, mapHeight/2 + equatorSize/2 + 1);
                break;
            default: break;
        };
        this.equatorLL = new Vector2D(0, mapHeight/2 - equatorSize/2);
    }

//    TODO: Implement plant generation algorithm.
    @Override
    public Vector2D generatePlant() {
        return null;
    }

    /**
     * Temporary method, to check if equator is calculated correctly.
     */
    public boolean isInEquator(Vector2D position) {
        return this.equatorLL.follows(position) && this.equatorUR.precedes(position);
    }
}
