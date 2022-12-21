package agh.ics.oop.Maps;

import agh.ics.oop.Utility.Directions;
import agh.ics.oop.Utility.Options;
import agh.ics.oop.Utility.Vector2D;

public class EarthMap extends WorldMap {
    public EarthMap(Options options) {
        super(options);
    }

    @Override
    public Vector2D calculateNewPosition(Vector2D position, Directions direction) {
        Vector2D dirPos = direction.toUnitVector();
        int newX = (position.x+dirPos.x)%width;
        int newY = (position.y+dirPos.y)%height;
        return new Vector2D(newX >= 0 ? newX : width-1, newY >= 0 ? newY : height-1);
    }
}
