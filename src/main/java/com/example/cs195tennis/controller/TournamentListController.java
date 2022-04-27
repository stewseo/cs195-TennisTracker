package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Rankings;
import com.example.cs195tennis.model.Rankings;
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


    ObservableList<Rankings> observableList = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private TableView<Rankings> tableView;
    @FXML
    private TableColumn<Rankings, String> ranking_dateCol;
    @FXML
    private TableColumn<Rankings, Integer> rankCol;
    @FXML
    private TableColumn<Rankings, String> pointsCol;
    @FXML
    private TableColumn<Rankings, String> idCol;
    @FXML
    private TableColumn<Rankings, String> playerCol;
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

    String inputDate;

    public void loadData() throws SQLException {
        String qu;
        DatabaseConnection dbConnection = new DatabaseConnection();

        List<Rankings> list = PlayerDao.getRankingList();

        list.forEach(e-> observableList.add(
                new Rankings(e.getRanking_date(), e.getRank(), e.getPlayer(),
                        e.getPoints())));

        ResultSet rs = dbConnection.execQuery("qu");



        TournamentDao tournamentDao = new TournamentDao();

        List<ResultSet> resultList = new ArrayList<>();
        try {
            while (rs.next()) {
                resultList.add(rs);
                String resultDate = rs.getString("ranking_date");
                String resultRank = rs.getString("rank");
                String resultPlayer = rs.getString("player");
                String resultPoints = rs.getString("points");
                String resultId = rs.getString("id");
                observableList.add(new Rankings(resultDate, resultRank, resultPlayer, resultPoints));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TournamentListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(observableList);
        resultList.forEach(e->System.out.println(e.toString()));
    }

    public TextField tournamentSearch;

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }


    private void initCol() {
        ranking_dateCol.setCellValueFactory(new PropertyValueFactory<>("ranking_date"));
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
        playerCol.setCellValueFactory(new PropertyValueFactory<>("player"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

//
//    @FXML
//    private void handleBookDeleteOption(ActionEvent event) throws SQLException, ParserConfigurationException, IOException, SAXException {
//        Rankings selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
//    }

//    @FXML
//    private void handleTournamentEditOption(ActionEvent event) {
//        Rankings selectedForEdit = tableView.getSelectionModel().getSelectedItem();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("tournament_list.fxml"));
//            Parent parent = loader.load();
//
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setScene(new Scene(parent));
//            stage.show();
//
//            stage.setOnHiding((e) -> {
//                try {
//                    handleRefresh(new ActionEvent());
//                } catch (SQLException | ParserConfigurationException | IOException | SAXException ex) {
//                    ex.printStackTrace();
//                }
//            });
//
//        } catch (IOException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void handleRefresh(ActionEvent event) throws SQLException, ParserConfigurationException, IOException, SAXException {
        loadData();
    }

//    @FXML
//    private void exportAsPDF(ActionEvent event) {
//        List<List> printData = new ArrayList<>();
//        String[] headers = {"   Name   ", "Winner", "  Loser  ", "  Score ", "Rounds"};
//        printData.add(Arrays.asList(headers));
//        for (PlayerRank currentRanking : observableList) {
//            List<String> row = new ArrayList<>();
//            row.add(currentRanking.getRanking_date());
//            row.add(currentRanking.getRank());
//            row.add(currentRanking.getPlayer());
//            row.add(currentRanking.getPoints());
//            printData.add(row);
//        }
//    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }


    public void loadTourneyData(ActionEvent event) {
    }

    public void loadTournament(ActionEvent event) {
    }
}
