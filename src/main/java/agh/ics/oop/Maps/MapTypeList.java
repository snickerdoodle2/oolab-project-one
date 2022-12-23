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

    public static MapTypeList fromString(String input){
        return switch (input) {
            case "Kula Ziemska", "EARTH" -> EARTH;
            case "Piekielny Portal", "PORTALS" -> PORTALS;
            default -> null;
        };
    }
}
