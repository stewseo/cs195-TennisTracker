package com.example.cs195tennis.controller;

import com.example.cs195tennis.model.TournamentStats;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {

    public StackPane tournamentContainer;
    public Text tournamentNameHolder;
    public Text tournamentDataHolder2;
    public Text tournamentDataHolder3;
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

        mainTabPane.tabMinWidthProperty().bind(rootAnchorPane.widthProperty().divide(mainTabPane.getTabs().size()).subtract(15));
    }


    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void handleMenuClose(ActionEvent event) {
        getStage().close();
    }


    private void initDrawer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void handleMenuAddBook(ActionEvent actionEvent) {
    }

    public void handleMenuAddMember(ActionEvent actionEvent) {
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

    public void loadTournamentData(ActionEvent actionEvent) {
    }
}