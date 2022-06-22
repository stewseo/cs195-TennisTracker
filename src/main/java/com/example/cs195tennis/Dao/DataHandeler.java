package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.jooq.*;
import org.jooq.impl.DSL;

import javax.naming.InvalidNameException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Record;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.jooq.impl.DSL.*;


//
public class DataHandeler {

    static DSLContext ctx(){
        return using(Database.connect(), SQLDialect.SQLITE);
    }


    /**
     @param:
     table name that will be returned as a list
     */
    private List<Object> getTable(String tableName) {
        return ctx().select(table(tableName).fields()).stream().collect(Collectors.toList());
    }


    public void csvToListString(List<String> getFilesFromFolder, String tableName, String[] fields) throws IOException {

        getFilesFromFolder.forEach(e->{
            List<List<String>> recordList = new ArrayList<>();
            try {
                recordList =  parseCsvToListString(e);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            insertRank(recordList);

        });
        System.out.println("done");
    }


    /**
    @param:
    table name containing column to be altered,
     fields that will be added or removed from the @param table
     if fields are being added or removed from the table
     */
    public void updateColumn(String tableName, String[] fields, boolean addColumn) {

        if(!addColumn) {
            Arrays.stream(fields).forEach(field -> {
                ctx().alterTable(table(tableName)).addColumn(field(field)).execute();
            });
        }else {
            Arrays.stream(fields).forEach(field -> {
                ctx().alterTable(table(tableName)).drop(field(field)).execute();
            });
        }
    }

    /**
     @param:
     Path of CSV
     table name
     */
    public void insert(String getFilesFromFolder, String tableName) throws IOException {
        List<List<String>> temp = parseCsvToListString(getFilesFromFolder);

        List<String> fields = new ArrayList<>();

        temp.forEach(col -> {
                var tours = "";
                var ranking_date = col.get(0);
                var rank = col.get(1);
                var player = col.get(2);
                var points = col.get(3);
                ctx().insertInto(table("AtpRankings"), field("ranking_date"), field("rank"), field("player"), field("points"))
                        .values(ranking_date, rank, player, points).execute();
            });

        temp.forEach(col-> {

                String playerId = col.get(0);
                String firstName = col.get(1);
                String lastName = col.get(2);
                String hand = col.get(3);
                String dateOfBirth = col.get(4);
                String location = col.get(5) == null ? "" : col.get(5);
                String height = col.get(6) == null ? "" : col.get(6);
                String wikiId = col.get(7) == null ? "" : col.get(7);

                ctx().insertInto(table("WtaPlayers"), field("player_id"), field("name_first"), field("name_last"), field("hand"), field("dob"),
                                field("ioc"),field("height"),field("wikidata_id"))
                        .values(playerId, firstName, lastName, hand, dateOfBirth, location,height,wikiId).execute();
            });
        System.out.println("records in table: " + ctx().fetchCount(table("AtpPlayerRanking")));

        }

    public static void changeFieldName(String tableName, String columnName) {
        ctx().alterTable("WtaRanking").renameColumn("player_id").to("player").execute();
    }


    // print all tables in the database
    // print all fields in each table
    // print number of items per column
    void printTableFields() throws IOException {

        ctx().meta().getTables().forEach(table->{
            System.out.println(ctx().meta().getTables(table.getName()));
            System.out.println(ctx().meta().getTables(table.getName()).get(0).fields().length);
        });

        List<Table<?>> r = ctx().meta().getTables();

        IntStream.range(0, r.size()).forEach(e->{

            System.out.println("table: " + e);

            if(ctx().fetchCount(DSL.selectFrom(r.get(e))) >= 0){

                System.out.println(ctx().fetchCount(DSL.selectFrom(r.get(e))));

                System.out.println("table " + r.get(e).fieldsRow());
            }
        });

    }


    /**
     *
     * @param  @path to a folder that contains CSV files
     * @return List of csv file paths
     * @throws IOException
     */
    List<String> getFilesFromFolder(String directory) throws IOException {
        return Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .map(Path::toString).toList();
    }

    /**
     *
     * @param path to a local CSV file
     * @return a List of List Strings. Each List<String> is a CSV Row
     * @throws IOException
     */
    public List<List<String>> parseCsvToListString(String path) throws IOException {
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

    /**
     *
     * @param tableName of table being created in the db4
     * @param fields that will be added to this table
     * @throws SQLException
     * @throws InvalidNameException
     */
    private void createTable(String tableName, List<String> fields) throws SQLException, InvalidNameException {
        if(tableName == null || tableName.length() == 0){
            throw new InvalidNameException("Invalid");
        }

        ctx().createTableIfNotExists(table(tableName, fields)).execute();
    }


    /**
     @param:
     Name of Table to be cleared
     */
    public void clearTable(Table<?> table) {
        ctx().delete(table).execute();
    }

    public void dropTable(String name) {
        ctx().dropTable(name).execute();
    }

    public static <R extends UpdatableRecord<R>> void save(UpdatableRecord<R> record) {
        record.store();
    }


    public static <R extends UpdatableRecord<R>> void update(UpdatableRecord<R> record) {
        record.update();
    }

    public static void delete(DSLContext context, Table<? extends Record> table, Condition condition) {
        context.delete(table)
                .where(condition)
                .execute();
    }

    public static <R extends UpdatableRecord<R>> void delete(UpdatableRecord<R> record) {
        record.delete();
    }

    public static Result<org.jooq.Record> getAll(DSLContext context, Table<? extends org.jooq.Record> table) {
        return context.select()
                .from(table)
                .fetch();
    }

    public static Result<org.jooq.Record> getFields(DSLContext context, Table<? extends org.jooq.Record> table, SelectFieldOrAsterisk... fields) {
        return context.select(fields)
                .from(table)
                .fetch();
    }

    public static <R extends org.jooq.Record> R getOne(DSLContext context, Table<R> table, Condition condition) {
        return context.fetchOne(table, condition);
    }

    public static <T> void update(DSLContext context, Table<? extends org.jooq.Record> table, Map<Field<T>, T> values, Condition condition) {
        context.update(table)
                .set(values)
                .where(condition)
                .execute();
    }


}










