package agh.ics.oop.Gui.Legend;

import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Legend {
    public VBox generateLegend(LegendItem[] items){
        VBox legend = new VBox();
            for (LegendItem item : items){
                Region spacer = new Region();
                HBox container = new HBox(item.getIcon(),  spacer, new Label(item.getText()));
                container.setHgrow(spacer, Priority.ALWAYS);
                container.setMinWidth(120);
                legend.getChildren().add(container);
            }
            return legend;
    }
}
