package agh.ics.oop.Utility;

import agh.ics.oop.Animal.AnimalTypesList;
import agh.ics.oop.Genes.GeneTypesList;
import agh.ics.oop.Maps.MapTypeList;
import agh.ics.oop.Plants.PlantGeneratorsList;

public class Options {
//    MAP OPTIONS
    public int mapHeight;
    public int mapWidth;
    public MapTypeList mapType;

//    PLANTS OPTIONS
    public PlantGeneratorsList plantType;
    public int initialPlants;
    public int plantsPerDay;
    public int energyPerPlant;

//    ANIMALS OPTIONS
    public AnimalTypesList animalType;
    public int minToBreed;
    public int initialAnimals;
    public int initialEnergy;
    public int energyToBreed;

//    GENES OPTIONS
    public GeneTypesList geneType;
    public int geneLength;
    public int minMutations;
    public int maxMutations;

//    OTHER
    public int delay;
}
