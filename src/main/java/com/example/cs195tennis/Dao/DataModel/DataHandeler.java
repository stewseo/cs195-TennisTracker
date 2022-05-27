package com.example.cs195tennis.Dao.DataModel;
import com.example.cs195tennis.database.Database;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.QOM;

import javax.naming.InvalidNameException;
import java.io.FileReader;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.cs195tennis.Dao.PlayerDao.ctx;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DataHandeler {

    static DSLContext ctx(){
        return using(Database.connect(), SQLDialect.SQLITE);
    }

    static List<Object> getTable(String tableName) {
        return ctx().select(table(tableName).fields()).stream().collect(Collectors.toList());
    }

    static String csvDirectory = "C:\\Users\\seost\\New folder";

    public static void main(String[]args) throws IOException {

        Table<?> inputTable = table("WtaRanks2000_2022");

        Result<?> result =
                ctx().select(field("player_id"), field("name_first", field("name_last"), field("ranking_date"),field("player"),field("rank"),field("points")))
                        .from(inputTable)
                        .where(field("player_id").isNotNull())
                        .orderBy(field("rank"))
                        .fetch();

        result.forEach(e->{
            if(e.size() > 3) {
                System.out.println(e);
            }
        });

    }


    private static void printFields() throws IOException {

        List<Table<?>> r = ctx().meta().getTables();

        IntStream.range(0, r.size()).forEach(e->{

            System.out.println("table: " + e);

            if(ctx().fetchCount(DSL.selectFrom(r.get(e))) >= 0){

                System.out.println(ctx().fetchCount(DSL.selectFrom(r.get(e))));

                System.out.println("table " + r.get(e).fieldsRow());
            }
        });


        //return list of all files at the end of path

        List<String> list = new ArrayList<>();

        List<String> l = getFilesFromFolder(csvDirectory);

        List<String> temp = new ArrayList<>();

        l.forEach(e-> {

            List<List<String>> recordList = new ArrayList<>();

            try {
                recordList = parseCsvToListString(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //match point by point data
            recordList.forEach(col-> {

                var v = col.get(1);

                int key = col.get(0).hashCode();
                key ^= key >>> 16;

                var v1 = col.get(1);
                var v2 = col.get(2);
                var v3=  col.get(3);
                var v4 = col.get(4);

                ctx().insertInto(table("WtaRanks2000_2022"), field("player_id"), field("name_first"),field("name_last"),field("hand"),field("dob"),field("ioc"))
                        .values(col.get(0), col.get(1), col.get(2), col.get(3), col.get(4), col.get(5)).execute();
            });}
        );
    }


    //
    static void insertAllRowsToDb(List<List<String>> li) throws IOException {

        int n = "points.csv".hashCode();

        List<String> list = getFilesFromFolder(csvDirectory).stream().filter(
                files-> files.substring(files.length()-10, files.length()).hashCode() == n).toList();


        list.forEach(e -> {

            List<String> temp = new ArrayList<>();

            try {
                temp = parseCsvToListString(e).get(0);

                List<List<String>> l =  parseCsvToListString(e);

                List<String> finalTemp = temp;
                l.forEach(col -> {
                    var v =  col.get(1);

                    int key = col.get(0).hashCode();

                    key ^= key >>> 16;


//                    InsertQuery<?> insert = ctx().insertQuery(DSL.table("GrandSlamPointByPoint"));
//
//                    IntStream.range(0, col.size()).forEach(i-> {
//                        insert.addValue(DSL.field(finalTemp.get(i), String.class), col.get(i));
//                        insert.execute();
//                    });
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    };

    static List<String> getFilesFromFolder(String directory) throws IOException {
        return Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .map(Path::toString).toList();
    }

    static List<List<String>> parseCsvToListString(String path) throws IOException {
        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
            String[] values = null;
            List<String> columns = new ArrayList<>();
            while ((values = csvReader.readNext()) != null) {
                allMatchesCsv.add(Arrays.asList(values));
            }
    } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return allMatchesCsv;
    }

    public static void createTable(String tableName, List<String> columns) throws SQLException, InvalidNameException {
        if(tableName == null || tableName.length() == 0){
            throw new InvalidNameException("Invalid");
        }

        ctx().createTableIfNotExists(table(tableName,columns)).execute();
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

    }










