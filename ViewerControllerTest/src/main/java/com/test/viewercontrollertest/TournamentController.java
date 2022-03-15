package com.test.viewercontrollertest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TournamentController implements Initializable {
    @FXML
    PieChart pieChart;
    @FXML
    TableView<TournamentStats> tableStats;
    @FXML private TableColumn<TournamentStats, String> tournament_nameCol;
    @FXML private TableColumn<TournamentStats, String> surfaceCol;
    @FXML private TableColumn<TournamentStats, String> tourney_dateCol;
    @FXML private TableColumn<TournamentStats, String> match_numCol;
    @FXML private TableColumn<TournamentStats, String> winner_nameCol;
    @FXML private TableColumn<TournamentStats, String> loser_nameCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("QB 60%",60),
                new PieChart.Data("WR 40%", 40));

        tournament_nameCol.setCellValueFactory(new PropertyValueFactory<>("tournament_name"));
        surfaceCol.setCellValueFactory(new PropertyValueFactory<>("surface"));
        tourney_dateCol.setCellValueFactory(new PropertyValueFactory<>("tourney_date"));
        match_numCol.setCellValueFactory(new PropertyValueFactory<>("match_num"));
        winner_nameCol.setCellValueFactory(new PropertyValueFactory<>("winner_name"));
        loser_nameCol.setCellValueFactory(new PropertyValueFactory<>("loser_name"));

        ObservableList<TournamentStats> teamStats = FXCollections.observableArrayList();

        tableStats.setItems(teamStats);

        pieChart.setData(pieChartData);
    }
}
