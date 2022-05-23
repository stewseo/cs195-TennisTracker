package com.example.cs195tennis.Dao.DataModel;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.Tournament;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Selector;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT;
import static org.jooq.impl.DSL.*;

public class DataHandeler {

    static String csvDirectory = "C:\\Users\\seost\\tennis_atp_datasets\\tennis_slam_pointbypoint-master";

    public static void main(String[] args) throws IOException {
        int count = ctx().fetchCount(DSL.selectFrom("Tournament"));

        List<Table<?>> r = ctx().meta().getTables();
        getFilesFromFolder(csvDirectory);

//        Arrays.stream(s).forEach(e -> {
//
//            ctx().meta().getTables(e).forEach(table-> System.out.println(Arrays.toString(table.fields())));
//
//        });
    }

    static DSLContext ctx(){
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }

    static void getFilesFromFolder(String directory) throws IOException {
        Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .map(Path::toFile).forEach(e-> {
                    try {
                        parseCsvToListString(e.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
    }

    static void parseCsvToListString(String path) throws IOException {
        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
            String[] values = null;
            List<String> columns = new ArrayList<>();
            columns.add("match_id");
            columns.add("year");
            columns.add("slam");
            columns.add("match_num");
            columns.add("player1");
            columns.add("player2");
            columns.add("status");
            columns.add("winner");
            columns.add("event_name");
            columns.add("round");   
            columns.add("court_name");
            columns.add("court_id");
            columns.add("player1id");
            columns.add("player2id");
            columns.add("nation1");
            columns.add("nation2");







            while ((values = csvReader.readNext()) != null) {
                 System.out.println(Arrays.toString(values));
//                 System.out.println(Arrays.toString(values));
                allMatchesCsv.add(Arrays.asList(values));
            }

//            allMatchesCsv.forEach(e -> {
//                System.out.println(e.get(0));
//            });
    } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    static ObservableList<Tournament> create() {

        ObservableList<Tournament> temp = FXCollections.observableArrayList();

        System.out.println(ctx().selectQuery(TOURNAMENT).fetch().size());

       return temp;
    }

    public static void createTable(String tableName, List<String> columns) throws SQLException {
        int number = columns.size();

        ObservableList<Tournament> tournamentObservable;
        Map<Object, List<Object >> mapToModel = new HashMap<>();

        StringBuilder queryBuilder = new StringBuilder("CREATE TABLE " + tableName + "(ID INT, ");

        int i = 0;

        columns.forEach(e -> {

            mapToModel.computeIfAbsent((e.toString()), v -> new ArrayList<>());

            mapToModel.get(e.toString()).add(e);

            queryBuilder.append(e).append(" TEXT");

        });
    }

    public static List<String> getColumnNames(String tableName) throws SQLException {

        List<String> columnNames = new LinkedList<String>();

        DatabaseMetaData dbm = Database.connect().getMetaData();

        ResultSet rs = dbm.getColumns(null, null, tableName, null);

        while (rs.next()) {
            String keyColumnName = rs.getString("COLUMN_NAME");
            columnNames.add(keyColumnName);
        }
        rs.close();
        return columnNames;
    }

    public static ObservableList<Tournament> getGrandSlams() {
        ObservableList<Tournament> temp = TOURNAMENT.createGrandSlamHistory();

        return temp;
    }


    public <T> List<T> fetchWithSelect(SelectQuery<?> select, Class<T> clazz) {
        DSLContext cst = using(Database.connect(), SQLDialect.SQLITE);
        return null;
        }

}









