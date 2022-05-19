package com.example.cs195tennis.Dao.DataModel;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.PlayerRanking;
import com.example.cs195tennis.model.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Selector;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.cs195tennis.Dao.DataModel.TournamentTable.TOURNAMENT;
import static org.jooq.impl.DSL.*;

public class DataHandeler {

    static void main(String[] args) {
        DSLContext create = using(Database.connect(), SQLDialect.SQLITE);

        int count = create.fetchCount(DSL.selectFrom("Tournament"));

    }
    DSLContext cst = using(Database.connect(), SQLDialect.SQLITE);


    static DSLContext ctx(){
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
        return ctx;
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

    public static ObservableList <PlayerRanking> getPlayerRanks() throws SQLException {
        return FXCollections.observableArrayList();
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









