package com.example.cs195tennis.Dao;
import com.example.cs195tennis.Dao.DataModel.DataHandeler;
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
import org.jooq.
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.io.FileReader;
import java.io.IOException;
import java.lang.Record;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT1;
import static org.jooq.impl.DSL.*;


public class TournamentDao {

    static DSLContext create() {
        try(Connection conn = Database.connect()) {

            return using(conn, SQLDialect.SQLITE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Collection<Field<?>> getTournamentObservable() throws SQLException {

        Collection<Field<?>> fields = new ArrayList<>();

        Objects.requireNonNull(create()).selectFrom(TOURNAMENT1)
                .orderBy(TOURNAMENT1.ID).forEach(System.out::println);

        return fields;
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

        Map<String, List<Tournament>> map = new HashMap<>();


        Result<org.jooq.Record> tournaments = Objects.requireNonNull(create()).select()
                .from(GRAND_SLAM)
                .fetch();

        Map<String, List<String>> mapp = new HashMap<>();

        Stream<Tournament> stream = create().selectFrom(GRAND_SLAM).stream()

            stream.forEach(e -> {

                mapp.computeIfAbsent(e.getTourney_id(), v -> new ArrayList<>());
                
                mapp.get(e.getTourney_id()).add(new Tournament(e.("tourney_id"),field("tourney_name"),field("tourney_date"), v.get));
                });
                
            });


//        tournaments.forEach(t -> {
//            Integer id = t.getValue(GRAND_SLAM.ID);
//            String tourneyName = t.getValue(GRAND_SLAM.TOURNEY_NAME);
//            String tourneyDate = t.getValue(GRAND_SLAM.DATE);
//            String tourneyLevel = t.getValue(GRAND_SLAM.TOURNEY_NAME);
//            String tourneyLevel = t.getValue(GRAND_SLAM.TOURNEY_NAME);
//            String surface = t.getValue(GRAND_SLAM.TOURNEY_NAME);


//
//        Objects.requireNonNull(create()).selectFrom(TOURNAMENT1)
//                .orderBy(TOURNAMENT1.ID).forEach(e -> {
//                    new Tournament(e.get(1),e.get(2));
//                        });
//
//            return grandSlamObservable;



    public static ObservableList<Tournament> getAllTournamentFieldsObservable() throws SQLException {


        ObservableList<Tournament> wtaResultsObservable = FXCollections.observableArrayList();
        wtaResultsObservable.addAll();
        return wtaResultsObservable;
    }

}



