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
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchController implements Initializable {

    public TextField searchBox;

    ObservableList<Match> matchObservableList = FXCollections.observableArrayList();

    public List<Match> matchList;

    @FXML public TableView<Match> matchTable;

    @FXML public TableColumn<Match, String> winner_idCol, winner_seedCol, winner_entryCol, winner_nameCol, winner_handCol, winner_htCol, winner_iocCol, winner_ageCol;

    Map<String, List<Match>> matchMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        try {
            matchMap = MatchDao.readAtpMatchToMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Map<String, Collection<Match>> map = matchMap.asMap();

        matchTable.getSelectionModel().selectedIndexProperty().addListener((
                e -> {

                    Match selectedMatch =  matchTable.getSelectionModel().selectedItemProperty().get();

                    int rowNumber = matchTable.getSelectionModel().selectedIndexProperty().get();

                    matchWindow(rowNumber, selectedMatch);
                }
        ));


        System.out.println(matchMap.size());
        //we have a model initialized by each row in the data access class. We use the tourney_id and tourney_name as the key, to all 50 columns.
        //Is there a point create a new model here?

        matchMap.forEach((k, v) -> v.forEach(e ->

                matchObservableList.add(new Match(e.getWinner_id(), e.getWinner_seed(), e.getWinner_entry(), e.getWinner_name(), e.getWinner_hand(),
                        e.getWinner_ht(), e.getWinner_ioc(), e.getWinner_age()
                ))));

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

        List<String[]> keysForNextQuery = new ArrayList<>();

        List<String> queryString = new ArrayList<>();

        matchMap.get(selectedId).forEach(e-> {

            keysForNextQuery.add(new String[]{
                    e.getTourney_id(), "-m-", e.getTourney_name(), e.getWinner_name(), e.getLoser_name(),
            });

            queryString.add(e.getTourney_date()+"-F-"+e.getTourney_name() +"-"+ e.getWinner_name() +"-"+ e.getLoser_name());

            System.out.println("\nIndex:" + (i.getAndIncrement()) +
                    "\nwinner id " + e.getWinner_id()
                    +"\nLoser id " + e.getLoser_id()
                    +"\nTourney Id " + e.getTourney_id()
            );
        });

//        keysForNextQuery.forEach(e->System.out.println(Arrays.toString(e)));

        queryString.forEach(System.out::println);

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
