package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.MatchDao;
import com.example.cs195tennis.Dao.ShotTypeDao;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.ShotType;
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

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShotTypeController implements Initializable {

    public TextField searchBox;
    @FXML public TableColumn<String, ShotType> match_idCol,playerCol,rowCol,shotsCol,pt_endingCol,winnersCol,induced_forcedCol,serve_returnCol,unforcedCol,shots_in_pts_wonCol,shots_in_pts_lostCol;

    ObservableList<ShotType> shotTypeObservableList = FXCollections.observableArrayList();

    public List<ShotType> shotTypeList;

    @FXML public TableView<ShotType> shotTypeTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            shotTypeList = ShotTypeDao.readShotTypesCsv();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        shotTypeTable.getSelectionModel().selectedIndexProperty().addListener((
                e -> {
                    int index = shotTypeTable.getSelectionModel().selectedIndexProperty().get();
                    System.out.println("\nshotTypeList.get("+index+") = " + shotTypeList.get(index));
                    shotTypeWindow(shotTypeList.get(index), shotTypeList);
                }
        ));


        System.out.println(shotTypeList.size());

        shotTypeList.forEach(e-> shotTypeObservableList.add(
                new ShotType(e.getMatch_id(), e.getPlayer(), e.getRow(),e.getShots(), e.getPt_ending(),
                        e.getWinners(), e.getInduced_forced(), e.getUnforced(),e.getServe_return(),e.getShots_in_pts_won(),e.getShots_in_pts_lost()
                )));

        match_idCol.setCellValueFactory(new PropertyValueFactory<>("match_id"));
        playerCol.setCellValueFactory(new PropertyValueFactory<>("player"));
        rowCol.setCellValueFactory(new PropertyValueFactory<>("row"));
        shotsCol.setCellValueFactory(new PropertyValueFactory<>("shots"));
        pt_endingCol.setCellValueFactory(new PropertyValueFactory<>("pt_ending"));
        winnersCol.setCellValueFactory(new PropertyValueFactory<>("winners"));
        induced_forcedCol.setCellValueFactory(new PropertyValueFactory<>("induced_forced"));
        serve_returnCol.setCellValueFactory(new PropertyValueFactory<>("unforced"));
        unforcedCol.setCellValueFactory(new PropertyValueFactory<>("serve_return"));
        shots_in_pts_wonCol.setCellValueFactory(new PropertyValueFactory<>("shots_in_pts_won"));
        shots_in_pts_lostCol.setCellValueFactory(new PropertyValueFactory<>("shots_in_pts_lost"));

        shotTypeTable.setItems(shotTypeObservableList);
    }

    private List<ShotType> shotDirectionList() throws FileNotFoundException {
        return ShotTypeDao.getShotDirectionList();
    }
    private List<ShotType> shotDirOutcomeList() throws FileNotFoundException {
        return ShotTypeDao.getShotDirOutcomeList();
    }

    private void shotTypeWindow(ShotType shotType, List<ShotType> shotTypeList) {
        System.out.println(" Use match_id and selected player to create new table and filters for lists " +  shotType);
    }

    public void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

    public void handleGitButton(ActionEvent event) throws FileNotFoundException {
        List<ShotType> temp = new ArrayList<>(shotDirOutcomeList());

        shotTypeObservableList.addAll(temp);

        shotTypeTable.setItems(shotTypeObservableList);
    }

    public void handleSearch(ActionEvent event) {
    }

    public void handleClearSearchText(ActionEvent event) {
    }
}
