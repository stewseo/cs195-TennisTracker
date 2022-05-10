package com.example.cs195tennis;

import com.example.cs195tennis.controller.DemoController;
import fr.brouillard.oss.cssfx.CSSFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private double x, y;

        @Override
        public void start(Stage primaryStage) throws IOException {
            CSSFX.start();
            FXMLLoader loader = new FXMLLoader(MFXDemoResourcesLoader.loadURL("Demo.fxml"));
            loader.setControllerFactory(c -> new DemoController(primaryStage));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.setTitle("CS 195 Tennis Stats");
            primaryStage.show();

        }


    public static void main(String[] args) {
        launch(args);
    }


}
