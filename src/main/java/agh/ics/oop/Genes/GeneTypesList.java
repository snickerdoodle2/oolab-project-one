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
}
