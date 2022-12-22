package agh.ics.oop.Genes;

import agh.ics.oop.Animal.Animal;
import agh.ics.oop.Utility.Options;

public class CorrectionGene extends Gene{
    public CorrectionGene(int length) {
        super(length);
    }

    public CorrectionGene(int length, Animal parent1, Animal parent2, Options options) {
        super(length, parent1, parent2, options);
    }

    @Override
    protected int mutate(int value) {
        return super.random.nextFloat() > .5 ? (value + 1) % (super.maxGene + 1) : (value - 1 == -1 ? 7 : value -1);
    }

}
