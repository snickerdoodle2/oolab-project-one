package agh.ics.oop.Gui;

import agh.ics.oop.Animal.AnimalTypesList;
import agh.ics.oop.Genes.GeneTypesList;
import agh.ics.oop.Gui.Input.Dropdown;
import agh.ics.oop.Gui.Input.IntInput;
import agh.ics.oop.Maps.MapTypeList;
import agh.ics.oop.Plants.PlantGeneratorsList;
import agh.ics.oop.Utility.Options;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class SettingsGUI extends Application {
    private static final int appWidth = 600;
    private static final int appHeight = 900;

    private static final int appPadding = 20;

    private static final ColumnConstraints columnWidth = new ColumnConstraints(appWidth/2-appPadding);

    private Text errorMessage = new Text();
    private static final String titleText = "Gra o Ewolucji";
    private IntInput genesLength;
    private IntInput minMutations;
    private IntInput maxMutations;
    private IntInput energyToBreed;
    private IntInput minToBreed;
    private IntInput startingAnimalEnergy;
    private IntInput animalAmount;
    private IntInput energyPerPlant;
    private IntInput plantPerDay;
    private IntInput plantAmount;
    private IntInput mapHeight;
    private IntInput mapWidth;
    private IntInput dayLength;
    private Dropdown mapType;
    private Dropdown plantType;
    private Dropdown animalType;
    private Dropdown geneType;

    public void start(Stage primaryStage){
        VBox settingsContainer = new VBox();
        settingsContainer.setStyle("-fx-padding: "+appPadding);
        settingsContainer.setSpacing(appPadding);
        Scene settingsScene = new Scene(settingsContainer, appWidth, appHeight);


        Text title = new Text(titleText);
        title.setStyle(
                "-fx-font-weight: 900;" +
                "-fx-font-size: 24");
        settingsContainer.setAlignment(Pos.TOP_CENTER);
        settingsContainer.getChildren().add(title);



//        MAP
        GridPane mapSettings = new GridPane();
        mapSettings.setAlignment(Pos.TOP_CENTER);
        mapSettings.getColumnConstraints().addAll(columnWidth, columnWidth);

        Text mapTitle =new Text("Ustawienia Mapy");
        mapTitle.setStyle("-fx-font-size: 16;" +
                "-fx-font-weight: 700");
        GridPane.setHalignment(mapTitle, HPos.CENTER);
        mapSettings.add(mapTitle, 0, 0, 2, 1);

        mapWidth = new IntInput("Szerokosc mapy");
        mapSettings.add(mapWidth.generateInput(), 0, 1, 1, 1);

        mapHeight = new IntInput("Wysokosc mapy");
        mapSettings.add(mapHeight.generateInput(), 1, 1, 1, 1);

        String[] mapTypes = Stream.of(MapTypeList.values()).map(Enum::toString).toArray(String[]::new);
        mapType = new Dropdown(mapTypes, "Rodzaj mapy");
        mapSettings.add(mapType.generateInput(), 0, 2, 1, 1);



        settingsContainer.getChildren().add(mapSettings);




//        PLANTS
        GridPane plantSettings = new GridPane();
        plantSettings.setAlignment(Pos.TOP_CENTER);
        plantSettings.getColumnConstraints().addAll(columnWidth, columnWidth);

        Text plantTitle =new Text("Ustawienia Roslin");
        plantTitle.setStyle("-fx-font-size: 16;" +
                "-fx-font-weight: 700");
        GridPane.setHalignment(plantTitle, HPos.CENTER);
        plantSettings.add(plantTitle, 0, 0, 2, 1);

        plantPerDay = new IntInput("Dziennie");
        plantSettings.add(plantPerDay.generateInput(), 0, 1, 1, 1);

        plantAmount = new IntInput("Ilosc poczatkowa");
        plantSettings.add(plantAmount.generateInput(), 1, 1, 1, 1);

        String[] plantTypes = Stream.of(PlantGeneratorsList.values()).map(Enum::toString).toArray(String[]::new);
        plantType = new Dropdown(plantTypes, "Rodzaj roslin");
        plantSettings.add(plantType.generateInput(), 0, 2, 1, 1);

        energyPerPlant = new IntInput("Energia");
        plantSettings.add(energyPerPlant.generateInput(), 1, 2, 1, 1);


        settingsContainer.getChildren().add(plantSettings);


//        ANIMAL
        GridPane animalSettings = new GridPane();
        animalSettings.setAlignment(Pos.TOP_CENTER);
        animalSettings.getColumnConstraints().addAll(columnWidth, columnWidth);

        Text animalTitle =new Text("Ustawienia zwierzat");
        animalTitle.setStyle("-fx-font-size: 16;" +
                "-fx-font-weight: 700");
        GridPane.setHalignment(animalTitle, HPos.CENTER);
        animalSettings.add(animalTitle, 0, 0, 2, 1);

        animalAmount = new IntInput("Ilosc poczatkowa");
        animalSettings.add(animalAmount.generateInput(), 0, 1, 1, 1);

        startingAnimalEnergy = new IntInput("Energia na start");
        animalSettings.add(startingAnimalEnergy.generateInput(), 1, 1, 1, 1);

        minToBreed = new IntInput("Min. do rozmnazania");
        animalSettings.add(minToBreed.generateInput(), 0, 2, 1, 1);

        energyToBreed = new IntInput("Zuzyta energia na rozmnazanie");
        animalSettings.add(energyToBreed.generateInput(), 1, 2, 1, 1);

        String[] animalTypes = Stream.of(AnimalTypesList.values()).map(AnimalTypesList::toString).toArray(String[]::new);
        animalType = new Dropdown(animalTypes, "Rodzaj zwierzat");
        animalSettings.add(animalType.generateInput(), 0, 3, 1, 1);


        settingsContainer.getChildren().add(animalSettings);
        
//        GENES
        GridPane genesSettings = new GridPane();
        genesSettings.setAlignment(Pos.TOP_CENTER);
        genesSettings.getColumnConstraints().addAll(columnWidth, columnWidth);

        Text genesTitle = new Text("Ustawienia genow");
        genesTitle.setStyle("-fx-font-size: 16;" +
                "-fx-font-weight: 700");
        GridPane.setHalignment(genesTitle, HPos.CENTER);
        genesSettings.add(genesTitle, 0, 0, 2, 1);

        minMutations = new IntInput("Min. mutacji");
        genesSettings.add(minMutations.generateInput(), 0, 1, 1, 1);

        maxMutations = new IntInput("Max. mutacji");
        genesSettings.add(maxMutations.generateInput(), 1, 1, 1, 1);

        genesLength = new IntInput("Dlugosc genu");
        genesSettings.add(genesLength.generateInput(), 1, 2, 1, 1);

        String[] geneTypes = Stream.of(GeneTypesList.values()).map(Enum::toString).toArray(String[]::new);
        geneType = new Dropdown(geneTypes, "Rodzaj genow");
        genesSettings.add(geneType.generateInput(), 0, 2, 1, 1);

        settingsContainer.getChildren().add(genesSettings);

//        GENERAL SETTINGS
        GridPane generalSettings = new GridPane();
        generalSettings.setAlignment(Pos.TOP_CENTER);
        generalSettings.getColumnConstraints().addAll(columnWidth, columnWidth);

        Text generalTitle = new Text("Ustawienia ogolne");
        generalTitle.setStyle("-fx-font-size: 16;" +
                "-fx-font-weight: 700");
        GridPane.setHalignment(generalTitle, HPos.CENTER);
        generalSettings.add(generalTitle, 0, 0, 2, 1);

        File f = new File("src/main/resources/presets/");
        Dropdown files = new Dropdown(f.list(), "Pliki");
        generalSettings.add(files.generateInput(), 0, 1, 1, 1);

        Button loadFileButton = new Button("ZALADUJ PLIK");
        GridPane.setHalignment(loadFileButton, HPos.CENTER);
        generalSettings.add(loadFileButton, 1, 1, 1, 1);

        loadFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fileName = files.getValue();
                if (fileName == null) return;
                try {
                    File presetFile = new File("src/main/resources/presets/" + fileName);
                    Scanner presetReader = new Scanner(presetFile);
                    while (presetReader.hasNextLine()) {
                        String line = presetReader.nextLine();
                        String[] lineArgs = line.split("\\s+");
                        if (lineArgs.length < 2) continue;
                        switch (lineArgs[0]) {
                            case "MAP_HEIGHT":
                                mapHeight.setValue(lineArgs[1]);
                                break;
                            case "MAP_WIDTH":
                                mapWidth.setValue(lineArgs[1]);
                                break;
                            case "MAP_TYPE":
                                mapType.setValue(lineArgs[1]);
                                break;
                            case "PLANT_TYPE":
                                plantType.setValue(lineArgs[1]);
                                break;
                            case "INITIAL_PLANTS":
                                plantAmount.setValue(lineArgs[1]);
                                break;
                            case "PLANTS_PER_DAY":
                                plantPerDay.setValue(lineArgs[1]);
                                break;
                            case "ENERGY_PER_PLANT":
                                energyPerPlant.setValue(lineArgs[1]);
                                break;
                            case "ANIMAL_TYPE":
                                animalType.setValue(lineArgs[1]);
                                break;
                            case "MIN_TO_BREED":
                                minToBreed.setValue(lineArgs[1]);
                                break;
                            case "INITIAL_ANIMALS":
                                animalAmount.setValue(lineArgs[1]);
                                break;
                            case "INITIAL_ENERGY":
                                startingAnimalEnergy.setValue(lineArgs[1]);
                                break;
                            case "ENERGY_TO_BREED":
                                energyToBreed.setValue(lineArgs[1]);
                                break;
                            case "GENE_TYPE":
                                geneType.setValue(lineArgs[1]);
                                break;
                            case "GENE_LENGTH":
                                genesLength.setValue(lineArgs[1]);
                                break;
                            case "MIN_MUTATIONS":
                                minMutations.setValue(lineArgs[1]);
                                break;
                            case "MAX_MUTATIONS":
                                maxMutations.setValue(lineArgs[1]);
                                break;
                            case "DELAY":
                                dayLength.setValue(lineArgs[1]);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            }
        });

        dayLength = new IntInput("Dlugosc dnia (ms)");
        generalSettings.add(dayLength.generateInput(), 0, 2, 1, 1);

        Button startButton = new Button("START");
        GridPane.setHalignment(startButton, HPos.CENTER);
        generalSettings.add(startButton, 1, 2, 1, 1);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorMessage.setText("");
                try {
                    if (!checkInputs()) {
                        errorMessage.setText("Sprawdz poprawonosc wprowadzonych danych");
                        return;
                    };
                    Options options = new Options();
                    options.mapHeight = mapHeight.getValue();
                    options.mapWidth = mapWidth.getValue();
                    options.mapType = MapTypeList.fromString(mapType.getValue());

                    options.plantType = PlantGeneratorsList.fromString(plantType.getValue());
                    options.initialPlants = plantAmount.getValue();
                    options.plantsPerDay = plantPerDay.getValue();
                    options.energyPerPlant = energyPerPlant.getValue();

                    options.animalType = AnimalTypesList.fromString(animalType.getValue());
                    options.minToBreed = minToBreed.getValue();
                    options.initialAnimals = animalAmount.getValue();
                    options.initialEnergy = startingAnimalEnergy.getValue();
                    options.energyToBreed = energyToBreed.getValue();

                    options.geneType = GeneTypesList.fromString(geneType.getValue());
                    options.geneLength = genesLength.getValue();
                    options.minMutations = minMutations.getValue();
                    options.maxMutations = maxMutations.getValue();

                    options.delay = dayLength.getValue();


                    newGame(options);
                } catch (Exception e) {
                    errorMessage.setText("Uzupelnij wszystkie pola");
                }
            }
        });



        settingsContainer.getChildren().add(generalSettings);

        errorMessage.setFill(Color.RED);
        settingsContainer.getChildren().add(errorMessage);


        primaryStage.setScene(settingsScene);
        primaryStage.setTitle(titleText);
        primaryStage.show();
    }

    private boolean checkInputs() throws Exception  {
        if (mapWidth.getValue() <= 0) return false;
        if (mapHeight.getValue() <= 0) return false;

        if (plantAmount.getValue() <= 0) return false;
        if (plantPerDay.getValue() <= 0) return false;
        if (energyPerPlant.getValue() <= 0) return false;

        if (animalAmount.getValue() <= 0) return false;
        if (startingAnimalEnergy.getValue() <= 0) return false;
        if (minToBreed.getValue() <= 0) return false;
        if (energyToBreed.getValue() <= 0) return false;
        if (minToBreed.getValue() <= energyToBreed.getValue() ) return false;

        if (genesLength.getValue() <= 0) return false;
        if (minMutations.getValue() < 0) return false;
        if (maxMutations.getValue() < 0) return false;
        if (minMutations.getValue() > maxMutations.getValue()) return false;
        if (minMutations.getValue() > genesLength.getValue() || maxMutations.getValue() > genesLength.getValue()) return false;

        if (dayLength.getValue() <= 0) return false;

        if (mapType.getValue() == null) throw new Exception("MAP");
        if (plantType.getValue() == null) throw new Exception("PLANT");
        if (animalType.getValue() == null) throw new Exception("ANIMAL");
        if (geneType.getValue() == null) throw new Exception("GENE");


        return true;
    }


    private void newGame(Options options) {
        Stage tmpStage = new Stage();
        GameGUI tmp = new GameGUI(options);

        tmpStage.setScene(tmp.getScene());
        tmpStage.setOnHiding((event) -> {
            tmp.stageEnded();
        });

        tmpStage.show();
    }
}