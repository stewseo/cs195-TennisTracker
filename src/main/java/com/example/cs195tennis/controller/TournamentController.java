package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.PlayerLoader;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.Rankings;
import com.example.cs195tennis.model.Tournament;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TournamentController implements Initializable {

    public TextField tourneyInput;
    public HBox tournamentInfo;

    @FXML
    public TableView<Tournament> tournamentTable;

    @FXML
    public Button toPlayerTable;


    @FXML
    private TextField searchBox;

    @FXML
    public TableColumn<Tournament, String> tourney_idCol, tourney_nameCol, surfaceCol, draw_sizeCol, tourney_dateCol, tourney_levelCol, match_numCol;

    ObservableList<Tournament> observableList = FXCollections.observableArrayList();

    public List<Tournament> tournamentList;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane contentPane;


    private void insertTournamentCsvToSql() throws SQLException, IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tournamentTable.getSelectionModel().selectedIndexProperty().addListener((
                e -> {
                    Object object =  tournamentTable.getSelectionModel().selectedItemProperty().get();
                    int index = tournamentTable.getSelectionModel().selectedIndexProperty().get();
                    System.out.println("\ntournamentList.get("+index+") = " + tournamentList.get(index));
                    newWindow(tournamentList.get(index));
                }
                ));

        try {
            tournamentList = TournamentDao.allMatchesCsvToTournamentList();
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println(tournamentList.size());

        tournamentList.forEach(e-> observableList.add(
                new Tournament(e.getTourney_id(), e.getTourney_name(),e.getSurface(), e.getDraw_size(),
                        e.getTourney_date(), e.getTourney_level(), e.getMatch_num()
                )));

        tourney_idCol.setCellValueFactory(new PropertyValueFactory<>("tourney_id"));
        tourney_nameCol.setCellValueFactory(new PropertyValueFactory<>("tourney_name"));
        surfaceCol.setCellValueFactory(new PropertyValueFactory<>("surface"));
        draw_sizeCol.setCellValueFactory(new PropertyValueFactory<>("draw_size"));
        tourney_dateCol.setCellValueFactory(new PropertyValueFactory<>("tourney_date"));
        tourney_levelCol.setCellValueFactory(new PropertyValueFactory<>("tourney_level"));
        match_numCol.setCellValueFactory(new PropertyValueFactory<>("match_num"));

        tournamentTable.setItems(observableList);
    }

    private void newWindow(Tournament tournament) {
        System.out.println("\nInsert tournament: " + tournament + " to GridPane -> HBox -> Root Pane");
    }

    @FXML
    public void handleSearch(ActionEvent event) {

        observableList.clear();

       tournamentList = tournamentList.stream().filter(e -> e.getTourney_id().equals(searchBox.getText()) ||
                e.getTourney_name().equals(searchBox.getText()) ||
                e.getSurface().equals(searchBox.getText()) || e.getDraw_size().equals(searchBox.getText())||
                e.getTourney_date().equals(searchBox.getText()) || e.getTourney_date().equals(searchBox.getText()) ||
                e.getTourney_level().equals(searchBox.getText()) || e.getMatch_num().equals(searchBox.getText()

                )).toList();

        observableList.addAll(tournamentList);

        System.out.println(observableList.size());

        tournamentTable.setItems(observableList);
    }


    public TextField tournamentSearch;

    public void loadTourneyData(ActionEvent event) {
    }

    public void loadTournament(ActionEvent event) {
    }

    public void handleAddEntry(ActionEvent event) {
    }

    public void handleRemoveEntry(ActionEvent event) {
    }


    public void handleCommitAddProject(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
        PlayerLoader playerLoader = new PlayerLoader();
    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) throws IOException {
        Platform.exit();
        event.consume();
        PlayerLoader playerLoader = new PlayerLoader();
    }

    public void handleClearSearchText(ActionEvent event) throws IOException {
        searchBox.setText("");
        event.consume();
    }

    public void switchTournamentData(ActionEvent event) {
    }

    public void switchPlayerData(ActionEvent event) {
    }

    @FXML
    public void handleButtonTest(ActionEvent event) {
    }
}
