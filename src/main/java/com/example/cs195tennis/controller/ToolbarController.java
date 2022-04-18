package com.example.cs195tennis.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;



public class ToolbarController implements Initializable {

    @FXML
    public void loadAddMatch(ActionEvent event) {}

    @FXML
    public void loadAddTournament(ActionEvent event) {

    }


    public static final String ICON_LOC = "";

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_LOC));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void loadAddMatchData(ActionEvent actionEvent) {
        System.out.println(actionEvent.toString());
    }

    public void loadAddTournamentList(ActionEvent actionEvent) {

    }

    public void loadAddMatchDataPointsList(ActionEvent actionEvent) {

    }


    public void loadSettings(ActionEvent actionEvent) {

    }

    public void setTournamentReturnCall(MainController mainController) {
    }

    public void loadUtil(ActionEvent actionEvent) {

    }
}
