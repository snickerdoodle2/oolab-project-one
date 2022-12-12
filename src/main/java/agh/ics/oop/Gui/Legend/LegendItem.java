package agh.ics.oop.Gui.Legend;

import javafx.scene.Node;

public class LegendItem {
    private Node icon;
    private String text;

    public LegendItem(Node icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public Node getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }
}
