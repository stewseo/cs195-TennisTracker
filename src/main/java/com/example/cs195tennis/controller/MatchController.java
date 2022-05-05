package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.model.Match;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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


        try {
            matchList = MatchDao.writeAllAtpMatchesToList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        matchTable.getSelectionModel().selectedIndexProperty().addListener((
                e -> {
                    Object object =  matchTable.getSelectionModel().selectedItemProperty().get();
                    int index = matchTable.getSelectionModel().selectedIndexProperty().get();
                    System.out.println("\nmatchList.get("+index+") = " + matchList.get(index));

                    matchWindow(matchList.get(index), matchList);
                }
        ));


        System.out.println(matchList.size());

//        matchList.forEach(e-> matchObservableList.add(
//                new Match(e.getWinner_id(), e.getWinner_seed(), e.getWinner_entry(),e.getWinner_name(), e.getWinner_hand(),
//                        e.getWinner_ht(), e.getWinner_ioc(), e.getWinner_age()
//                )));

        winner_idCol.setCellValueFactory(new PropertyValueFactory<>("winner_id"));
        winner_seedCol.setCellValueFactory(new PropertyValueFactory<>("winner_seed"));
        winner_entryCol.setCellValueFactory(new PropertyValueFactory<>("winner_entry"));
        winner_nameCol.setCellValueFactory(new PropertyValueFactory<>("winner_name"));
        winner_handCol.setCellValueFactory(new PropertyValueFactory<>("winner_hand"));
        winner_htCol.setCellValueFactory(new PropertyValueFactory<>("winner_ht"));
        winner_iocCol.setCellValueFactory(new PropertyValueFactory<>("winner_ioc"));
        winner_ageCol.setCellValueFactory(new PropertyValueFactory<>("winner_age"));

        matchTable.setItems(matchObservableList);
    }

    private void matchWindow(Match match, List<Match> matchList) {
        System.out.println("\nUse winner_id and name for MatchStats data: " + match + " to GridPane -> HBox -> Root Pane");
    }

    @FXML
    public void clickItem(MouseEvent event) {

        if (event.getClickCount() == 2) {
            System.out.println(matchTable.getSelectionModel().getSelectedItem().getWinner_id());
            System.out.println(matchTable.getSelectionModel().getSelectedItem().getWinner_seed());
            System.out.println(matchTable.getSelectionModel().getSelectedItem().getWinner_entry());
        }

    }

    public void handleMatchAdd(ActionEvent event) {

    }

    public void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

    public void handleSearch(ActionEvent event) {
        matchObservableList.clear();



        matchList = matchList.stream().filter(e -> e.getWinner_id().equals(searchBox.getText()) ||
                e.getWinner_seed().equals(searchBox.getText()) ||
                e.getWinner_entry().equals(searchBox.getText()) || e.getWinner_name().equals(searchBox.getText()) ||
                e.getWinner_hand().equals(searchBox.getText())
        ).toList();

        matchObservableList.addAll(matchList);


        System.out.println(matchObservableList.size());
        matchTable.setItems(matchObservableList);
    }

    public void handleClearSearchText(ActionEvent event) {
    }
}
