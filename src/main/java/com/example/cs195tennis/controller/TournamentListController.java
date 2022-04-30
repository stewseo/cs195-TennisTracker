package com.example.cs195tennis.controller;

import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.Dao.PlayerDao;
import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Rankings;
import com.example.cs195tennis.model.Tournament;
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

    public TextField tourneyInput;
    public HBox tournamentInfo;

    ObservableList<Rankings> observableList = FXCollections.observableArrayList();


    @FXML
    private StackPane rootPane;
    @FXML
    private TableView<Rankings> tableView;
    @FXML private TableColumn<Tournament, String> tourney_idCol,tourney_nameCol,tourney_dateCol,winner_nameCol,loser_nameCol,ranking_dateCol,rankCol,pointsCol,idCol;

    @FXML
    private TableColumn<Rankings, String> playerCol;

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

        tourney_nameCol.setCellValueFactory(new PropertyValueFactory<>("tourney_name"));
        tourney_dateCol.setCellValueFactory(new PropertyValueFactory<>("tourney_date"));
        winner_nameCol.setCellValueFactory(new PropertyValueFactory<>("winner_name"));
        loser_nameCol.setCellValueFactory(new PropertyValueFactory<>("loser_name"));
        tourney_idCol.setCellValueFactory(new PropertyValueFactory<>("tourney_id"));
        ranking_dateCol.setCellValueFactory(new PropertyValueFactory<>("ranking_date"));
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
        playerCol.setCellValueFactory(new PropertyValueFactory<>("player"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    }

    @FXML
    private void handleBookDeleteOption(ActionEvent event) throws SQLException, ParserConfigurationException, IOException, SAXException {
        Tournament selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleTournamentEditOption(ActionEvent event) {
        Tournament selectedForEdit = tableView.getSelectionModel().getSelectedItem();
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
        for (Tournament tournament : observableList) {
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

    public void loadTournament(ActionEvent event) {
    }


    public void handleAddEntry(ActionEvent event) {
    }

    public void handleRemoveEntry(ActionEvent event) {
    }


}
