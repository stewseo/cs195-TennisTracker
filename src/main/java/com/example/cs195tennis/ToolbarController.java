package com.example.cs195tennis;

import com.example.cs195tennis.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;


public class ToolbarController implements Initializable {

    @FXML
    private void loadAddMatch(ActionEvent event) {}

    @FXML
    private void loadAddTournament(ActionEvent event) {}

//    @FXML
//    private void loadMatchTable(ActionEvent event) {
//        loadWindow(getClass().getResource(""), "Match List", null);
//    }
//
//    @FXML
//    private void loadTournamentTable(ActionEvent event) {
//        loadWindow(getClass().getResource(""), "Tournament List", null);
//    }


    public static final String ICON_LOC = "";

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_LOC));
    }
//
//    public void loadWindow(URL loc, String title, Stage parentStage) {
//        Object controller = null;
//        try {
//            FXMLLoader loader = new FXMLLoader((getClass().getResource("toolbar.fxml")));
//            Parent parent = loader.load();
//            controller = loader.getController();
//            Stage stage = null;
//            if (parentStage != null) {
//                stage = parentStage;
//            } else {
//                stage = new Stage(StageStyle.DECORATED);
//            }
//            stage.setTitle(title);
//            stage.setScene(new Scene(parent));
//            stage.show();
//            setStageIcon(stage);
//        } catch (IOException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadAddMatchData(ActionEvent actionEvent) {
    }

    public void loadAddTournamentList(ActionEvent actionEvent) {
    }

    public void loadAddMatchDataPointsList(ActionEvent actionEvent) {
    }
}
