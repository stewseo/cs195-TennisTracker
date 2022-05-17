package com.example.cs195tennis.Dao;

import com.example.cs195tennis.model.*;
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
import java.util.*;


public class AtpTourDao {

    AtpTourDao() {}

    public static ObservableList<Match> AtpTourObservable = FXCollections.observableArrayList();

    private List<File> getFilesFromFolder(String pathToFolder) throws IOException {

        return Files.walk(Paths.get(pathToFolder))
                .filter(Files::isRegularFile)
                .map(Path::toFile).toList();

    }

    public static ObservableList<AtpMatch> getAtpTourObservable() {
        return null;
    }

    private Result<Record> fetchRowsFromCsv(List<String[]> list, boolean header) {
        return null;
    }

}














