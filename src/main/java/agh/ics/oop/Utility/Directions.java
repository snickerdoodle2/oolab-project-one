package agh.ics.oop.Utility;

import java.util.Random;

public enum Directions {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    private static final Random random = new Random();
    public static Directions randomDirection() {
        Directions[] directions = values();
        return directions[random.nextInt(directions.length)];
    }

    private Directions next() {
        return switch (this) {
            case NORTH -> NORTHEAST;
            case NORTHEAST -> EAST;
            case EAST -> SOUTHEAST;
            case SOUTHEAST -> SOUTH;
            case SOUTH -> SOUTHWEST;
            case SOUTHWEST -> WEST;
            case WEST -> NORTHWEST;
            case NORTHWEST -> NORTH;
        };
    }

    public Directions turn(int n) {
        Directions tmp = this;
        for (int i = 0; i < n; i++){
            tmp = tmp.next();
        }
        return tmp;
    }

    public Vector2D toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2D(0, 1);
            case NORTHEAST -> new Vector2D(1, 1);
            case EAST -> new Vector2D(1, 0);
            case SOUTHEAST -> new Vector2D(1, -1);
            case SOUTH -> new Vector2D(0, -1);
            case SOUTHWEST -> new Vector2D(-1, -1);
            case WEST -> new Vector2D(-1, 0);
            case NORTHWEST -> new Vector2D(-1, 1);
        };
    }
}
