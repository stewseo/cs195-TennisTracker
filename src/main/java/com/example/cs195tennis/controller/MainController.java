package com.example.cs195tennis.controller;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player, String> name;

    @FXML
    private TableColumn<Player, Integer> rank;

    @FXML
    private TableColumn<Player, Integer> ace;

    ObservableList<Player> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        if(table != null) {
            name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            rank.setCellValueFactory(new PropertyValueFactory<Player, Integer>("rank"));
            ace.setCellValueFactory(new PropertyValueFactory<Player, Integer>("ace"));
            table.setItems(list);
        }

    }

    @FXML
    public void switchMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainScene2.fxml"));

        // this line gets the Stage information
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // creates a new scene to the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchPlayerData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PlayerDataScene2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchTournamentData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tournament.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchPlayerData2(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayerDataScene_2.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
    @FXML
    public void switchTournamentScene_2(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TournamentScene_2.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }


}
