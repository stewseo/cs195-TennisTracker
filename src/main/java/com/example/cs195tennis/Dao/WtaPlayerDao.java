package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Player;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.WtaPlayer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT1;


public class WtaPlayerDao {

    private static DSLContext create() {
        try(Connection conn = Database.connect()) {

            return DSL.using(conn, SQLDialect.SQLITE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ObservableList<PlayerRanking> allTimeWtaRankings(String query) {

        ObservableList<PlayerRanking> playerObservableList = FXCollections.observableArrayList();

        try (Connection connection = Database.connect()) {
            ResultSet rs = connection.prepareStatement(query).executeQuery();

            while (rs.next()) {
                System.out.println("\nwta player");
                playerObservableList.add(new PlayerRanking(
                        rs.getString("ranking_date"),
                        rs.getString("rank"),
                        rs.getString("player"),
                        rs.getString("points")
                ));
            }
            } catch (SQLException e) {
                e.printStackTrace();

            }return playerObservableList;
        }

//    public void insert(String csv) throws SQLException, IOException, CsvValidationException {
//
//        List<File> filesInFolder = Files.walk(Paths.get("C:\\Users\\seost\\Downloads\\tennis_slam_pointbypoint-master\\tennis_slam_pointbypoint-master\\"))
//                .filter(Files::isRegularFile)
//                .map(Path::toFile).collect(Collectors.toList());
//
//        filesInFolder.forEach(System.out::println);
//
//        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();
//
//
//        List<String[]> csvList = new ArrayList<>();
//
//        String[] values = null;
//
//        int i = 0;


}





