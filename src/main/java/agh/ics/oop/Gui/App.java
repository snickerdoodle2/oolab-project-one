package agh.ics.oop.Gui;

import agh.ics.oop.Maps.Earth;
import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Plants.PlantGeneratorsList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private int appWidth = 600;
    private int appHeight = 600;
    public void start(Stage primaryStage){
        WorldMap equator = new Earth(100, 100, PlantGeneratorsList.EQUATOR);
        equator.generatePlant();
        GridPane mapContent = equator.toGridPane(550);
        mapContent.setStyle("-fx-border-color: black; -fx-border-width: 2px");

        HBox mainContainer = new HBox(mapContent);
        mainContainer.setAlignment(Pos.CENTER);

        VBox centerContainer = new VBox(mainContainer);
        centerContainer.minHeight(appHeight);
        centerContainer.setAlignment(Pos.CENTER);

        Scene scene = new Scene(centerContainer, appWidth, appHeight);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
