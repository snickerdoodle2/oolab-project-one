package agh.ics.oop.Animal;

import agh.ics.oop.Maps.WorldMap;

public class Obedient extends Animal {
    public Obedient(int energy, int geneLength, WorldMap map) {
        super(energy, geneLength, map);
    }

    @Override
    protected void nextGene() {
        super.curGeneIndex++;
    }
}
