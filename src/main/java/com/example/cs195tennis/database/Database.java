package com.example.cs195tennis.database;

import java.sql.*;

public class Database {

    public DatabaseConnection dbConnection;

    String catalog = null,
            schemaPattern = null,
            tableNamePattern = null;
    String columnNamePattern = null;
    String[] types = {"TABLE"};

    public Database() {}


    private void getMetaInfo() throws SQLException {

        DatabaseMetaData meta = dbConnection.c.getMetaData();

        String productName = meta.getDatabaseProductName();

        String productVersion = meta.getDatabaseProductVersion();

        ResultSet result = meta.getTables(catalog, schemaPattern, tableNamePattern, types);

        result.next();

        String tableName = result.getString(3);

        ResultSet rsColumns = meta.getColumns(catalog, schemaPattern, tableName, columnNamePattern);

        ResultSet rsPK = meta.getPrimaryKeys(catalog, schemaPattern, tableName);

    }

//    public ResultSet getTournamentInfo() throws SQLException {
//        String query = "SELECT TournamentStats.Source_id, TournamentStats.author, BOOK.isAvail, ISSUE.issueTime FROM BOOK LEFT JOIN ISSUE on BOOK.id = ISSUE.bookID where BOOK.id = ?";
//        PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(query);
////        stmt.setString(1, id);
////        ResultSet rs = stmt.executeQuery();
//        return rs;
//    }

    public ResultSet execQuery(String query)  {

        try {
            dbConnection = new DatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement statement = null;
        try {
            statement = dbConnection.c.prepareStatement("SELECT * FROM wta_matches_1990_to_2022 WHERE winner_ioc = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.setString(1, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet foundS = null;
        try {
            foundS = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(foundS);
        return foundS;
    }

    public void readDatabase() {
        try {
            DatabaseMetaData meta = dbConnection.c.getMetaData();
            String catalog = null, schemaPattern = null, tableNamePattern = null;
            String[] types = {"TABLE"};

            ResultSet rsTables = meta.getTables(catalog, schemaPattern, tableNamePattern, types);

            while (rsTables.next()) {
                String tableName = rsTables.getString(3);
                System.out.println("\n=== TABLE: " + tableName);

                String columnNamePattern = null;
                ResultSet rsColumns = meta.getColumns(catalog, schemaPattern, tableName, columnNamePattern);

                ResultSet rsPK = meta.getPrimaryKeys(catalog, schemaPattern, tableName);

                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("COLUMN_NAME");
                    String columnType = rsColumns.getString("TYPE_NAME");
                    int columnSize = rsColumns.getInt("COLUMN_SIZE");
                    System.out.println("\t" + columnName + " - " + columnType + "(" + columnSize + ")");
                }

                while (rsPK.next()) {
                    String primaryKeyColumn = rsPK.getString("COLUMN_NAME");
                    System.out.println("\tPrimary Key Column: " + primaryKeyColumn);
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }
}
