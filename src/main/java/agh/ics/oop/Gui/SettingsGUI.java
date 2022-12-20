package agh.ics.oop.Gui;

import agh.ics.oop.Utility.Options;
import javafx.application.Application;
import javafx.stage.Stage;

public class SettingsGUI extends Application {
    private int appWidth = 600;
    private int appHeight = 800;


    public void start(Stage primaryStage){
        for (int i =0; i < 2; i++ ){
            Stage tmpStage = new Stage();
            GameGUI tmp = new GameGUI(new Options());
            tmpStage.setScene(tmp.getScene());
            tmpStage.setOnHiding((event) -> {
                System.out.println("KONIEC");
                tmp.stageEnded();
            });
            tmpStage.show();
        }
    }
}