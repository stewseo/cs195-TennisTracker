package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.TournamentStats;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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

    public HBox tourneyHBox;
    public TextField tourneyInput;
    public HBox tournamentInfo;

    ObservableList<TournamentStats> observableList = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private TableView<TournamentStats> tableView;
    @FXML
    private TableColumn<TournamentStats, String> tourney_idCol;
    @FXML
    private TableColumn<TournamentStats, Integer> tourney_nameCol;
    @FXML
    private TableColumn<TournamentStats, String> tourney_dateCol;
    @FXML
    private TableColumn<TournamentStats, String> winner_nameCol;
    @FXML
    private TableColumn<TournamentStats, String> loser_nameCol;
    @FXML
    private AnchorPane contentPane;
    public Stack<Node> stack;
    private Node next;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //        MainController main = new MainController();
//        main.getResult().forEach(System.out::println);
    String inputDate;

    public void loadData() throws SQLException {
        String qu = " WHERE tourney_date > 2022000000";
        DatabaseConnection dbConnection = new DatabaseConnection();
        ResultSet rs = dbConnection.execQuery(qu);
        TournamentDao tournamentDao = new TournamentDao();

        List<ResultSet> resultList = new ArrayList<>();
        try {
            while (rs.next()) {
                resultList.add(rs);
                String resultName = rs.getString("tourney_name");
                String resultDate = rs.getString("tourney_date");
                String resultWinnerName = rs.getString("winner_name");
                String resultLoserName = rs.getString("loser_name");
                String resultId = rs.getString("tourney_id");
                observableList.add(new TournamentStats(resultName, resultDate, resultWinnerName, resultLoserName, resultId));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TournamentListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(observableList);
        resultList.forEach(e->System.out.println(e.toString()));
    }


    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }


    private void initCol() {
        tourney_nameCol.setCellValueFactory(new PropertyValueFactory<>("tourney_name"));
        tourney_dateCol.setCellValueFactory(new PropertyValueFactory<>("tourney_date"));
        winner_nameCol.setCellValueFactory(new PropertyValueFactory<>("winner_name"));
        loser_nameCol.setCellValueFactory(new PropertyValueFactory<>("loser_name"));
        tourney_idCol.setCellValueFactory(new PropertyValueFactory<>("tourney_id"));
    }


    @FXML
    private void handleBookDeleteOption(ActionEvent event) throws SQLException, ParserConfigurationException, IOException, SAXException {
        TournamentStats selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleTournamentEditOption(ActionEvent event) {
        TournamentStats selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tournament_list.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.show();

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
        for (TournamentStats tournament : observableList) {
            List<String> row = new ArrayList<>();
            row.add(tournament.getTourney_name());
            row.add(tournament.getTourney_date());
            row.add(tournament.getWinner_name());
            row.add(tournament.getLoser_name());
            row.add(tournament.getTourney_id());
            printData.add(row);
        }
    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }


    public void loadTourneyData(ActionEvent event) {
    }

}
