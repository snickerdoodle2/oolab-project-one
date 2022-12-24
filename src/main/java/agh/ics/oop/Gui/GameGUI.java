package agh.ics.oop.Gui;

import agh.ics.oop.Engine.SimulationEngine;

import agh.ics.oop.Gui.Legend.Legend;
import agh.ics.oop.Gui.Legend.LegendIcon;
import agh.ics.oop.Gui.Legend.LegendItem;

import agh.ics.oop.Maps.EarthMap;
import agh.ics.oop.Maps.PortalsMap;
import agh.ics.oop.Maps.WorldMap;

import agh.ics.oop.Plants.PlantGeneratorsList;
import agh.ics.oop.Utility.HSLColor;
import agh.ics.oop.Utility.IMapObserver;
import agh.ics.oop.Utility.Options;

import javafx.application.Platform;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameGUI implements IMapObserver {
    Options gameOptions;

    private static final int windowsHeight = 800;
    private static final int windowsWidth = 1200;

    private final VBox mapPane = new VBox();

    private final VBox info = new VBox();

    private Thread engineThread;

    private WorldMap map;

    private boolean paused = false;
    private List<LegendItem> legendItems = new ArrayList<>();

    public GameGUI(Options gameOptions) {
        this.gameOptions = gameOptions;
    }

    private VBox generateColumn() {
        VBox myVBox = new VBox();
        myVBox.setMinWidth(windowsWidth/4);
        myVBox.setStyle("-fx-padding: 32;");
        myVBox.setSpacing(32);

        return myVBox;
    }
    public Scene getScene() {

        if (gameOptions.plantType == PlantGeneratorsList.EQUATOR) {
                legendItems.add(new LegendItem(LegendIcon.generateIcon("#61764B", 20), "Rownik"));
        }
        legendItems.add(new LegendItem(LegendIcon.generateIcon("#9BA17B", 20), "Trawa"));
        legendItems.add(new LegendItem(LegendIcon.generateIcon("#FAD6A5", 20), "Ziemia"));
        legendItems.add(new LegendItem(LegendIcon.generateIcon(HSLColor.convertToHex(Color.web("hsla(24, 100%, 100%, 1)")), 20), "Zwierzak"));

        map = switch (gameOptions.mapType) {
            case EARTH -> new EarthMap(gameOptions);
            case PORTALS -> new PortalsMap(gameOptions);
        };
        mapPane.setAlignment(Pos.CENTER);

        VBox legend = Legend.generateLegend(legendItems);
        legend.setAlignment(Pos.CENTER);

        VBox leftColumn = generateColumn();

        HBox.setHgrow(leftColumn, Priority.ALWAYS);

        VBox moreOptions = new VBox();


        Button pauseButton = new Button("PAUZA");
        pauseButton.setOnAction(event -> {
                if (paused){
                    engineThread.resume();
                } else {
                    engineThread.suspend();
                }
                paused = !paused;

        });
        VBox.setVgrow(info, Priority.ALWAYS);

        moreOptions.getChildren().addAll(info, pauseButton);
        moreOptions.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(moreOptions, Priority.ALWAYS);

        leftColumn.getChildren().setAll(moreOptions, legend);

        HBox container = new HBox();


        container.setStyle("-fx-padding: 32");
        container.getChildren().setAll(leftColumn, mapPane);
        container.setSpacing(24);

        engineThread = new Thread(new SimulationEngine(map, gameOptions.delay, this));

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
            mapPane.getChildren().setAll(map.toGridPane(windowsHeight-64));
            info.getChildren().setAll(
                    new Label("Dzien nr " + map.getDay()),
                    new Label("Liczba zwierzat: " + map.getAnimalsAlive()),
                    new Label("Liczba roslin: " + map.getNumberOfPlants()),
                    new Label("Liczba wolnych pol: " + map.getEmptyCells()),
                    new Label("Najpopularniejszy gen: " +map.getMostCommonGene()),
                    new Label("Srednia energia zyjacych zwierzat: " + map.getAverageEnergy()),
                    new Label("Srednia dlugosc zycia martwych zwierzat: " + map.getAverageAge())
            );
        });
    }

    @Override
    public void threadFinished() {
        Platform.runLater(() -> {
            System.out.println("KONIEC :)");
        });
    }
}
