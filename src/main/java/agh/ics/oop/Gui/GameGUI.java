package agh.ics.oop.Gui;

import agh.ics.oop.Animal.AnimalTypesList;
import agh.ics.oop.Engine.SimulationEngine;

import agh.ics.oop.Genes.GeneTypesList;
import agh.ics.oop.Gui.Legend.Legend;
import agh.ics.oop.Gui.Legend.LegendIcon;
import agh.ics.oop.Gui.Legend.LegendItem;

import agh.ics.oop.Maps.EarthMap;
import agh.ics.oop.Maps.MapTypeList;
import agh.ics.oop.Maps.PortalsMap;
import agh.ics.oop.Maps.WorldMap;

import agh.ics.oop.Plants.PlantGeneratorsList;
import agh.ics.oop.Utility.HSLColor;
import agh.ics.oop.Utility.IMapObserver;
import agh.ics.oop.Utility.Options;

import javafx.application.Platform;

import javafx.geometry.Pos;

import javafx.scene.Scene;
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

    private Thread engineThread;

    private WorldMap map;

//    TODO: LEGENDA
    private List<LegendItem> legendItems = new ArrayList<>();

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
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();

        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        VBox legend = Legend.generateLegend(legendItems);
        legend.setAlignment(Pos.CENTER);

        VBox leftColumn = generateColumn();
        leftColumn.getChildren().setAll(legend);
        VBox rightColumn = generateColumn();


        HBox container = new HBox();


        container.setStyle("-fx-padding: 32");
        container.getChildren().setAll(leftColumn, leftSpacer, mapPane, rightSpacer, rightColumn);
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
            mapPane.getChildren().setAll(map.toGridPane(500));
        });
    }

    public void threadFinished() {
        Platform.runLater(() -> {
            System.out.println("KONIEC:)");
        });
    }
}
