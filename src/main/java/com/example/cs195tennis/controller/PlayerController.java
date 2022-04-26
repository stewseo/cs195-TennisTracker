package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.model.Player;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class PlayerController implements Initializable {

    @FXML
    private TableColumn<Player, String> df_Col, srv_gm_wonCol, bp_svd_Col, srv_pts_wonCol, first_srvCol, dobCol, handCol, firstNameCol,second_srvCol, idCol, lastNameCol;

    @FXML
    private TableView<Player> table = new TableView<>();

    public TextField playerSearchTextField;

    public ObservableList<Player> playerObservableList =  FXCollections.observableArrayList();
    public List<Player> playerList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){



//        PlayerDao.getTempList(qu,playerList);
//
//        playerList.forEach(e-> playerObservableList.add(
//                new Player(e.getWinner_name(), e.getWinner_rank(), e.getW_ace())));
//


//        table.setItems(playerObservableList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        handCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("hand"));

    }


    public void switchMainScene(ActionEvent event) throws IOException {
        System.out.println("Main");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchPlayerData(ActionEvent event) throws IOException {
        System.out.println("testPlayerData");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayerDataScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchTournamentData(ActionEvent event) throws IOException {
        System.out.println("testTournamentData");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TournamentScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchPlayerData2(ActionEvent event) throws IOException {
        System.out.println("TestPlayerData2");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayerDataScene2.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    public void csvToTable() {
        PlayerDao playerDao = new PlayerDao();
        playerDao.createTablePlayer("Players");

        try {
            playerDao.createPlayerTableFromCsv();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    public String searchFilters(String filter1, String filter2, String filter3) {
        //TODO: Filters for player ranking, tournament ranking, type match ranking searches

        return "";
    }

    @FXML
    public void loadPlayerData(ActionEvent event) {
        String qu = " WHERE " + event.getSource().toString() + " = " + playerSearchTextField.getText();

        playerList = new ArrayList<>();

        String input = playerSearchTextField.getText();

        playerList = PlayerDao.getTempList(input);

        playerList.parallelStream().forEach(e-> playerObservableList.add(
                new Player(e.getId(), e.getFirstName(), e.getLastName(),
                        e.getHand(),  e.getDob(), e.getIoc(), e.getHeight(), e.getWiki()
                )));

        table.setItems(playerObservableList);

    }
}
