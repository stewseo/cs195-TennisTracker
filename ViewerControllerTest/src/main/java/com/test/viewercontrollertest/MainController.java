package com.test.viewercontrollertest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainController implements Initializable {
    @FXML private Button match, tournament, players;
    @FXML private ListView<TournamentStats> listView;
    private ObservableList<TournamentStats> tournamentStatsObservableList;

    public void handleButtonClicks(Event mouseEvent) {
        if (mouseEvent.getSource() == players) {loadStages("PlayerViewer.fxml");}
        else if (mouseEvent.getSource() == match) {loadStages("MatchViewer.fxml");}
        else if (mouseEvent.getSource() == tournament) {loadStages("TournamentViewer.fxml");}
    }

    public MainController()  {
        try {
            tournamentStatsObservableList = FXCollections.observableArrayList();
            tournamentStatsObservableList.addAll(
                    new TournamentStats("US_OPEN", TournamentStats.COURT.CLAY),
                    new TournamentStats("AUS_OPEN", TournamentStats.COURT.GRASS));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        listView.setItems(tournamentStatsObservableList);
//        listView.setCellFactory(tournamentListViewCell -> new TournamentListViewCell());


    }


    private void loadStages (String fxml){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}