package agh.ics.oop.Plants;

public enum PlantGeneratorsList {
    EQUATOR,
    TOXIC;


    @Override
    public String toString() {
        return switch (this) {
            case EQUATOR -> "Zalesione Rowniki";
            case TOXIC -> "Toksyczne Trupy";
        };
    }

    public static PlantGeneratorsList fromString(String input){
        return switch (input) {
            case "Zalesione Rowniki", "EQUATOR" -> EQUATOR;
            case "Toksyczne Trupy", "TOXIC" -> TOXIC;
            default -> null;
        };
    }
}
