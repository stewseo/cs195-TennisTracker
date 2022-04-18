package com.example.cs195tennis;
import com.example.cs195tennis.controller.TournamentListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class TournamentListLoader extends Application {

    public TournamentListLoader(String s) throws IOException {
        TournamentListController tournamentListController = new TournamentListController();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tournament_list.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws SQLException, IOException {
    }


    public static void main(String[] args) {
        launch(args);
    }

}
