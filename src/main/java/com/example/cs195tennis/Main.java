package com.example.cs195tennis;

import com.example.cs195tennis.controller.MainController;
import fr.brouillard.oss.cssfx.CSSFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private double x, y;



        @Override
        public void start(Stage primaryStage) throws IOException {
            CSSFX.start(); //monitor css for the given window and child nodes of that window
            FXMLLoader loader = new FXMLLoader(MFXDemoResourcesLoader.loadURL("Demo.fxml"));
            loader.setControllerFactory(c -> new MainController(primaryStage)); //dependency injection from fxml to controller
            Parent root = loader.load(); //rppt pf sceme gra[h
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT); //The root node of the Scene is given the CSS style class 'root',
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.setTitle("CS 195 Tennis Stats");
            primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }

}
