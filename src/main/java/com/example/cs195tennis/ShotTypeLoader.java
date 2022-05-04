package com.example.cs195tennis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ShotTypeLoader extends Application {

    @Override
    public void start(Stage stage) throws SQLException, IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShotType.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


}
