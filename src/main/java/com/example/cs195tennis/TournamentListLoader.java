package com.example.cs195tennis;
import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.model.Tournament;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class TournamentListLoader extends Application {

    public TournamentListLoader() throws IOException, SQLException {

    }

    @Override
    public void start(Stage stage) throws SQLException, IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tournament.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
//        TournamentDao.createTableTournament();

        launch(args);
    }

}
