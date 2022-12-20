package agh.ics.oop.Genes;

import java.util.Random;

public class Gene {
    private final int length;

    private static int minGene = 0;
    private static int maxGene = 7;

    private static final Random random = new Random();

    private int[] genes;

    public Gene(int length) {
        this.length = length;
        this.genes = new int[length];

        for (int i = 0; i < length; i++){
            this.genes[i] = this.random.nextInt(maxGene+1);
        }
    }

    public int getGene(int i) {
        return genes[i % this.length];
    }
}
