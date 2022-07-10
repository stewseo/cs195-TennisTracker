//package com.example.cs195tennis.Data.Dao;
//
//import com.example.database.db_connection.Connection.Database;
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvValidationException;
//import org.jooq.*;
//import org.jooq.exception.DataAccessException;
//import org.jooq.impl.DSL;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.Record;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//
//import static org.jooq.impl.DSL.*;
//
//
////
//public class DataHandeler {
//
//    static DSLContext ctx(){
//        return using(Database.connect(), SQLDialect.SQLITE);
//    }
//
//
//
//    private List<Object> getFields(String tableName) {
//        return ctx()
//                .select(table(tableName).fields())
//                .stream()
//                .collect(Collectors.toList());
//    }
//
//
//    public void csvToListString(List<String> getFilesFromFolder, String tableName, String[] fields) throws IOException {
//
//        getFilesFromFolder.forEach(e->{
//            List<List<String>> recordList = new ArrayList<>();
//            try {
//                recordList =  parseCsvToListString(e);
//
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        });
//    }
//
//    /**
//    @param:
//    table name containing column to be altered,
//     fields that will be added or removed from the @param table
//     if fields are being added or removed from the table
//     */
//    public void updateColumn(String tableName, String[] fields, boolean addColumn) {
//
//        if(!addColumn) {
//            Arrays.stream(fields).forEach(field -> {
//                ctx().alterTable(table(tableName)).addColumn(field(field)).execute();
//            });
//        }else {
//            Arrays.stream(fields).forEach(field -> {
//                ctx().alterTable(table(tableName)).drop(field(field)).execute();
//            });
//        }
//    }
//
//    /**
//     @param:
//     Path of CSV
//     table name
//     */
//    static int c = 1;
////    public void loadFromCsvIntoMySql(String path, String tableName) throws IOException {
////        getFilesFromFolder(path).forEach(csv-> {
////            List<List<String>> rowsAsListString = null;
////            try {
////                rowsAsListString = parseCsvToListString(csv);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////            List<String> fields = new ArrayList<>();
////
////            for(int i = 1; i<rowsAsListString.size(); i++) {
////
////                if(rowsAsListString.get(i).get(1).charAt(0) >= 48 && rowsAsListString.get(i).get(1).charAt(0) <= 57) {
////                    if (rowsAsListString.get(i).get(3).charAt(0) >= 48 && rowsAsListString.get(i).get(3).charAt(0) <= 57) {
////                        var YEAR = rowsAsListString.get(i).get(1);
////                        var NAME = rowsAsListString.get(i).get(2);
////                        var TOURNEY_ID = YEAR +"-"+ NAME;
////
////                        ctx().insertInto(TOURNAMENT,
////                                        TOURNAMENT.TOUR,
////                                        TOURNAMENT.DATE,
////                                        TOURNAMENT.NAME
////                                )
////                                .values(TOURNEY_ID.toString(),
////                                        Integer.parseInt(YEAR),
////                                        NAME.toString()
////                                )
////                                .execute();
////                    }
////                }
////            }
////
////        });
////    }
//
//    public static void changeFieldName(String tableName, String columnName) {
//        ctx().alterTable("WtaRanking").renameColumn("player_id").to("player").execute();
//    }
//
//
//    // print all tables in the database
//    // print all fields in each table
//    // print number of items per column
//    void printTableFields() throws IOException {
//
//        ctx().meta().getTables().forEach(table->{
//            System.out.println(ctx().meta().getTables(table.getName()));
//            System.out.println(ctx().meta().getTables(table.getName()).get(0).fields().length);
//        });
//
//        List<Table<?>> r = ctx().meta().getTables();
//
//        IntStream.range(0, r.size()).forEach(e->{
//
//            System.out.println("table: " + e);
//
//            if(ctx().fetchCount(DSL.selectFrom(r.get(e))) >= 0){
//
//                System.out.println(ctx().fetchCount(DSL.selectFrom(r.get(e))));
//
//                System.out.println("table " + r.get(e).fieldsRow());
//            }
//        });
//
//    }
//
//    /**
//     *
//     * @param  @path to a folder that contains CSV files
//     * @return List of csv file paths
//     * @throws IOException
//     */
//    public List<String> getFilesFromFolder(String directory) throws IOException {
//        return Files.walk(Paths.get(directory))
//                .filter(Files::isRegularFile)
//                .map(Path::toString).toList();
//    }
//
//    /**
//     *
//     * @param path to a local CSV file
//     * @return a List of List Strings. Each List<String> is a CSV Row
//     * @throws IOException
//     */
//    public List<List<String>> parseCsvToListString(String path) throws IOException {
//        List<List<String>> allMatchesCsv = new ArrayList<List<String>>();
//
//        try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
//            String[] values = null;
//            List<String> columns = new ArrayList<>();
//            while ((values = csvReader.readNext()) != null) {
//                allMatchesCsv.add(Arrays.asList(values));
//            }
//    } catch (CsvValidationException e) {
//            e.printStackTrace();
//        }
//        return allMatchesCsv;
//    }
//
//
//
//
//    public void renameTable(String table, String newName) {
//        try {
//            ctx()
//                    .alterTable(table)
//                    .renameTo(newName)
//                    .execute();
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//    public boolean clearTable(Table<?> table) {
//        return ctx().delete(table).execute() > 0;
//    }
//
//    public void dropTable(String name) {
//        ctx().dropTable(name).execute();
//    }
//
//    public static <R extends UpdatableRecord<R>> void save(UpdatableRecord<R> record) {
//        record.store();
//    }
//
//
//    public static <R extends UpdatableRecord<R>> void update(UpdatableRecord<R> record) {
//        record.update();
//    }
//
//    public static void delete(DSLContext context, Table<? extends Record> table, Condition condition) {
//        context.delete(table)
//                .where(condition)
//                .execute();
//    }
//
//    public static <R extends UpdatableRecord<R>> void delete(UpdatableRecord<R> record) {
//        record.delete();
//    }
//
//    public static Result<org.jooq.Record> getAll(DSLContext context, Table<? extends org.jooq.Record> table) {
//        return context.select()
//                .from(table)
//                .fetch();
//    }
//
//    public static Result<org.jooq.Record> getFields(DSLContext context, Table<? extends org.jooq.Record> table, SelectFieldOrAsterisk... fields) {
//        return context.select(fields)
//                .from(table)
//                .fetch();
//    }
//
//    public static <R extends org.jooq.Record> R getOne(DSLContext context, Table<R> table, Condition condition) {
//        return context.fetchOne(table, condition);
//    }
//
//    public static <T> void update(DSLContext context, Table<? extends org.jooq.Record> table, Map<Field<T>, T> values, Condition condition) {
//        context.update(table)
//                .set(values)
//                .where(condition)
//                .execute();
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
