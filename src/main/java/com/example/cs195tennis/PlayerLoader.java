package com.example.cs195tennis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.application.Application.launch;

public class PlayerLoader extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = new Stage();
        window.setTitle("New Scene");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Container.fxml")));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());


        window.show();
    }
}
