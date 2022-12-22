package agh.ics.oop.Maps;

public enum MapTypeList {
    EARTH,
    PORTALS;


    @Override
    public String toString() {
        return switch (this) {
            case EARTH -> "Kula Ziemska";
            case PORTALS -> "Piekielny Portal";
        };
    }
}
