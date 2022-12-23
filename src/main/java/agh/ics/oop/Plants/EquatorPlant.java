package agh.ics.oop.Plants;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Utility.Vector2D;

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
        Vector2D tmp = new Vector2D(this.random.nextInt(this.map.getWidth()), this.random.nextInt(equatorUR.y-equatorLL.y+1)+equatorLL.y);
        while (map.plantAt(tmp)){
            tmp = new Vector2D(this.random.nextInt(this.map.getWidth()), this.random.nextInt(equatorUR.y-equatorLL.y+1)+equatorLL.y);
        }
        return tmp;
    }

    @Override
    protected Vector2D generateDisPreferred() {
        //            TODO: Make plant generator outside equator better :D
        int up, down;
        if (this.random.nextDouble() > .5) {
            down = 0;
            up = this.equatorLL.y;
        } else {
            down = this.equatorUR.y + 1;
            up = this.map.getHeight() - 1;
        }
        Vector2D tmp = new Vector2D(this.random.nextInt(this.map.getWidth()), this.random.nextInt(up - down+1) + down);

        while (map.plantAt(tmp)){
            tmp = new Vector2D(this.random.nextInt(this.map.getWidth()), this.random.nextInt(up - down+1) + down);
        }
        return tmp;
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
