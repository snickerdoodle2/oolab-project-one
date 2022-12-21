package agh.ics.oop.Genes;


public class RandomGene extends Gene {
    public RandomGene(int length) {
        super(length);
    }

    @Override
    protected int mutate(int value) {
        return super.random.nextInt(super.maxGene + 1);
    }
}
