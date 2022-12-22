package agh.ics.oop.Genes;

import agh.ics.oop.Animal.Animal;
import agh.ics.oop.Utility.Options;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public abstract class Gene {
    private final int length;
    protected static int maxGene = 7;

    protected static final Random random = new Random();

    private int[] genes;

    public Gene(int length) {
        this.length = length;
        this.genes = new int[length];

        for (int i = 0; i < length; i++){
            this.genes[i] = random.nextInt(maxGene+1);
        }
    }

    public Gene(int length, Animal parent1, Animal parent2, Options options) {
        this.length = length;
        this.genes = new int[length];

        int summedEnergy = parent1.getEnergy() + parent2.getEnergy();

        float parent1part = (float) parent1.getEnergy()/summedEnergy;
        float parent2part = (float) parent2.getEnergy()/summedEnergy;

        int fromParent1Amount = (int) (length * parent1part);
        int fromParent2Amount = (int) (length * parent2part);

        Gene parent1Genes = parent1.getGenes();
        Gene parent2Genes = parent2.getGenes();
        fromParent1Amount += length-(fromParent1Amount+fromParent2Amount);

        int curIndex = 0;
        if (random.nextFloat() > .5) { // LEPSZY RODZIC LEWA
            while(curIndex < fromParent1Amount){
                this.genes[curIndex] = parent1Genes.getGene(curIndex);
                curIndex++;
            }
            while(curIndex < length) {
                this.genes[curIndex] = parent2Genes.getGene(curIndex);
                curIndex++;
            }
        } else {
            while(curIndex < fromParent2Amount){
                this.genes[curIndex] = parent2Genes.getGene(curIndex);
                curIndex++;
            }
            while(curIndex < length) {
                this.genes[curIndex] = parent1Genes.getGene(curIndex);
                curIndex++;
            }
        }

        int[] indexes = IntStream.range(0, length).toArray();
        shuffleIndex(indexes);

        for (int i = 0; i < (random.nextInt(options.maxMutations-options.minMutations+1)+options.minMutations); i++) {
            genes[indexes[i]] = mutate(genes[indexes[i]]);
        }
    }

    private void shuffleIndex(int[] t) {
        for (int i = 0; i < t.length; i++) {
            int indexToSwap = random.nextInt(t.length);
            int tmp = t[indexToSwap];
            t[indexToSwap] = t[i];
            t[i] = tmp;
        }
    }

    protected abstract int mutate(int value);

    public int getGene(int i) {
        return genes[i % this.length];
    }
}
