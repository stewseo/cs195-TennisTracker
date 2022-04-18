//package com.example.cs195tennis.ui;
//
//
//import com.example.cs195tennis.database.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class createAndExecute {
//    DatabaseConnection dbCont;
//
//    private void executeUpdate(String s) throws SQLException {
//
//        Connection connection = DriverManager.getConnection(dbCont.());
//        executeUpdate(
//                "CREATE TABLE \"ENTRY\" (" +
//                        "\"SHARED_ID\" NUMBER NOT NULL, " +
//                        "\"TYPE\" VARCHAR2(255) NULL, " +
//                        "\"VERSION\" NUMBER DEFAULT 1, " +
//                        "CONSTRAINT \"ENTRY_PK\" PRIMARY KEY (\"SHARED_ID\"))");
//
//        connection.createStatement().
//
//                executeUpdate("CREATE SEQUENCE \"ENTRY_SEQ\"");
//
//        connection.createStatement().
//
//                executeUpdate("CREATE TRIGGER \"ENTRY_T\" BEFORE INSERT ON \"ENTRY\" " +
//                        "FOR EACH ROW BEGIN SELECT \"ENTRY_SEQ\".NEXTVAL INTO :NEW.shared_id FROM DUAL; END;");
//
//        connection.createStatement().
//
//                executeUpdate(
//                        "CREATE TABLE \"FIELD\" (" +
//                                "\"ENTRY_SHARED_ID\" NUMBER NOT NULL, " +
//                                "\"NAME\" VARCHAR2(255) NOT NULL, " +
//                                "\"VALUE\" CLOB NULL, " +
//                                "CONSTRAINT \"ENTRY_SHARED_ID_FK\" FOREIGN KEY (\"ENTRY_SHARED_ID\") " +
//                                "REFERENCES \"ENTRY\"(\"SHARED_ID\") ON DELETE CASCADE)");
//
//        connection.createStatement().
//
//                executeUpdate(
//                        "CREATE TABLE \"METADATA\" (" +
//                                "\"KEY\"  VARCHAR2(255) NULL," +
//                                "\"VALUE\"  CLOB NOT NULL)");
//
//    }
//
//
//    String escape(String expression) {
//        return expression;
//    }
//
//}
