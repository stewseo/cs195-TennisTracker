package com.example.cs195tennis.Dao;
import com.example.cs195tennis.Dao.DataModel.TournamentRecord;
import com.example.cs195tennis.Dao.DataModel.TournamentTable;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Tournament;
import com.example.cs195tennis.model.WtaMatch;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.beans.property.adapter.ReadOnlyJavaBeanDoublePropertyBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.util.PropertySource;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.meta.sqlite.SQLiteDatabase;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT1;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.using;


public class TournamentDao {

    static DSLContext create() {
        try (Connection conn = Database.connect()) {

            return using(conn, SQLDialect.SQLITE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Tournament> getTournamentObservable() throws SQLException {

        Map<String, List<Tournament>> map = new LinkedHashMap<>();
        return null;

    }

    public static List<List<String>> readCSVRowsToList(String url) throws SQLException {

        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(url));) {
            String[] values = null;

            while ((values = csvReader.readNext()) != null) {

                allMatchesCsv.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return allMatchesCsv;
    }
    static String GRAND_SLAM = "GrandSlam";


    public static <R> ObservableList<Tournament> grandSlamTournamentsObservable() throws SQLException {

        ObservableList<Tournament> grandSlamObservable = FXCollections.observableArrayList();

        Map<Object, List<Object>> map = new HashMap<>();

        Result<TournamentRecord> tournaments = create().selectQuery(TOURNAMENT1).fetch();

        System.out.println("fetched " + tournaments);

        Result<Record> result =
                create().select()
                        .from(GRAND_SLAM).fetch();


        result.forEach(e -> {

            if (e == null) return;

            Object o2 = (Object) e;

            Field<?> field;

            List<Object> obj = new ArrayList<>();

            grandSlamObservable.addAll(new Tournament(e.fields()));

            map.computeIfAbsent(field("o2"), v -> new ArrayList<>());

            map.get(field("id")).add(e);

        });

        map.forEach((k, v) -> v.forEach(System.out::println));

        System.out.println("test ");


       TournamentDao.getAllTournamentFieldsObservable();
        tournaments.forEach(t -> {
            System.out.println();
            Integer id = t.getValue(TOURNAMENT1.ID);
            String tourneyName = t.getValue(TOURNAMENT1.TOURNEY_NAME);
            String surface = t.getValue(TOURNAMENT1.SURFACE);
            String level = t.getValue(TOURNAMENT1.LEVEL);
            String tourneyDate = t.getValue(TOURNAMENT1.DATE);
        });
        grandSlamObservable.addAll();
        return grandSlamObservable;
    }



    public static ObservableList<Tournament> getAllTournamentFieldsObservable() throws SQLException {

        ObservableList<Tournament> wtaResultsObservable = FXCollections.observableArrayList();

        wtaResultsObservable.addAll();

        return wtaResultsObservable;
    }

    public <T> List<T> fetchUsingSelectStatement(SelectQuery<?> select, Class<T> clazz) {

        if(select == null) {
            return null;
        }

//        List<T> list = new ArrayList<T>(clazz);

        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);

        try (Connection connection = Database.connect()) {

            DSLContext create = using(connection, SQLDialect.SQLITE);

        } catch (Exception e) {
            return null;
        }
        return null;

    }
}



