package agh.ics.oop.Gui;

import agh.ics.oop.Gui.Legend.Legend;
import agh.ics.oop.Gui.Legend.LegendItem;
import agh.ics.oop.Maps.Earth;
import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Plants.PlantGeneratorsList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private int appWidth = 600;
    private int appHeight = 800;

//    TODO: ADD LEGEND ITEMS
    private LegendItem[] legendItems = {
            new LegendItem(new Label("icon"), "text"),
            new LegendItem(new Label("icon123"), "text1")
};

    private static Legend legend = new Legend();

    public void start(Stage primaryStage){
        HBox mainContainer = new HBox();
        mainContainer.setAlignment(Pos.CENTER);

        VBox centerContainer = new VBox(mainContainer);
        centerContainer.minHeight(appHeight);
        centerContainer.setAlignment(Pos.CENTER);

        Scene scene = new Scene(centerContainer, appWidth, appHeight);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}