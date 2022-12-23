package agh.ics.oop.Maps;

import agh.ics.oop.Utility.Directions;
import agh.ics.oop.Utility.Options;
import agh.ics.oop.Utility.Vector2D;

import java.util.Random;

public class PortalsMap extends WorldMap{

    private static final Random random = new Random();
    public PortalsMap(Options options) {
        super(options);
    }

    @Override
    protected Vector2D calculateNewPosition(Vector2D position, Directions direction) {
        Vector2D newPosition = position.add(direction.toUnitVector());
        if (
                newPosition.x == -1 ||
                newPosition.y == -1 ||
                newPosition.x == super.width ||
                newPosition.y == super.height
        ) return new Vector2D(random.nextInt(super.width), random.nextInt(super.height));
        return newPosition;
    }
}
