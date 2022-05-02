package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.Rankings;
import com.example.cs195tennis.model.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    @FXML
    public void handleSearch(ActionEvent event) {

        observableList.clear();

        tournamentList.stream().filter(e -> e.getTourney_id().equals(searchBox.getText()) ||
                e.getTourney_name().equals(searchBox.getText()) ||
                e.getSurface().equals(searchBox.getText()) || e.getDraw_size().equals(searchBox.getText())||
                e.getTourney_date().equals(searchBox.getText()) || e.getTourney_date().equals(searchBox.getText()) ||
                e.getTourney_level().equals(searchBox.getText()) || e.getMatch_num().equals(searchBox.getText()

                ))
                .forEach(observableList::add);

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


    public void handleTournamentAdd(ActionEvent event) {
    }

    public void handleExitButtonClicked(ActionEvent event) {
    }

    public void handleClearSearchText(ActionEvent event) {
    }
}
