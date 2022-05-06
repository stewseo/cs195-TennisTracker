package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.model.Match;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchController implements Initializable {

    public TextField searchBox;

    ObservableList<Match> matchObservableList = FXCollections.observableArrayList();

    public List<Match> matchList;

    @FXML public TableView<Match> matchTable;

    @FXML public TableColumn<Match, String> winner_idCol, winner_seedCol, winner_entryCol, winner_nameCol, winner_handCol, winner_htCol, winner_iocCol, winner_ageCol;
    Multimap<String, Match> matchMap = ArrayListMultimap.create();


    @Override
    public void initialize(URL url, ResourceBundle rb) {


        try {
            matchMap = MatchDao.readAtpMatchToMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        matchTable.getSelectionModel().selectedIndexProperty().addListener((
                e -> {

                    Match selectedMatch =  matchTable.getSelectionModel().selectedItemProperty().get();

                    int rowNumber = matchTable.getSelectionModel().selectedIndexProperty().get();

                    matchWindow(rowNumber, selectedMatch);
                }
        ));


        System.out.println(matchMap.size());

        matchMap.forEach((k, v) ->
                matchObservableList.add(new Match(v.getWinner_id(), v.getWinner_seed(), v.getWinner_entry(), v.getWinner_name(), v.getWinner_hand(),
                        v.getWinner_ht(), v.getWinner_ioc(), v.getWinner_age()
                )));

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
    //provide stat catgeory button click a match -> button for winner -> map winner_id of that match -> value: ShotType constructor using match_id and winner_id
    //to GridPane -> HBox -> Root Pane
    private void matchWindow(int rowNumber, Match selectedMatch) {

        String selectedId = selectedMatch.getWinner_id();

        List<Match> value = matchMap.get(selectedId).stream().toList();

        AtomicInteger i = new AtomicInteger(1);

        System.out.println("\nClicked Row Number : " + rowNumber);

        matchMap.get(selectedId).forEach(e-> {

            System.out.println("\nIndex:" + (i.getAndIncrement()) +
                    "\nwinner id " + e.getWinner_id()
                    + "\nLoser id " + e.getLoser_id()
                    + "\nTourney Id " + e.getTourney_id()
            );
        });

        String winnerAces = selectedMatch.getW_ace();

        System.out.print("\n\nAll fields accessible from selected row: " + value);

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
