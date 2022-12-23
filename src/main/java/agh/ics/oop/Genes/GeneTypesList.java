package agh.ics.oop.Genes;

public enum GeneTypesList {
    RANDOM,
    CORRECTION;


    @Override
    public String toString() {
        return switch (this) {
            case RANDOM -> "Pelna Losowosc";
            case CORRECTION -> "Lekka Korekta";
        };
    }

    public static GeneTypesList fromString(String input){
        return switch (input) {
            case "Pelna Losowosc" -> RANDOM;
            case "Lekka Korekta" -> CORRECTION;
            default -> null;
        };
    }
}
