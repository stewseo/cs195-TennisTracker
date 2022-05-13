package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DataHandeler;
import com.example.cs195tennis.model.*;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.jooq.tools.csv.CSVReader;

import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

import static java.util.Collections.addAll;


public class AtpMatchDao {

    AtpMatchDao() {
    }
    static List<Match> matchList;

    public static ObservableList<Match> matches = FXCollections.observableArrayList();


    static String grandPrix = "C:\\Users\\seost\\Downloads\\tennis_slam_pointbypoint-master\\tennis_slam_pointbypoint-master\\";


    public static List<File> getFilesFromFolder(String pathToFolder) throws IOException {

        return Files.walk(Paths.get(grandPrix))
                .filter(Files::isRegularFile)
                .map(Path::toFile).toList();

    }


    public Result<Record> fetchRowsFromCsv(String string, boolean header, char delimiter) {

        CSVReader reader = new CSVReader(new StringReader(string), delimiter);
        List<String[]> list = null;
        try {
            list = reader.readAll();
        } catch (IOException e) {
            throw new DataAccessException("Could not read the CSV string", e);
        } finally {
            try {
                reader.close();
            } catch (IOException ignore) {
            }
        }
        return fetchRowsFromCsv(list, header);
    }

    private Result<Record> fetchRowsFromCsv(List<String[]> list, boolean header) {
        return null;
    }


    public static void main(String[] args) throws SQLException, IOException, CsvValidationException {

        int valid = grandPrix.codePointAt(grandPrix.length()-1);

    }


    public static ObservableList<Match> getSeasonTotal() {
        ObservableList<Match> seasonTotal = FXCollections.observableArrayList();
        return seasonTotal;
    }


}














