package com.example.cs195tennis.controller;

import com.example.cs195tennis.ToolbarController;
import com.example.cs195tennis.TournamentListLoader;
import com.example.cs195tennis.database.DbController;
import com.example.cs195tennis.model.TournamentStats;
import com.jfoenix.controls.JFXDrawer;

import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import com.jfoenix.controls.JFXHamburger;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;


public class MainController implements Initializable {

    public StackPane tournamentContainer;
    public Text tournamentNameHolder;
    public Text tournamentDataHolder2;
    public Text tournamentDataHolder3;
    public Text issueDateHolder;
    public Text fineInfoHolder;
    @FXML TextField tournamentID;
    public JFXButton renewButton;
    public JFXButton submissionButton;
    @FXML
    StackPane rootPane;
    @FXML
    AnchorPane rootAnchorPane;
    @FXML
    JFXDrawer drawer;
    @FXML
    TabPane mainTabPane;
    @FXML
    Tab tournamentTab;
    @FXML
    Tab playerTab;
    @FXML
    HBox tournamentInfo;
    @FXML
    HBox matchInfo;
    @FXML
    TextField matchNumberInput;
    @FXML
    StackPane matchContainer;
    @FXML
    Text matchName;
    @FXML
    Text matchInfoText;
    @FXML
    HBox dataHolderContainer;
    @FXML
    Text playerDataHolder1;
    @FXML
    Text playerDataHolder2;
    @FXML
    Text playerDataHolder3;
    @FXML
    Text numberDaysHolder;
    @FXML
    JFXHamburger hamburger;
    @FXML
    JFXButton Search;

    @FXML
    TextField tournamentInput;
    @FXML
    Text tournamentType;
    @FXML
    Text tournamentDate;
    @FXML
    Text tournamentName;

    @FXML
    private ListView<TournamentStats> listView;
    private ObservableList<TournamentStats> tournamentStatsObservableList;


    public MainController() {}



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DbController db = new DbController();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initDrawer();
//        initGraphs();
        mainTabPane.tabMinWidthProperty().bind(rootAnchorPane.widthProperty().divide(mainTabPane.getTabs().size()).subtract(15));

    }

    @FXML
    PieChart bookChart;
    @FXML
    PieChart memberChart;

    private void initGraphs() {

//            bookChart = new PieChart(DbController.getStatistics());
//            memberChart = new PieChart(DbController.getStatistics());
//            tournamentContainer.getChildren().add(bookChart);
//            matchContainer.getChildren().add(memberChart);

//            tournamentTab.setOnSelectionChanged((Event event) -> {
//
//            });
        }



    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void handleMenuClose(ActionEvent event) {
        getStage().close();
    }



    private void initDrawer() {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("toolbar.fxml"));
//        taskBtn.setGraphic(createGraphicTitle(task));
//        VBox toolbar = new VBox();
//        toolbar.getStyleClass().add("toolbar-container");
//        JFXButton jfxButton = new JFXButton();
//        jfxButton.getStyleClass().add("toolbar-button");

//        scene.getStylesheets().add("application.css");

//        ToolbarController toolController = new ToolbarController();
//        toolbar.getChildren().add(jfxButton);

//        drawer.setSidePane(toolbar);

        System.out.println("test");


        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
    }


    public void handleMenuSettings(ActionEvent actionEvent) {
    }

    public void handleMenuTournament(ActionEvent actionEvent) {
    }

    public void handleMenumatch(ActionEvent actionEvent) {
    }

    public void handleMenuViewBook(ActionEvent actionEvent) {
    }

    public void handleMenuViewMemberList(ActionEvent actionEvent) {
    }

    public void handleIssuedList(ActionEvent actionEvent) {
    }

    public void handleMenuFullScreen(ActionEvent actionEvent) {
    }

    public void handleMenuOverdueNotification(ActionEvent actionEvent) {
    }

    public void handleAboutMenu(ActionEvent actionEvent) {
    }

    public void handleButtons(KeyEvent keyEvent) {
    }

    public void searchBuilder(ActionEvent actionEvent) {
    }

    public void loadMatchInfo(ActionEvent actionEvent) {
    }

    @FXML
    public void loadTournamentData(ActionEvent actionEvent) throws SQLException, IOException {
        String s = tournamentInput.getText();
        TournamentListLoader Tloadder = new TournamentListLoader(s);
        tournamentInput.getStyleClass().add("searchField");
    }

    public void handleMenuMatchData(ActionEvent actionEvent) {
    }
}


//        FXMLLoader loader = new FXMLLoader(getClass().getResource("tournament_list.fxml"));
//        VBox toolbar = loader.load();
//        drawer.setSidePane(toolbar);


//
//        DbController db = new DbController();
//        db.execQuery(s);




//        Glyph glyph = new Glyph("fontFamily", "TOURNAMENT");
//        glyph.setStyle("-fx-text-fill: primary");

