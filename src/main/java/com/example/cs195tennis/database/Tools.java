package com.example.cs195tennis.database;

import com.example.cs195tennis.Dao.Schem;
import com.example.cs195tennis.Dao.Table.StatisticsListener;
import org.jooq.ExecuteType;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Properties;

import static java.lang.System.out;

public class Tools {

    static Properties properties;
    static Connection connection;
    private static final String driver = "org.sqlite.JDBC";


    public static Connection connection() {
            if (connection == null) {
                try {
                    connection = Database.connect();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return connection;
        }

    public static String url() {
        return "jdbc:sqlite:Database/wta-tournaments.sqlite";
    }

    public static Properties properties() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(Tools.class.getResourceAsStream("config.properties"));
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }

    public static void title(String title) {
            String dashes = "=============================================================================================";

            System.out.println();
            System.out.println(title);
            System.out.println(dashes);
            System.out.println();
        }

        public static void print(Object o) {
            System.out.println(o);
            System.out.println("statistics key set: " + StatisticsListener.STATISTICS.keySet());
            System.out.println("entry set: " + StatisticsListener.STATISTICS.entrySet());
            System.out.println("executions: " + StatisticsListener.STATISTICS.entrySet());
            Arrays.stream(ExecuteType.values()).forEach(type->{
                out.println(type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
            });
        }

    public static void meta() {
        Schem.tableInfo();
    }
}

