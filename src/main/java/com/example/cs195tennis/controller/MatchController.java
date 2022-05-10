package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.PlayerLoader;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.Rankings;
import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class MatchController implements Initializable {

    @FXML public AnchorPane rootAnchorPane;
    public TextField searchBox2;
    @FXML BorderPane rootPane;

    public TextField searchBox;

    ObservableList<Match> matchObservableList = FXCollections.observableArrayList();

    public List<Match> matchList;

    @FXML public TableView<Match> matchTable;

    @FXML public TableColumn<Match, String> tourney_nameCol, tourney_dateCol, winner_nameCol, loser_nameCol, scoreCol, roundCol, matchNumCol,bestOfCol;

    Map<String, List<Match>> matchMap = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            matchMap = MatchDao.readAtpMatchToMap();
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        matchTable.getSelectionModel().selectedIndexProperty().addListener((
                e -> {

                    Match selectedMatch =  matchTable.getSelectionModel().selectedItemProperty().get();

                    int rowNumber = matchTable.getSelectionModel().selectedIndexProperty().get();

                    try {
                        handleRowClick(rowNumber, selectedMatch);
                    } catch (IOException | CsvValidationException ex) {
                        ex.printStackTrace();
                    }

                }));

        matchMap.forEach((k, v) -> v.forEach(e ->

                matchObservableList.add(new Match(e.getTourney_name(), e.getTourney_date(), e.getMatch_num(), e.getWinner_name(), e.getLoser_name(), e.getScore(),
                        e.getRound(), e.getBest_of()
                ))));

        tourney_nameCol.setCellValueFactory(new PropertyValueFactory<>("tourney_name"));
        tourney_dateCol.setCellValueFactory(new PropertyValueFactory<>("tourney_date"));
        winner_nameCol.setCellValueFactory(new PropertyValueFactory<>("winner_name"));
        loser_nameCol.setCellValueFactory(new PropertyValueFactory<>("loser_name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        roundCol.setCellValueFactory(new PropertyValueFactory<>("round"));
        matchNumCol.setCellValueFactory(new PropertyValueFactory<>("match_num"));
        bestOfCol.setCellValueFactory(new PropertyValueFactory<>("best_of"));

        matchTable.setItems(matchObservableList);
    }

    //provide stat catgeory button click a match -> button for winner -> map winner_id of that match -> value: ShotType constructor using match_id and winner_id
    //to GridPane -> HBox -> Root Pane
    private void handleRowClick(int rowNumber, Match selectedMatch) throws IOException, CsvValidationException {

        AtomicReference<String> match_id = new AtomicReference<>("");

        Map<String, List<Rankings>> playerRanks = PlayerDao.readCsvToMap();

        Map<String, List<Player>> playerMap = PlayerDao.getPlayerMap();

        playerMap.forEach((k, v) -> v.forEach(e -> {

            String fullName = e.getFirstName() + " " + e.getLastName();

            if(fullName.equals(selectedMatch.getWinner_name()) || fullName.equals(selectedMatch.getLoser_name())) {

                match_id.set(e.getId());

                System.out.println(match_id);
            }
        }));

        Map<String, List<String>> mapSeasonTotals = new HashMap<>();

        matchMap.forEach((k, v)-> v.forEach(e -> {
            String season_total_id = "";

            mapSeasonTotals.computeIfAbsent(season_total_id, key -> new ArrayList<>());

            }
        ));
    }


    public void handleMatchAdd(ActionEvent event) throws SQLException {
        MatchDao.insert();
    }

    public void handleExitButtonClicked(ActionEvent event) throws IOException {

        Platform.exit();
        event.consume();
        PlayerLoader playerLoader = new PlayerLoader();
    }

    public void handleSearch(ActionEvent event) {

        matchObservableList.clear();

        matchMap.forEach((k,v) -> v.stream().filter(e -> e.getWinner_id().equals(searchBox.getText()) ||
                e.getWinner_seed().equals(searchBox.getText()) ||
                e.getWinner_entry().equals(searchBox.getText()) || e.getWinner_name().equals(searchBox.getText()) ||
                e.getWinner_hand().equals(searchBox.getText())
        ).collect(Collectors.toList()));

//        matchObservableList.addAll();
//
//        matchTable.setItems(matchObservableList);
    }

    public void handleClearSearchText(ActionEvent event) throws SQLException {

    }

    public void handleGitButtonClicked(ActionEvent event) {
        new Application() {
            @Override
            public void start(Stage stage) {
            }
        }.getHostServices().showDocument("https://github.com/stewseo/cs195-TennisTracker");
        event.consume();
    }

    Node getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : rootAnchorPane.getChildren()) {
            if(GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }

    public void handleButton3CLick(ActionEvent event) {
            Node node = (Node) event.getTarget();
            int row = GridPane.getRowIndex(node);
            int column = GridPane.getColumnIndex(node);
    }

    public void handleButton4Click(ActionEvent event) {
    }

    public void handleSearch2(ActionEvent event) {
    }
}
