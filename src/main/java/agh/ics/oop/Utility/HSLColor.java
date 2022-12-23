package agh.ics.oop.Utility;


import javafx.scene.paint.Color;

public class HSLColor {
    public static String convertToHex(Color color) {
        return ("#" + Integer.toHexString((color.hashCode()))).substring(0, 7);
    }
}
