package agh.ics.oop.Gui.Legend;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;

public class Legend {
    public static VBox generateLegend(List<LegendItem> items){
        VBox legend = new VBox();
            for (LegendItem item : items){
                Region spacer = new Region();
                Text title = new Text(item.getText());
                title.setStyle("-fx-font-weight: 500;" +
                        "-fx-font-size: 16;");
                HBox container = new HBox(item.getIcon(),  spacer, title);
                container.setHgrow(spacer, Priority.ALWAYS);
                container.setMaxWidth(150);
                legend.getChildren().add(container);
            }
            legend.setSpacing(5);
            return legend;
    }
}
