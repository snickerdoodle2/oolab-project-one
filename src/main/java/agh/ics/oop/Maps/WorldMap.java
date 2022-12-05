package agh.ics.oop.Maps;

import agh.ics.oop.utility.Vector2D;

import java.util.HashMap;
import java.util.Map;

public abstract class WorldMap {
    private int width;
    private int height;

    public WorldMap(int width, int height){
        this.width = width;
        this.height = height;
    }
}
