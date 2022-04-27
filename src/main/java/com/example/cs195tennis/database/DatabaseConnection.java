package com.example.cs195tennis.database;
import java.sql.DatabaseMetaData;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.sql.DriverManager;
import java.util.logging.Level;


public class DatabaseConnection {

    private final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class.getName());
    private final String driver = "org.sqlite.JDBC";
    private static final String url = "jdbc:sqlite:wta-tournaments.sqlite";

    public Connection c = DriverManager.getConnection(url);
    DatabaseMetaData meta = c.getMetaData();
    String productName = meta.getDatabaseProductName();
    String productVersion = meta.getDatabaseProductVersion();

    private static final String requiredTable = "Tournaments";

    public DatabaseConnection() throws SQLException {
    }

    public static Connection connect() {
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:wta-tournaments.sqlite";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


    private static boolean checkTables() throws SQLException {
        String checkTables =
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" + requiredTable + "'";

        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement statement = connection.prepareStatement(checkTables);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getString("tbl_name").equals(requiredTable)) return true;
            }
            System.out.println("Could not find Table");
        }
        return false;
    }

    public static boolean isOK() throws SQLException {
        return checkTables();
    }


    public ResultSet execQuery(String qu) throws SQLException {
        String updateQ = "UPDATE wta_matches_1990_to_2022 set tourney_name=Australian Open";
<<<<<<< HEAD
        String query = "SELECT * FROM wta_matches_1990_to_2022 where tourney_date >= 2022000000";
=======
        String query = "SELECT * FROM wta_matches_1990_to_2022";
>>>>>>> origin/develop
        Connection connection = DatabaseConnection.connect();

        if(Objects.nonNull(connection)) {
            Statement statement = connection.createStatement();

<<<<<<< HEAD
            return statement.executeQuery(query);
=======
            ResultSet rs = statement.executeQuery(query);
//
//            while(rs.next()) {
//                System.out.println(rs.getString("tourney_name"));
//                System.out.println(rs.getString("tourney_date"));
//                System.out.println(rs.getString("winner_name"));
//            }
            return rs;
>>>>>>> origin/develop
        }else{
            System.out.println("Database or JDBC url error");
            return null;
        }
    }
}

