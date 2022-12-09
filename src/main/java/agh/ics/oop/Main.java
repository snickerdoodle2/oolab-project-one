package agh.ics.oop;

import agh.ics.oop.Engine.SimulationEngine;
import agh.ics.oop.Maps.Earth;
import agh.ics.oop.Plants.Equator;
import agh.ics.oop.Plants.PlantGeneratorsList;
import agh.ics.oop.utility.Directions;

public class Main {
    public static void main(String[] args){
        SimulationEngine engine = new SimulationEngine(new Earth(10, 31, PlantGeneratorsList.EQUATOR), 1000);
        engine.run();
    }
}
