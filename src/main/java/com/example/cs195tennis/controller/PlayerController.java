package com.example.cs195tennis.controller;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.model.Player;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PlayerController implements Initializable {

    @FXML public TableView<Player> playerTable = new TableView<>();

    @FXML
    public TableColumn<Player,String> heightCol,tourney_dateCol,countryCol, wikiCol;

    @FXML
    private TableColumn<Player, String>  ioc_col, df_Col, srv_gm_wonCol, bp_svd_Col, srv_pts_wonCol, first_srvCol, dobCol, handCol, firstNameCol,second_srvCol, idCol, lastNameCol;

    @FXML
    private TableView<Player> table = new TableView<>();

    public TextField playerSearchTextField;

    public ObservableList<Player> playerObservableList =  FXCollections.observableArrayList();
    public Map<String, List<Player>> playerMap = new HashMap<>();
    private List<Player> playerCache;

    @FXML
    private TextField searchBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        playerTable.getSelectionModel().selectedIndexProperty().addListener((
                e -> {
                    Player player =  playerTable.getSelectionModel().selectedItemProperty().get();
                    int index = playerTable.getSelectionModel().selectedIndexProperty().get();
                    playerWindow(player);
                }
        ));

        playerMap = PlayerDao.getPlayerMap();

        System.out.println(playerMap.size());

        playerMap.forEach((k, v) -> v.forEach(pid-> playerObservableList.add(
                new Player(pid.getId(), pid.getFirstName(), pid.getLastName(), pid.getHand(), pid.getDob(), pid.getIoc(), pid.getHeight(), pid.getWiki(), pid.getRank()
                ))));

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        handCol.setCellValueFactory(new PropertyValueFactory<>("hand"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        ioc_col.setCellValueFactory(new PropertyValueFactory<>("ioc"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        wikiCol.setCellValueFactory(new PropertyValueFactory<>("wiki"));

        playerTable.setItems(playerObservableList);
    }


    private void playerWindow(Player player) {
        System.out.println("\nInsert Player: " + player + " to GridPane -> HBox -> Root Pane");

    }

    @FXML
    public void loadPlayerData(ActionEvent event) {

        String qu = " WHERE " + event.getSource().toString() + " = " + playerSearchTextField.getText();
        playerMap = PlayerDao.getPlayerMap();

        String input = playerSearchTextField.getText();

        playerMap.forEach((k, v) -> v.forEach(pid-> playerObservableList.add(
                new Player(pid.getId(), pid.getFirstName(), pid.getLastName(), pid.getHand(), pid.getDob(), pid.getIoc(), pid.getHeight(), pid.getWiki(), pid.getRank()
                ))));

        table.setItems(playerObservableList);
    }

    @FXML
    public void handleAddButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayerDataScene2.fxml")));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #81c483");
        scene.setFill(Color.web("#81c483"));
        stage.setScene(scene);
        stage.show();
    }

    public void handleClearSearchText(ActionEvent event) {
    }

    @FXML
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

    @FXML
    public void handleSearch(ActionEvent event) {

        List<Predicate<Player>> allPredicates = Arrays.asList(
                        e -> e.getLastName().equals(searchBox.getText()),
                        e -> e.getFirstName().equals(searchBox.getText())
                );

        Comparator<Player> byfullName = Comparator.comparing(e-> e.getFirstName() + "_" + e.getLastName());

        Comparator<Player> byPlayer = Comparator.comparing(Player::getId).thenComparing(Player::getFirstName)
                .thenComparing(Player::getLastName)
                .thenComparing(Player::getDob);

        playerObservableList.clear();

        Player player = new Player(searchBox.getText());

        Map<String, List<Player>> filteredMap = playerMap;
        System.out.println(filteredMap);
        AtomicReference<String> id = new AtomicReference<>(searchBox.getText());

        Map<String, List<Player>> playerId = new HashMap<>();

        filteredMap.forEach((k, v) -> {

                    v.stream()
                    .filter(e -> e.getFirstName().equals(id.toString()) || e.getLastName().equals(id.toString())).forEach(result -> {
                        id.set(result.getId());
                        playerId.computeIfAbsent(result.getId(), idList -> new ArrayList<>());
                        playerId.get(result.getId()).add(result);
                    });
        });


        playerObservableList.addAll(playerId.get(id.toString()));
//
        table.setItems(playerObservableList);
    }

    public void switchTournamentData(ActionEvent event) {
    }

    public void switchPlayerData2(ActionEvent event) {
    }
}
