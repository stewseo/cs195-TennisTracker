package com.test.viewercontrollertest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage){
        try {
            System.out.println("1");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Viewer.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
            System.out.println("2");
            primaryStage.setScene(scene);
            System.out.println("2");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {launch(args);}
}