package agh.ics.oop.Animal;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Utility.Options;

public class CrazyAnimal extends Animal {
    public CrazyAnimal(Options options, WorldMap map) {
        super(options, map);
    }

    public CrazyAnimal(Options options, WorldMap map, Animal parent1, Animal parent2) {
        super(options, map, parent1, parent2);
    }

    @Override
    protected void nextGene() {
        if (random.nextFloat() > .8) {
            super.curGeneIndex = random.nextInt(super.geneLength);
        } else {
            super.curGeneIndex++;
        }
    }
}
