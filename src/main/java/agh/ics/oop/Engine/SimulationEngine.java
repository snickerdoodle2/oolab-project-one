package agh.ics.oop.Engine;

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
        map.init();
        while (true){
            try {
                if (map.simulateDay()) {
                    observer.rerender();
                    break;
                };
                observer.rerender();
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }

        }
        observer.threadFinished();
    }
}
