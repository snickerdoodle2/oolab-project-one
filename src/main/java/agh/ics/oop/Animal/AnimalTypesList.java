package agh.ics.oop.Animal;

public enum AnimalTypesList {
    CRAZY,
    OBEDIENT;

    @Override
    public String toString() {
        return switch (this) {
            case CRAZY -> "Nieco szalenstwa";
            case OBEDIENT -> "Pelna predestynacja";
        };
    }

    public static AnimalTypesList fromString(String input){
        return switch (input) {
            case "Nieco szalenstwa" -> CRAZY;
            case "Pelna predestynacja" -> OBEDIENT;
            default -> null;
        };
    }
}
