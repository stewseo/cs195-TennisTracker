//package com.example.cs195tennis.Util;
//
//import Database.MyCatalog;
//import com.example.cs195tennis.Data.Dao.DataHandeler;
//import com.example.database.Listeners.StatisticsListener;
//import com.example.database.schema.Public;
//import org.jooq.*;
//import org.jooq.Record;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.sql.SQLDataException;
//import java.sql.SQLException;
//import java.util.*;
//
//import static com.example.database.db_connection.Connection.Database.ctx;
//import static com.example.database.schema.Public.SCHEMA;
//import static java.lang.System.out;
//import static org.jooq.impl.DSL.*;
//import static org.jooq.impl.SQLDataType.VARCHAR;
//
//public class Tools {
//
//
//    static DataHandeler dataHandeler = new DataHandeler();
//
//    private static final String driver = "org.sqlite.JDBC";
//    public static String url() {
//        return "jdbc:sqlite:Database/wta-tournaments.sqlite";
//    }
//
//    public static Field<?>[] fields(String[] strings) {
//        Field<?>[] fields = new Field[strings.length];
//
//        for(int i =0; i < strings.length; i++) {
//            fields[i] = field(strings[i]);
//        }
//        return fields;
//    }
//
//    public static Schema schema(String tableName) {
//        out.println(tableName);
//        return
//                ctx()
//                        .meta()
//                        .getTables()
//                        .stream().filter(t ->
//                                t.getName()
//                                        .equalsIgnoreCase(tableName)
//                        )
//                        .toList()
//                        .get(0)
//                        .getSchema();
//    }
//
//    public static Table<?> table(String tableName) {
//
//        Schema schema = schema(tableName);
//        out.println(schema.getName());
//
//        return
//                ctx()
//                        .meta()
//                        .filterSchemas(sch ->
//                                sch.getName()
//                                        .equalsIgnoreCase(schema.getName())
//                        )
//                        .getTables()
//                        .stream()
//                        .filter(t ->
//                                t.getName().equals(tableName)
//                        )
//                        .toList()
//                        .get(0);
//    }
//
//    public static void title(String title) {
//        String dashes = "=============================================================================================";
//
//        System.out.println();
//        System.out.println(title);
//        System.out.println(dashes);
//        System.out.println();
//    }
//
//    public static Result<Record> getRecordsFromTableName(String tableName) {
//        return ctx()
//                .select(table(tableName).fields())
//                .from(table(tableName))
//                .fetch();
//    }
//
//    public static List<? extends UniqueKey<?>> getAllPrimaryKeys() {
//        List<? extends UniqueKey<?>> pkeys_from_meta =
//                ctx()
//                        .meta()
//                        .getTables()
//                        .stream()
//                        .map(Table::getPrimaryKey)
//                        .filter(Objects::nonNull)
//                        .toList();
//
//        List<? extends UniqueKey<?>> pkeys_from_schema =
//                SCHEMA
//                        .getTables()
//                        .stream()
//                        .map(Table::getPrimaryKey)
//                        .filter(Objects::nonNull)
//                        .toList();
//
//
//        if(!pkeys_from_meta.equals(pkeys_from_schema)){
//            print("not equal primary key lists schema: " + pkeys_from_schema.size()
//            + "  run time meta: " + pkeys_from_meta.size()
//            );
//        }
//        return pkeys_from_meta;
//    }
//
//    public static UniqueKey<?> getPrimaryKey(String tableName) {
//
//        List<? extends UniqueKey<?>> primaryKeys =
//                SCHEMA.getTables()
//                        .stream()
//                        .filter(e -> e.getName().equals(tableName))
//                        .map(Table::getPrimaryKey)
//                        .filter(Objects::nonNull)
//                        .toList();
//
//
//        assert primaryKeys.size() == 1;
//
//        return primaryKeys.get(0);
//
//    }
//
//    public static void primaryKeyInfo(UniqueKey<?> primaryKey) {
//        String pkName = primaryKey.getName();
//        Table<?> table = primaryKey.getTable();
//        List<? extends ForeignKey<?, ?>> pkReferences = primaryKey.getReferences();
//
//        print("Primary Key Name: " + pkName + " Primary Key Table: " + table.getName() + " Foreign Key References of Primary Key: " + pkReferences);
//
//    }
//
//    public static boolean testAllTableFields(Table<?> table) {
//        int n = Public.SCHEMA.getTables().indexOf(table);
//
//        Public.SCHEMA.getTables().forEach(e->{
//            print(" name " + e.getName());
//            print(" primary key" + e.getPrimaryKey());
//            print(" references " + e.getReferences());
//            print(" fields " + e.fieldsRow());
//        });
//
//        return true;
//    }
//
//    public static Schema getSchema(String schemaName) {
//        return ctx()
//                .meta()
//                .getSchemas()
//                .stream().
//                filter(e -> e.getName()
//                        .equals(schemaName)
//                ).toList()
//                .get(0);
//    }
//
//    public static Row getFieldsRow(String tableName){
//        return
//                table(tableName)
//                        .fieldsRow();
//    }
//
//    public void alterColumnDataType(Table<?> table, Field<?> field, DataType<?> dataType){
//
//        ctx()
//                .alterTable(table)
//                .alter(field)
//                .set(dataType)
//                .execute();
//
//    }
//
//    public static List<List<String>> getRecordsFromCsv(String pathToCsv) throws IOException {
//        Result<Record> rs = ctx().newResult();
//
//        List<String> paths =
//                java.nio.file.Files
//                        .walk(Paths.get(pathToCsv))
//                        .filter(Files::isRegularFile)
//                        .map(Path::toString)
//                        .toList();
//
//        return dataHandeler
//                .parseCsvToListString(paths.get(0));
//
//    }
//
//
//    public static void tableInfo(String tableName) throws SQLException {
//        StringBuilder tableInfoSb = new StringBuilder("Table: " + tableName + " info ");
//        Table<?> table = MyCatalog.CATALOG.getTables(tableName);
//
//        if(table == null){
//            return;
//        }
//
//        UniqueKey<?> primaryKey = table.getPrimaryKey();
//        verifyPrimaryKey(tableInfoSb, primaryKey, table);
////        tableInfoSb.append("\nTable ").append(table.getName()).append(" Primary Key: ").append(primaryKey);
//
//        List<ForeignKey<?, ?>> foreignKeys = SCHEMA.getForeignKeys(tableName);
//        verifyForeignKeys(tableInfoSb, foreignKeys, table.getName());
//
//        List<Index> indexes = SCHEMA.getIndexes();
//        verifyIndexes(tableInfoSb,indexes, table);
//
//        out.print(tableInfoSb);
//    }
//
//    private static void verifyPrimaryKey(StringBuilder checkKeys, UniqueKey<?> primaryKey, Table<?> table) throws SQLException {
//
//        UniqueKey<?> primaryKeyFromMeta =
//                SCHEMA.getMetaPrimaryKey();
//
//        if(table.getPrimaryKey() != null && table.getPrimaryKey().isPrimary()) {
//
//            if (!primaryKey.getName().isBlank()) {
//                checkKeys.append("\nPrimary Key: ").append(primaryKey.getName());
//                checkKeys.append(primaryKey.getName());
//            }
//        }
//    }
//
//    private static boolean verifyForeignKeys(StringBuilder checkKeys,List<ForeignKey<?, ?>> foreignKeys , String tableName) throws SQLException {
//
//        List<ForeignKey<?, ?>> foreignKeysFromMeta = SCHEMA.getMetaForeignKeys();
//
//        if (!foreignKeys.isEmpty()) {
//            checkKeys.append("\nForeign Keys: ").append(foreignKeys);
//        }
//
//        return true;
//    }
//
//    private static boolean verifyIndexes(StringBuilder checkKeys, List<Index> indexes, Table<?> table) {
//
//        try {
//            List<Index> indexesFromMeta = SCHEMA.getMetaIndexes();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if (!indexes.isEmpty()) {
//            checkKeys.append("\nIndexes: ").append(indexes);
//        }
//        return true;
//    }
//
//    public static List<String> getTableNames() {
//        return SCHEMA
//                .getTables()
//                .stream()
//                .map(Named::getName)
//                .toList();
//    }
//
//    public static <T> void print(List<T> o) {
//        if(o.isEmpty()){
//            return;
//        }
//        out.println("List size: "+ o.size());
//    }
//
//    public static <T> void print(String o) {
//        if(o.isEmpty()){
//            return;
//        }
//        out.println(o);
//        if (StatisticsListener.STATISTICS.keySet().size() > 0) {
//            System.out.println("statistics key set: " + StatisticsListener.STATISTICS.keySet());
//
//            if (StatisticsListener.STATISTICS.entrySet().size() > 0) {
//                System.out.println("entry set: " + StatisticsListener.STATISTICS.entrySet());
//            }
//            Arrays.stream(ExecuteType.values()).forEach(type -> {
//                out.println(type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//            });
//        }
//    }
//
//    public static <T> void print(Result<Record> rs) {
//        if(rs.isEmpty()){
//            return;
//        }
//        out.println("number records: " + rs.size());
//        if (StatisticsListener.STATISTICS.keySet().size() > 0) {
//            System.out.println("statistics key set: " + StatisticsListener.STATISTICS.keySet());
//
//            if (StatisticsListener.STATISTICS.entrySet().size() > 0) {
//                System.out.println("entry set: " + StatisticsListener.STATISTICS.entrySet());
//            }
//            Arrays.stream(ExecuteType.values()).forEach(type -> {
//                out.println(type.name() + " " + StatisticsListener.STATISTICS.get(type) + " executions");
//            });
//        }
//    }
//
//    public static <T> void print(Table<?> t) {
//        out.println("table: " + t.getName());
//        out.println("Primary Key: " + t.getPrimaryKey());
//        out.println("FieldRow size: " + t.fieldsRow().size());
//        out.println("Fields: " + t.fieldsRow());
//    }
//
//    public static <T> void print(Select<?> select) {
//
//        out.println(select);
//    }
//
//    public static <T> void print(Schema schema) {
//        out.println("Schema name: " + schema);
//        out.println("Tables in schema: "+ schema.getTables().size());
//    }
//
//    public static <T> void print(DSLContext ctx) {
//        out.println("parsing connection: " + ctx.parsingConnection());
//
//    }
//
//    public static <T> void print(Record record) throws SQLDataException {
//        if(record == null){
//            throw new SQLDataException();
//        }
//        out.println("Degrees/Arity/Columns/Fields of Record: "+ record.size());
//    }
//
//    public static <T> void print(Queries queries) throws SQLDataException {
//        if(queries.fetchMany().size() == 0){
//            throw new SQLDataException();
//        }
//
//        out.println("Queries: "+ queries.$queries().size());
//    }
//
//    public static <T> void print(Object o) {
//        out.println("o: " + o);
//    }
//}
//
