package agh.ics.oop.Animal;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Utility.Options;

public class ObedientAnimal extends Animal {
    public ObedientAnimal(Options options, WorldMap map) {
        super(options, map);
    }

    public ObedientAnimal(Options options, WorldMap map, Animal parent1, Animal parent2) {
        super(options, map, parent1, parent2);
    }

    @Override
    protected void nextGene() {
        super.curGeneIndex++;
    }
}
