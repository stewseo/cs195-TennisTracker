package com.example.cs195tennis;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;


public class App extends Application {
    private final static Logger LOGGER = LogManager.getLogger(App.class.getName());

    @Override
    public void start(Stage stage) throws SQLException {

            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayerDataScene.fxml")));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO,"startTime " +  startTime);

        launch(args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        long exitTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO,"exitTime " +  exitTime);
        }));
    }
}

