package com.example.cs195tennis.Dao.DataModel;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Tournament;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.io.FileReader;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.INTEGER;
import static org.jooq.impl.SQLDataType.VARCHAR;


public class DataHandeler {

    static DSLContext ctx(){
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
    }


    static String csvDirectory = "C:\\Users\\seost\\tennis_atp_datasets\\tennis_slam_pointbypoint-master";

    public static void main(String[] args) throws IOException {
        List<Table<?>> r = ctx().meta().getTables();
        int count = 0;

        IntStream.range(0, r.size()).forEach(e->{
            System.out.println("table: " + e);

            if(ctx().fetchCount(DSL.selectFrom(r.get(e))) >= 0){
                System.out.println("table " + r.get(e));
            }
        });

        getFilesFromFolder(csvDirectory).stream().filter(files->
                files.substring(files.length()-11, files.length()).equals("matches.csv")).forEach(e-> {
            try {
                List<String> temp = parseCsvToListString(e).get(0);

                IntStream.range(0,temp.size()).forEach(i -> {
                    ctx().alterTable("GrandSlams").add(field(name(temp.get(i)), VARCHAR)).execute();
                });
            }catch (IOException ex) {
                ex.printStackTrace();
            });
        });
    }

    static List<String> getFilesFromFolder(String directory) throws IOException {
        return Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .map(Path::toString).toList();
    }

    static List<List<String>> parseCsvToListString(String path) throws IOException {
        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
            String[] values = null;
            List<String> columns = new ArrayList<>();

//            columns.add("match_id");
//            columns.add("year");
//            columns.add("slam");
//            columns.add("match_num");
//            columns.add("player1");
//            columns.add("player2");
//            columns.add("status");
//            columns.add("winner");
//            columns.add("event_name");
//            columns.add("round");
//            columns.add("court_name");
//            columns.add("court_id");
//            columns.add("player1id");
//            columns.add("player2id");
//            columns.add("nation1");
//            columns.add("nation2");

            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
    } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return allMatchesCsv;
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









