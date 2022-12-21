package agh.ics.oop.Genes;

import agh.ics.oop.Animal.Animal;

import java.util.Random;

public abstract class Gene {
    private final int length;
    protected static int maxGene = 7;

    protected static final Random random = new Random();

    private int[] genes;

    public Gene(int length) {
        this.length = length;
        this.genes = new int[length];

        for (int i = 0; i < length; i++){
            this.genes[i] = this.random.nextInt(maxGene+1);
        }
    }

    public Gene(Animal parent1, Animal parent2, int length) {
        this.length = length;

    }

    protected abstract int mutate(int value);

    public int getGene(int i) {
        return genes[i % this.length];
    }
}
