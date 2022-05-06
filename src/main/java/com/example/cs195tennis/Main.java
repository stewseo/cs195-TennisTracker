package com.example.cs195tennis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

        @Override
        public void start(Stage stage) {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScene2.fxml")));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    public static void main(String[] args) {
        launch(args);
    }


}