package com.example.cs195tennis.controller;

import com.example.cs195tennis.database.DbController;
import com.example.cs195tennis.model.TournamentStats;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TournamentListController implements Initializable {

    ObservableList<TournamentStats> list = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private TableView<TournamentStats> tableView;
    @FXML
    private TableColumn<TournamentStats, String> loserNameCol;
    @FXML
    private TableColumn<TournamentStats, String> idCol;
    @FXML
    private TableColumn<TournamentStats, String> ROUNDCol;
    @FXML
    private TableColumn<TournamentStats, String> winner_iocCol;
    @FXML
    private TableColumn<TournamentStats, String> winnerNameCol;
    @FXML
    private AnchorPane contentPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {


        initCol();
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }


    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        loserNameCol.setCellValueFactory(new PropertyValueFactory<>("loser_name"));
        winnerNameCol.setCellValueFactory(new PropertyValueFactory<>("tourney_winner"));
        ROUNDCol.setCellValueFactory(new PropertyValueFactory<>("ROUND"));
        winner_iocCol.setCellValueFactory(new PropertyValueFactory<>("winner_ioc"));
    }

    private void loadData() throws SQLException {

        DbController myHandler = new DbController();

        String qu = "SELECT * FROM wta_matches_1990_to_2022";

        ResultSet rs = myHandler.execQuery("USA");

        try {
            while (rs.next()) {
                System.out.println(rs);
                var resultId = rs.getString("Source.name");
                var resultRound = rs.getString("ROUND");
                var resultLoc = rs.getString("winner_ioc");
                var resultLoserName = rs.getString("loser_name");
                var resultWinnerName = rs.getString("winner_name");
                list.add( new TournamentStats(resultId, resultWinnerName, resultRound, resultLoc, resultLoserName));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TournamentListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableView.setItems(list);
    }

    @FXML
    private void handleBookDeleteOption(ActionEvent event) throws SQLException, ParserConfigurationException, IOException, SAXException {
        TournamentStats selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleTournamentEditOption(ActionEvent event) {

        TournamentStats selectedForEdit = tableView.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/assistant/ui/addbook/add_book.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.show();
//            TournamentUtil.setStageIcon(stage);

            stage.setOnHiding((e) -> {
                try {
                    handleRefresh(new ActionEvent());
                } catch (SQLException | ParserConfigurationException | IOException | SAXException ex) {
                    ex.printStackTrace();
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) throws SQLException, ParserConfigurationException, IOException, SAXException {
        loadData();
    }

    @FXML
    private void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"   Name   ", "Winner", "  Loser  ", "  Score ", "Rounds"};
        printData.add(Arrays.asList(headers));
        for (TournamentStats tournament : list) {
            List<String> row = new ArrayList<>();
            row.add(tournament.getLoser_name());
            row.add(tournament.getId());
            row.add(tournament.getWinner_ioc());
            row.add(tournament.getTOURNEY_DATE());
            row.add(tournament.getROUND());
            printData.add(row);
        }
    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }


}
