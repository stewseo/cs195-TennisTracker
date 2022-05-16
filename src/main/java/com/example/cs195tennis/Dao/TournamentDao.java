package com.example.cs195tennis.Dao;
import com.example.cs195tennis.Dao.DataModel.DataHandeler;
import com.example.cs195tennis.Dao.DataModel.TournamentRecord;
import com.example.cs195tennis.Dao.DataModel.TournamentTable;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Match;
import com.example.cs195tennis.model.Tournament;
import com.example.cs195tennis.model.WtaMatch;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.io.FileReader;
import java.io.IOException;
import java.lang.Record;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT;
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

    public static  Collection<Field<?>>  getTournamentObservable() throws SQLException {

        Query queryT = select(field(name("GRANDSLAM"), SQLDataType.VARCHAR), field(name("TOURNEY_DATE"), SQLDataType.VARCHAR)).from(table(name("TOURNAMENT")));


        Collection<Field<?>> fields = new ArrayList<>();

        Map<String, List<Table<?>>> allTables = new LinkedHashMap<>();

        TOURNAMENT.allTablesInDb().forEach(e-> {
            if(e==null)return;

            System.out.println("length " + e.fields().length);

            System.out.println("primary key name ");

            int n = e.fields().length;

            System.out.print(" Number of Columns: " + n);

            for(Field field : e.fields()){

               Objects.nonNull(field);

                    System.out.println("\nindex: " + e.indexOf(field));

                    System.out.print(" = " + field + ", ");


            }
            fields.addAll(Arrays.stream(e.fields()).sequential().toList());
        });

        System.out.println(fields.size());

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

//    public static ObservableList<Tournament> grandSlamTournamentsObservable(String table) throws SQLException {
//
//        ObservableList<Tournament> grandSlamObservable = FXCollections.observableArrayList();
//
//        PreparedStatement ps = c.prepareStatement("select * from " + table);
//
//        ResultSet rs = ps.executeQuery();
//
//        Query query = create().select(TOURNAMENT.ID, TOURNAMENT.NAME, TOURNAMENT.DATE, TOURNAMENT.SURFACE, TOURNAMENT.DRAW_SIZE, TOURNAMENT.LEVEL, TOURNAMENT.DATE)
//                .from(TOURNAMENT);
//
//        String sql = query.getSQL();
//
//        List<Object> bindValues = query.getBindValues();
//
//        while (rs.next()) {
//            grandSlamObservable.add(
//                    new WtaMatch(
//                            rs.getString("tourney_id"),
//                            rs.getString("tourney_name"),
//                            rs.getString("tourney_date"),
//                            rs.getString("winner_name"),
//                            rs.getString("loser_name"),
//                            rs.getString("score"),
//                            rs.getString("round"),
//                            rs.getString("winner_id"),
//                            rs.getString("loser_id")
//                    ));
//        }
//        return grandSlamObservable;
//    }

//    public static ObservableList<Tournament> getAllTournamentFieldsObservable() throws SQLException {
//        ResultSet rs = Database.connect().createStatement().executeQuery("SELECT * FROM Tournament");
//
//        Result<org.jooq.Record> result = create().fetch(rs);
//
//        Cursor<org.jooq.Record> cursor = create().fetchLazy(rs);
//
//        ObservableList<Tournament> wtaResultsObservable = FXCollections.observableArrayList();
//        wtaResultsObservable.addAll();
//        return wtaResultsObservable;
//    }

}



