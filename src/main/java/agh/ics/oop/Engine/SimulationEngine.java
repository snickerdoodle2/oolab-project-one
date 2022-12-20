package agh.ics.oop.Engine;

import agh.ics.oop.Animal.Animal;
import agh.ics.oop.Animal.Obedient;
import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Utility.IMapObserver;

public class SimulationEngine implements Runnable {
    private WorldMap map;
    private int moveDelay;
    private IMapObserver observer;

    public SimulationEngine(WorldMap map, int moveDelay, IMapObserver observer) {
        this.map = map;
        this.moveDelay = moveDelay;
        this.observer = observer;
    }

    public void run() {
        Animal animal = new Obedient(50, 50, map);
        map.addAnimal(animal);
        while (true){
            try {
//                map.generatePlant();
                animal.move();
                observer.rerender();
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }

        }
    }
}
