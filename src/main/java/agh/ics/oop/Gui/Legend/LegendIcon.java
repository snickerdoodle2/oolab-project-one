package agh.ics.oop.Gui.Legend;

import javafx.scene.layout.Pane;

public class LegendIcon {
    public static Pane generateIcon(String color, int size) {
        Pane icon = new Pane();
        icon.setStyle("-fx-background-color: " + color +";");
        icon.setMinSize(size, size);
        return icon;
    }
}
