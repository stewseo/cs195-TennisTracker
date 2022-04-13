package com.example.cs195tennis.database;


import com.example.cs195tennis.model.TournamentStats;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataHelper {

    DbController dbController;
    private final Logger LOGGER = LogManager.getLogger(DbController.class.getName());



    public boolean insertToTournament(TournamentStats tournament) {
        try {
            PreparedStatement statement = dbController.getConnection().prepareStatement(
                    "INSERT INTO TOURNAMENT(id,title,author,publisher,isAvail) VALUES(?,?,?,?,?)");
            statement.setString(1, tournament.getId());
            statement.setString(2, tournament.getLoser_name());
            statement.setString(3, tournament.getTOURNEY_DATE());
            statement.setString(4, tournament.getWinner_ioc());
            statement.setString(5, tournament.getROUND());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.ERROR, "{}", ex);
        }
        return false;
    }

    public boolean insertToMatch(TournamentStats stats) {
        try {
            PreparedStatement statement = dbController.getConnection().prepareStatement(
                    "INSERT INTO MATCH(id,name,mobile,email) VALUES(?,?,?,?)");
            statement.setString(1, stats.getId());
            statement.setString(2, stats.getId());
            statement.setString(3, stats.getId());
            statement.setString(4, stats.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.ERROR, "{}", ex);
        }
        return false;
    }


    public boolean insertToPlayer(String id) {
        try {
            String checkstmt = "SELECT COUNT(*) FROM PLAYER WHERE id=?";
            PreparedStatement stmt = dbController.getConnection().prepareStatement(checkstmt);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println(count);
                return (count > 0);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.ERROR, "{}", ex);
        }
        return false;
    }

    public void wipeTable(String tableName) {
        try {
            Statement statement = dbController.getConnection().createStatement();
            statement.execute("DELETE FROM " + tableName + " WHERE TRUE");
        } catch (SQLException ex) {
            LOGGER.log(Level.ERROR, "{}", ex);
        }
    }


}
