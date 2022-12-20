package agh.ics.oop.Animal;

import agh.ics.oop.Genes.Gene;
import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Utility.Directions;
import agh.ics.oop.Utility.Vector2D;

import java.util.Random;

public abstract class Animal {
    private int energy;
    private Vector2D position;
    private final Gene genes;
    private final WorldMap map;

    public Vector2D getPosition() {
        return position;
    }

    protected int curGeneIndex = 0;
    protected final int geneLength;

    private static final Random random = new Random();

    public Animal(int energy, int geneLength, WorldMap map) {
        this.energy = energy;

        this.map = map;
        this.position = new Vector2D(random.nextInt(map.getWidth()), random.nextInt(map.getHeight()));

        this.geneLength = geneLength;
        this.genes = new Gene(geneLength);
    }

    protected abstract void nextGene();

    public void move() {
        int curGene = genes.getGene(this.curGeneIndex);
        nextGene();
        this.position = this.map.moveAnimal(this.position, Directions.values()[curGene]);
    }

    public void decrementEnergy() {
        this.energy--;
    }

    public boolean isDead() {
        return this.energy == 0;
    }
}
