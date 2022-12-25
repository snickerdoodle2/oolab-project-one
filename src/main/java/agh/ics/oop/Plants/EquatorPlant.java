package agh.ics.oop.Plants;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Utility.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class EquatorPlant extends PlantGenerator {

    private Vector2D equatorLL;
    private Vector2D equatorUR;
    public EquatorPlant(WorldMap map, int equatorSize) {
        super(map);
        int mapHeight = map.getHeight();
        int mapWidth = map.getWidth();
        switch (mapHeight % 2){
            case 0: // EVEN
                if (equatorSize % 2 == 1) equatorSize++;
                this.equatorUR = new Vector2D(mapWidth-1, mapHeight/2 + equatorSize/2 - 1);
                break;
            case 1: // ODD
                if (equatorSize % 2 == 0) equatorSize++;
                this.equatorUR = new Vector2D(mapWidth-1, mapHeight/2 + equatorSize/2 + 1);
                break;
            default: break;
        };
        this.equatorLL = new Vector2D(0, mapHeight/2 - equatorSize/2);
    }


    @Override
    protected Vector2D generatePreferred() {
//            TODO: Make plant generator inside equator better :D
        List<Vector2D> availableCells = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Vector2D tmp = new Vector2D(x, y);
                if (!map.plantAt(tmp) && isInEquator(tmp)) {
                    availableCells.add(tmp);
                }
            }
        }
        if (availableCells.size() == 0) {
            return null;
        }
        return availableCells.get(random.nextInt(availableCells.size()));
    }

    @Override
    protected Vector2D generateDisPreferred() {
        List<Vector2D> availableCells = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Vector2D tmp = new Vector2D(x, y);
                if (!map.plantAt(tmp) && !isInEquator(tmp)) {
                    availableCells.add(tmp);
                }
            }
        }
        if (availableCells.size() == 0) {
            return null;
        }
        return availableCells.get(random.nextInt(availableCells.size()));
    }

    @Override
    public String getCellBgColor(Vector2D position) {
        if (isInEquator(position)) return "#61764B";
        return "#FAD6A5";
    }

    /**
     * Temporary method, to check if equator is calculated correctly.
     */
    public boolean isInEquator(Vector2D position) {
        return this.equatorLL.precedes(position) && this.equatorUR.follows(position);
    }
}
