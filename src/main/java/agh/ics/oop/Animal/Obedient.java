package agh.ics.oop.Animal;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Utility.Options;

public class Obedient extends Animal {
    public Obedient(Options options, WorldMap map) {
        super(options, map);
    }

    @Override
    protected void nextGene() {
        super.curGeneIndex++;
    }
}
