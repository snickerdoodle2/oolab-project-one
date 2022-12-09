package agh.ics.oop;

import agh.ics.oop.Engine.SimulationEngine;
import agh.ics.oop.Maps.Earth;
import agh.ics.oop.utility.Directions;

public class Main {
    public static void main(String[] args){
        SimulationEngine engine = new SimulationEngine(new Earth(10, 30), 1000);
        engine.run();
    }
}
