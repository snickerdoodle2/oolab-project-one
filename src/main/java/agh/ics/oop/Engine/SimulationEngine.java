package agh.ics.oop.Engine;

import agh.ics.oop.Maps.WorldMap;

import java.time.LocalDateTime;

public class SimulationEngine implements Runnable {
    private WorldMap map;
    private int moveDelay;

    public SimulationEngine(WorldMap map, int moveDelay) {
        this.map = map;
        this.moveDelay = moveDelay;
    }

    public void run() {
        while (true){
            try {
                System.out.println(LocalDateTime.now());
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }

        }
    }
}
