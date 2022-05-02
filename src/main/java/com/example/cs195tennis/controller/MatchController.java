package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.model.Match;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MatchController implements Initializable {
    public TextField searchBox;
    ObservableList<Match> matchObservableList = FXCollections.observableArrayList();

    public List<Match> matchList;

    @FXML public TableView<Match> matchTable;

    @FXML public TableColumn<Match, String> winner_idCol, winner_seedCol, winner_entryCol, winner_nameCol, winner_handCol, winner_htCol, winner_iocCol, winner_ageCol;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        matchList = MatchDao.allMatchesCsvToTournamentList();

        System.out.println(matchList.size());

        matchList.forEach(e-> matchObservableList.add(
                new Match(e.getWinner_id(), e.getWinner_seed(), e.getWinner_entry(),e.getWinner_name(), e.getWinner_hand(),
                        e.getWinner_ht(), e.getWinner_ioc(), e.getWinner_age()
                )));

        winner_idCol.setCellValueFactory(new PropertyValueFactory<>("tourney_id"));
        winner_seedCol.setCellValueFactory(new PropertyValueFactory<>("tourney_name"));
        winner_entryCol.setCellValueFactory(new PropertyValueFactory<>("surface"));
        winner_nameCol.setCellValueFactory(new PropertyValueFactory<>("draw_size"));
        winner_handCol.setCellValueFactory(new PropertyValueFactory<>("tourney_date"));
        winner_htCol.setCellValueFactory(new PropertyValueFactory<>("tourney_level"));
        winner_iocCol.setCellValueFactory(new PropertyValueFactory<>("match_num"));
        winner_ageCol.setCellValueFactory(new PropertyValueFactory<>("winner_age"));

        matchTable.setItems(matchObservableList);
    }


    public void handleMatchAdd(ActionEvent event) {
    }

    public void handleExitButtonClicked(ActionEvent event) {
    }

    public void handleSearch(ActionEvent event) {
    }

    public void handleClearSearchText(ActionEvent event) {
    }
}
