package agh.ics.oop.Genes;


import agh.ics.oop.Animal.Animal;
import agh.ics.oop.Utility.Options;

public class RandomGene extends Gene {
    public RandomGene(int length) {
        super(length);
    }

    public RandomGene(int length, Animal parent1, Animal parent2, Options options) {
        super(length, parent1, parent2, options);
    }

    @Override
    protected int mutate(int value) {
        return random.nextInt(super.maxGene + 1);
    }
}
