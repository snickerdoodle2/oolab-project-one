package agh.ics.oop.Gui;

import agh.ics.oop.Engine.SimulationEngine;
import agh.ics.oop.Gui.Legend.Legend;
import agh.ics.oop.Gui.Legend.LegendItem;
import agh.ics.oop.Maps.Earth;
import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Plants.PlantGeneratorsList;
import agh.ics.oop.Utility.IMapObserver;
import agh.ics.oop.Utility.Options;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameGUI implements IMapObserver {
    Options gameOptions;

    private static int windowsHeight = 800;
    private static int windowsWidth = 1200;

    private static Legend legendGenerator = new Legend();

    private VBox mapPane = new VBox();

    private Thread engineThread;

    private WorldMap map;

//    TODO: LEGENDA
    private LegendItem[] legendItems = {
            new LegendItem(new Label("1"), "jeden"),
            new LegendItem(new Label("2"), "dwa"),
            new LegendItem(new Label("3"), "trzy"),
            new LegendItem(new Label("4"), "cztery"),
    };;

    public GameGUI(Options gameOptions) {
        this.gameOptions = gameOptions;
    }

    private VBox generateColumn() {
        VBox myVBox = new VBox();
        myVBox.setMaxWidth(windowsWidth/4);
        myVBox.setMinWidth(windowsWidth/4);
        myVBox.setStyle("-fx-background-color: pink");

        return myVBox;
    }
    public Scene getScene() {
        map = new Earth(30, 30, PlantGeneratorsList.EQUATOR);
        mapPane.getChildren().setAll(new Label(":)"));
        mapPane.setAlignment(Pos.CENTER);

        VBox leftColumn = generateColumn();
        leftColumn.getChildren().setAll(legendGenerator.generateLegend(legendItems));

        VBox rightColumn = generateColumn();

        HBox container = new HBox();
        container.setStyle("-fx-padding: 32");
        container.getChildren().setAll(leftColumn, mapPane, rightColumn);
        container.setSpacing(24);

        engineThread = new Thread(new SimulationEngine(map, 1000, this));

        engineThread.start();


        return new Scene(container, windowsWidth, windowsHeight);
    }

    public void stageEnded() {
        engineThread.stop();
    }

//    RERENDER MAP
    @Override
    public void rerender() {
        Platform.runLater(() -> {
            mapPane.getChildren().setAll(map.toGridPane(500));
        });
    }
}
