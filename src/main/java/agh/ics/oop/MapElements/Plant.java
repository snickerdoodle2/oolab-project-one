package agh.ics.oop.MapElements;

import agh.ics.oop.utility.Vector2D;

public class Plant implements MapElement{
    private Vector2D position;

    public Plant(Vector2D position) {
        this.position = position;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }
}