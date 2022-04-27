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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        window.setScene((new Scene(loader.load())));
        window.show();

    }
}
