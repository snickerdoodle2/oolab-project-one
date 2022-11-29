package agh.ics.oop.utility;

import java.util.Objects;

public class Vector2D {
    public final int x;
    public final int y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object other){
        if (this == other) return true;
        if (!(other instanceof Vector2D)) return false;
        Vector2D that = (Vector2D) other;
        if (this.x != that.x) return false;
        if (this.y != that.y) return false;
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public String toString(){
        return String.format("(%s,%s)", this.x, this.y);
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x+other.x,this.y+other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x-other.x,this.y-other.y);
    }

}
