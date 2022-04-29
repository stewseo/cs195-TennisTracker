package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.DatabaseConnection;
import com.example.cs195tennis.model.Rankings;
import com.example.cs195tennis.model.Player;
import org.sqlite.jdbc3.JDBC3Connection;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDao {
//        private static String mainTable = "Player";
        private static List<Player> playerList;
        private static List<Rankings> playerRankList;
        private static final String id = "id";
        private static final String firstName = "firstName";
        private static final String lastName = "lastName";
        private static final String hand = "hand";
        private static final String dob = "dob";
        private static final String ioc = "ioc";
        private static final String height = "height";
        private static final String wikiData_id = "wikidata_id";
        private static final String player_Rank_Id = "ID";

        private static final String CREATE_PLAYER = "CREATE TABLE PLAYER"
                + "("
                + " ID INT,"
                + " firstName TEXT NOT NULL,"
                + " lastName TEXT NOT NULL,"
                + " hand TEXT,"
                + " dob TEXT,"
                + " ioc TEXT,"
                + " height TEXT,"
                + " wikidata_id TEXT,"
                + " PRIMARY KEY (ID)"
                + ")";


        private static final String CREATE_CURRENT_RANK = "CREATE TABLE RANK"
                + "("
                + " ID INT,"
                + " ranking_date TEXT ,"
                + " rank TEXT,"
                + " player TEXT NOT NULL,"
                + " points TEXT NOT NULL,"
                + " PRIMARY KEY (ID)"
                + ")";


        static String rankingTable = "Player_Rank";



    public static void insertPlayerFromCsv() throws SQLException, IOException {
        Connection c = DatabaseConnection.connect();

        c.setAutoCommit(false);
        int batchSize = 20;
        String sql = "INSERT INTO PLAYER (id, firstName, lastName, hand, dob, ioc, height, wikidata_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = c.prepareStatement(sql);

        BufferedReader lineReader = new BufferedReader(new FileReader(playerCsv));
        String lineText = null;
        int count = 0;
        List<Player> playerList = new ArrayList<Player>();

        File inputF = new File(playerCsv);
        InputStream inputFS = new FileInputStream(inputF);
        Player player = new Player();

        lineReader.readLine();

        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");
            System.out.println(data.length);
            String id = data[0];
            String firstName = data[1];
            String lastName = data[2];
            String hand = data[3];
            String dob = data.length > 4 ? data[4] : "";
            String ioc  = data.length >  5 ? data[5] : "";
            String height = data.length > 6 ? data[6] : "";
            String wikidata_id = data.length > 7 ? data[7] : "";


            statement.setString(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, hand);
            statement.setString(5, dob);
            statement.setString(6, ioc);
            statement.setString(7, height);
            statement.setString(8, wikidata_id);

            statement.addBatch();

            statement.executeBatch();
        }
        lineReader.close();

        statement.executeBatch();

        c.commit();
        c.close();
    }




    public static void createTablePlayer() {

        Connection c = null;
        Statement st = null;

        try {
            c = DatabaseConnection.connect();
            st = c.createStatement();

            st.executeUpdate(CREATE_PLAYER);

            System.out.println("Table created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public static void createTablePlayerRank(String rankingTable) {

            Connection c = null;
            Statement st = null;

            try {
                c = DatabaseConnection.connect();
                st = c.createStatement();

                st.executeUpdate(CREATE_CURRENT_RANK);

                System.out.println("Table created");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public static List<Rankings> getRankingList() {
            String query = "SELECT * FROM " + " PLAYER ";
            List<Rankings> currentRank = new ArrayList<>();
            try (Connection connection = DatabaseConnection.connect()) {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    currentRank.add(new Rankings(
                            rs.getString("ranking_date"),
                            rs.getString("points"),
                            rs.getString("hand"),
                            rs.getString("dob")));
                }
                System.out.println("here");
                currentRank.forEach(System.out::println);
            } catch (SQLException e) {
                e.printStackTrace();
            }return currentRank;
        }

        String querys = " select"+ lastName+ " from " +"PLAYERS"+" join ranking on player.id = ranking.player_id where pos == 1 group by lastName; ";


        public static List<Player> getTempList() {

            String query = "SELECT * FROM " + "Player";

            List<Player> player = new ArrayList<>();
            try (Connection connection = DatabaseConnection.connect()) {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    player.add(new Player(
                            rs.getString(id),
                            rs.getString(firstName),
                            rs.getString(lastName),
                            rs.getString(hand),
                            rs.getString(dob),
                            rs.getString(ioc),
                            rs.getString(height),
                            rs.getString(wikiData_id)
                    ));
                }
                System.out.println("Size of temp player list: " + player.size());

            } catch (SQLException e) {
                e.printStackTrace();
            }return player;
        }


        static String playerCsv = "cs195-TennisTracker\\src\\main\\resources\\com\\example";
        static String playerRankingsCsv = "";



//        createPlayerTableFromCsv();

//        rankCsvToSql();
//        createTablePlayer(CREATE_PLAYER);
//        createTablePlayerRank(CREATE_CURRENT_RANK);
        }





