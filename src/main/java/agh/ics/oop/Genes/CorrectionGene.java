package agh.ics.oop.Genes;

public class CorrectionGene extends Gene{
    public CorrectionGene(int length) {
        super(length);
    }

    @Override
    protected int mutate(int value) {
        return super.random.nextFloat() > .5 ? (value + 1) % (super.maxGene + 1) : (value - 1 == -1 ? 7 : value -1);
    }
}
