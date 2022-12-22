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
}
