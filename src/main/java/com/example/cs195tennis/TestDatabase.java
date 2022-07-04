package com.example.cs195tennis;

import Data.Catalog.MyCatalog;
import Data.Database;
import Data.Listeners.CustomVisitListener;
import Data.QueryParts.BuildQuery;
import com.example.cs195tennis.Util.Tools;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.conf.ParseWithMetaLookups;
import org.jooq.conf.Settings;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultVisitListenerProvider;
import org.jooq.impl.ParserException;
import org.jooq.meta.mysql.MySQLDatabase;
import org.jooq.tools.JooqLogger;


import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.cs195tennis.Util.Tools.print;
import static java.lang.System.out;

import static org.jooq.impl.DSL.*;

public class TestDatabase {
    private static MyCatalog CATALOG = MyCatalog.CATALOG;
    private static Parser parser;
    private static DSLContext ctx;
    private static final JooqLogger log = JooqLogger.getLogger(MySQLDatabase.class);

    public static boolean filterTables(Table<?> e) {
        return
                e.getName().equals("Schemata") ||
                        e.getName().equals("COLUMNS") ||
                        e.getName().equals("OPTIMIZER_TRACE") ||
                        e.getName().equals("PARAMETERS") ||
                        e.getName().equals("PARTITIONS") ||
                        e.getName().equals("REFERENTIAL_CONSTRAINTS") ||
                        e.getName().equals("STATISTICS") ||
                        e.getName().equals("VIEWS");
    }

    Predicate<Table<?>> tableFilter = e -> filterTables(e);

    //============================================================================
    //              Parse String to Query Part
    //              Test Visit Listener
    //              Test Exception Handeling
    //============================================================================
    public static void main(String[] args) throws Exception {
        try {
//            Database.mySqlProperties();
            create();
            createParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        database.setConnection(Database.connect());
        Table<?> Actor = table("actor");

        ctx
                .meta()
                .getSchemas()
                .forEach(out::println);

        Result<Record1<Integer>> result = ctx.selectOne().fetch();


        Query query = query(

                .meta()
                .getSchemas("sakila")
                .stream().toList("1")
        );

        String fieldW_3 = "women_participants.women_participant_id";
        String fieldM_3 = "men_participants.men_participant_id";

        String fieldM3 = "men_participant_id";
        String fieldW3 = "women_participant_id";


        try {
            String fieldW2 = "women_participants.women_participant_id as women_participant_id ";


            String fieldM4 = "men_participant.id, ";
            String fieldM5 = "men_participants.men_participants_id";


            Table<?> join = table("men_participants CROSS JOIN women_participants");

            String fieldW = "women_participants.women_participant_id as women_participant_id ";

            Query query1 =
                    query("" +
                            "SELECT men_participants.first_name, women_participants.first_name " +
                            "FROM women_participants INNER JOIN men_participants on  men_participants.first_name <= women_participants.first_name " +
                            "UNION ALL " +
                            "SELECT women_participants.first_name,  men_participants.first_name " +
                            "FROM women_participants inner join men_participants on women_participant_id <  men_participant_id " +
                            ""
                    );

            Result<Record> rs = ctx.fetch(query1.getSQL()
            );

            out.println(rs);

            Query query2 =
                    query("" +
                            "SELECT men_participant_id, women_participant_id " +
                            "FROM women_participants CROSS JOIN men_participants " +
                            "ORDER BY men_participant_id, women_participant_id " +
                            ""
                    );

            Select<?> select = DSL.select(field("SELECT " + fieldM3 + "," + fieldW3));


        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        print(select(
                        field("women_participant_id"),
                        field("men_participant_id"),
                        rank().over(partitionBy(field("ID_CLASS")).orderBy(field("GRADE").asc()
                                        )
                                )
                                .as(quotedName("Rank"))
                )
                        .from(table(unquotedName("table"))
                        )
                        .orderBy(field(unquotedName("ID_CLASS")
                                ).asc()
                        )
        );


//        print(ctx.fetch(s.getSQL()));
//
//        print(" " +
//                ctx.select()
//                .from(from)
//                        .join(join)
//                .fetch()
//
//        );


//        Result<Record> rs = ctx.select(select)
//                .from(from)
//                .crossJoin(join)
//                        .fetch();


        sqlLogicalOrder(query)
        ;
    }


    //============================================================================
    //              @params:
    //                      name of table that FROM clause will reference,
    //============================================================================
    public static void sqlLogicalOrder(String tableName, String queryString)
            throws SQLDataException {

        parser = Database.getParserWithIgnoreComments();
        Table<?> productTable = null;
        Field<?> selectField = null;

        try {
            ResultQuery query = parser.parseResultQuery(queryString);
            writeSqlQueryToFile(query);
        } catch (ParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            productTable = lookupTable(tableName);

            Field<?>[] productTableFields =
                    getAllFields(productTable.getName()
                    );


            selectField = getField(tableName, "product_name");


            getColumnSize(tableName, selectField.getName());

        } catch (Exception e) {
            e.printStackTrace();

        }

        sqlLogicalOrder(queryString, productTable, selectField);
    }

    //============================================================================
    //              @params:
    //                      Sql queryString,
    //                      table reference,
    //                      column reference
    //============================================================================
    private static void sqlLogicalOrder(String sql, Table<?> table, Field<?> field) {
        Query query = null;

        try {
            query = parser.parseQuery(sql);

            String formatted =
                    ctx.fetch(
                                    query.getSQL()
                            )
                            .format();

//            writeSqlQueryToFile("SqlStringBeforeFetch", formatted);

        } catch (Exception e) {
            e.printStackTrace();

        }
        sqlLogicalOrder(query);

    }

    //============================================================================
    //              @params:
    //                      Built Query
    //============================================================================
    private static void sqlLogicalOrder(Query query) {
        try {
            Tools.title("                             Fetch Records returned by Query String execution");

            out.println(query.getSQL());

            Result<Record> result = ctx.fetch(query.getSQL());

            String tableFormat = result.format();

            Tools.title("                       Result<Record> size " + result.size() + " Record Values: ");

            writeResultsToFile("resultAggregateBeforeGroupBy", tableFormat);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sqlLogicalOrder(String[] tablesArray)
            throws SQLDataException {

        Field<?>[] selectedFields =
                getFieldsFromJoinedTables(tablesArray
                );
        BuildQuery.build(

                query("" +
                        "SELECT count(*) " +
                        "FROM products " +
                        "WHERE product_id = 1 "
                )
        );


//
//        parser.parse(
//                query.getSQL()
//        );

    }


    //============================================================================
    //                          Schema Getters
    //============================================================================
    public static List<Schema> getSchemaList() {
        return
                DSL.using(Database.connect()
                        )
                        .meta()
                        .getSchemas()
                        .stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()
                        )
                ;
    }
    public static Schema getSchema(Schema schemaName) {
        if(!isValid(schemaName)) {
            return null;
        }

        try {
            List<Schema> schemaList =
                    getSchemaList()
                            .stream()
                            .filter(e -> e.getName()
                                    .equals(schemaName
                                    )
                            )
                            .toList();
            if (schemaList.size() == 1) {
                return schemaList.get(0)
                        ;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //============================================================================
    //                          Table<?> Getters
    //============================================================================
    private static Table<?> getTable(String schemaString, String tableName)
            throws SQLException {

        Schema schema =
                getSchema(
                        schema(schemaString
                        )
                );

        return schema
                .getTable(tableName)
                ;
    }


    public static Table<?> lookupTable(String tableName) throws Exception {
        if (tableName == null || tableName.hashCode() == 0 || tableName.hashCode() == "".hashCode()) {
            throw new Exception("Line 279 Lookup Table");
        }

        Meta meta = ctx.meta(table(tableName));

        DSLContext c = ctx
                .configuration()
                .derive((MetaProvider) () -> meta)
                .deriveSettings(s ->
                        s.withParseWithMetaLookups(
                                ParseWithMetaLookups.THROW_ON_FAILURE
                        )
                )
                .dsl();

        Field<?>field =
                meta
                        .getTables("speed_dating")
                        .stream()
                        .toList()
                        .get(0)
                        .field(1)
                ;



        try {
            c.parser().parseSelect("select id from book");
        }
        catch (ParserException e) {

            e.printStackTrace();
        }
        return table("speed_dating");
    }

    private static void writeSqlStringToTxt(String sql) {

    }

    public static List<Table<?>> getTableList(String schemaName) {
        Schema schema = getSchema(schema(schemaName)
        );
        return new ArrayList<>(getSchema(schema)
                .getTables()
        )
                ;
    }

    //===================================================================================
    //                          Fields
    //===================================================================================
    public static Field<?> getField(String tableName, String fieldName) throws Exception {
        Field<?> field =
                lookupTable(tableName
                )
                        .field(fieldName
                        );
        print("Single Field:" + field);
        return field;
    }

    public static Field<?>[] getFields(String tableName, String[] fields) throws Exception {
        return
                lookupTable(tableName
                )
                        .fields(fields
                        )
                ;
    }

    public static Field<?>[] getAllFields(String tableName) throws Exception {
        Field<?>[] fields =
                lookupTable(tableName
                )
                        .fields(
                        );

        print("\nTable: " + tableName +
                "\nNumber of Fields: " + fields.length +
                "\nField names: " + Arrays.toString(fields)
        );
        return fields;
    }

    public static Field<?>[] getFieldsFromJoinedTables(String[] tablesArray) {
        return null;
    }

    //==============================================================================
    //                          Column / Field Values
    //                          Number of Values in Field / Column
    //==============================================================================

    /**
     * @param tableName  name of table that contains the field
     * @param columnName name of column being used as select field of query
     * @return number of values stored in @code{columnName}
     */
    public static int getColumnSize(String tableName, String columnName) {
        String selectString = "SELECT count(*), " + columnName;
        String fromString = "FROM " + tableName;


        Result<Record> rs =
                ctx.fetch(
                        query("" +
                                selectString +
                                fromString
                        ).getSQL()
                );
        out.println("Column: " + columnName +
                " number of records in column: " +
                rs.get(0).size()
        );

        return rs
                .stream()
                .toList()
                .get(0)
                .size()
                ;

    }

    private static void getColumnSize(String fromString, Field<?>[] productTableFields) {

        Select<?> select = DSL.select(productTableFields);

        Table<?> from = DSL.table(fromString);

        Result<Record> rs =
                ctx.fetch(
                        query("" +
                                select +
                                from
                        ).getSQL()
                );

        rs.forEach(e ->
                print(
                        e.size()
                )
        );
    }

    public static Result<Record> getAllColumnValues(String tableName) {
        create()
        ;

        Query query =
                query("" +
                        "SELECT * " +
                        "FROM " + tableName
                );

        String sql = query.getSQL();

        Result<Record> rs = ctx.fetch(sql);

        return rs
                ;
    }

    public static Result<Record> getColumnValues(String tableName, String[] fieldNames) throws Exception {
        create()
        ;

        Field<?>[] fieldsArray = getFields(tableName, fieldNames);

        Query query =
                query("" +
                        "SELECT " + fieldsArray +
                        " FROM " + tableName
                );

        String sql = query.getSQL();

        Result<Record> rs = ctx.fetch(sql);

        return rs
                ;
    }

    public static Result<Record> getColumnValue(String tableName, String fieldName) throws Exception {

        Field<?> field = getField(tableName, fieldName);

        String selectString = "SELECT " + field.getName();
        String fromString = " FROM " + tableName;

        Query query =
                query("" +
                        selectString +
                        fromString
                );

        String sql = query.getSQL();

        Result<Record> rs = ctx.fetch(sql);

        return rs
                ;
    }

    public static Result<? extends Record1<?>> getColumnValue(Field<?> select, String from) {
        return
                ctx
                        .select(select)
                        .from(from)
                        .fetch();
    }

    //======================================================================================
    //                          Get Example Query Strings
    //=======================================================================================

    /**
     * @return List of Query String with correct syntax and query structure
     */
    private static List<String> getCorrectQueries() {
        List<String> list = new ArrayList<>();
        int i = 0;

        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT product_name, count(*) " +
                "FROM products " +
                "GROUP BY product_name " +
                "HAVING count(*) > 0 "
        );
        print(
                parser.parseQuery(list.get(i++)
                ).getSQL()
        );
        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT product_name, count(*)\n" +
                "FROM products\n" +
                "GROUP BY product_name\n" +
                "ORDER BY count(*) DESC"
        );
        print(
                parser.parseQuery(list.get(i++)
                ).getSQL()
        );
        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT product_name, MAX(product_id), count(*)\n" +
                "FROM products\n" +
                "GROUP BY product_name"

        );
        print(
                parser.parseQuery(list.get(i++)
                ).getSQL()
        );
        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT product_name || ' ' || MAX(product_id), count(*)\n" +
                "FROM products\n" +
                "GROUP BY product_name"
        );
        print(
                parser.parseQuery(list.get(i++)
                ).getSQL()
        );
        //-- Correct: Because aggregate functions are calculated
        //-- *after* GROUP BY but *before* HAVING, so they're
        //-- available to the HAVING clause.
        list.add("" +
                "SELECT MAX(product_name || ' ' || product_id), count(*)\n" +
                "FROM products\n" +
                "GROUP BY product_name"
        );
        print(
                parser.parseQuery(list.get(i++)
                ).getSQL()
        );
        return list;
    }

    /**
     * @return List of Query String with incorrect syntax and query structure
     */
    private static List<String> getIncorrectQueries() {
        List<String> list = new ArrayList<>();

        //-- Wrong: Because aggregate functions are calculated
        //-- *after* GROUP BY, and WHERE is applied *before* GROUP BY
        //
        //-- logical order         -- available columns after operation
        //-------------------------------------------------------------
        //FROM products            -- products.*
        //WHERE ??? > 1            -- products.* (count not yet available!)
        //GROUP BY product_name      -- product_name (customer.* for aggs only)
        //<aggregate> count(*)     -- product_name, count
        //SELECT product_name, count -- product_name, count
        list.add(
                "SELECT product_name, count(*) " +
                        "FROM products " +
                        "WHERE count(*) > 0 " +
                        "GROUP BY product_name "
        );
        parser.parseQuery(
                        list.get(0)
                )
                .getSQL()
        ;


        //         * Wrong:
//         * Because the GROUP BY clause creates groups of
//         * product names, and all the remaining products columns
//         * are aggregated into a list, which is only visiblbe to
//         * aggregate functions
//         *
//         * -- logical order         -- available columns after operation
//         * ----------------------------------------------------------------
//         * FROM products            -- products.*
//         * GROUP BY product_name      -- product_name (products.* for aggs only)
//         * <aggregate> count(*)     -- product_name, count
//         * -- product_name, count (product_id removed)
//         *  SELECT product_name, ???, count
//
        list.add(
                "SELECT product_name, product_id, count(*)\n" +
                        "        FROM products\n" +
                        "        GROUP BY product_name"
        );
        parser.parseQuery(
                        list.get(1)
                )
                .getSQL()
        ;

        //-- Wrong: Because we still cannot access the last name column
        //-- which is in that list after the GROUP BY clause.
        //
        //-- logical order         -- available columns after operation
        //-----------------------------------------------------------------
        //FROM products            -- products.*
        //GROUP BY product_name    -- product_name (products.* for aggs only)
        //<aggregate> count(*)     -- product_name, count
        //                         -- product_name, count (last_name product_id)
        //SELECT product_name || ' ' || ???, count
        list.add("" +
                "SELECT product_name || ' ' || product_id, count(*) " +
                "FROM products " +
                "GROUP BY product_name"
        );

        parser.parseQuery(
                        list.get(2)
                )
                .getSQL()
        ;

        return list;
    }

    //============================================================================
    //                          Parser Configuration
    //                          Parse Listener Settings
    //============================================================================
    private static void createParser() {
        Settings parseSettings =
                Database
                        .getParseSettings();

        parser =
                Database
                        .getParserWithIgnoreComments()
        ;

    }

    //======================================================================================
    //                           Get Database Connection
    //=======================================================================================

    /**
     * @return DSLContext ctx
     * • to JDBC MySQL Driver
     */
    static DSLContext create() {

        Connection connection =
                Database.connect("speed_dating");


        Configuration configuration =
                Database.getVisitConfiguration();


        ctx = DSL.using(configuration);
        return ctx;
    }

    public static void printAllSchemas() {
        MyCatalog.CATALOG
                .getSchemas()
                .forEach(e ->
                        print(e)
                );
        List<Schema> schema = CATALOG.getSchemas();
        schema
                .stream()
                .map(e ->
                        e.getTables()
                )
                .collect(Collectors.toList()
                )
                .forEach(t ->
                        print(t)
                );
    }

    //======================================================================================
    //                           Output to txt files
    //=======================================================================================
    private static void writeResultsToFile(String schema, String result)
            throws IOException {

        Schema sch = getSchema(schema(schema)
        );
        if (sch == null) {
            return;
        }
        StringBuilder sb = new StringBuilder("/com/example/cs195tennis/text_files/");
        File file = new File(schema + ".txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer.write(result);

        }
    }

    private static void writeTableList(String tableName)
            throws Exception {

        Table<?> table = lookupTable(tableName);

        if (!isValid(table)) {
            return;
        }

        File file = new File(tableName.concat(".txt")
        );

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {


            String formatted =
                    ctx
                            .fetch(tableName)
                            .format();


            writer.write(formatted);
        }
        ;
    }

    private static void writeSqlQueryToFile(Query query)
            throws IOException {

        if (query == null || !query.isExecutable()) {
            return;
        }

        File file = new File(query + ".txt");

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer.write(
                    ctx
                            .fetch(query.getSQL()
                            )
                            .formatChart()
            );
        }
    }

    private static void logQuery(List<Field<?>> fields)
            throws IOException {


        File file = new File("selectQuery.txt");

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer
                    .write(
                            ctx
                                    .select(fields)
                                    .fetch()
                                    .formatChart()
                    );
        }
    }

    private static void writeInformationSchemaToTxt(String schemaName, Predicate<Table<?>> predicate)
            throws IOException {

        if (schemaName.hashCode() == 0) {
            return;
        }

        List<Schema> schemaList = ctx.meta().getSchemas(schemaName);

        DSLContext c = ctx
                .configuration()
                .derive(() -> ctx.meta(schema(schemaName))
                )
                .deriveSettings(s ->
                        s.withParseWithMetaLookups(
                                ParseWithMetaLookups.THROW_ON_FAILURE
                        )
                )
                .dsl();

        SelectWhereStep<?> selectWhere =
                ctx.selectFrom(schemaName);

        Select<?> select =
                ctx.parser().parseSelect(selectWhere.getSQL()
                );

        Select<?> selectStatement =
                select(select.getSelect());

        String sqlString =
                selectStatement.getSQL();

        String formattedString =
                ctx.fetch(sqlString).format();


        writeSqlStringToTxt(formattedString);

        File file = new File(schemaName + ".txt");

        Files.deleteIfExists(file.toPath());

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer.write(formattedString);
        }
    }


    //======================================================================================
    //                           temporary exception handling
    //=======================================================================================
    private static boolean isValid(Table<?> table) {
        DSL.using(Database.connect())
                .configuration()
                .derive(() -> ctx.meta()
                )
                .derive(new DefaultVisitListenerProvider(new CustomVisitListener())
                );

        if (table == null ||
                ctx.select(field("count(*) > 0 ")
                        )
                .from(table)
                .fetchOne(0, int.class)  == 0) {
            return false;
        }

        if(table instanceof Table) {

            int numberOfTables =
                    ctx
                            .select(count())
                            .from(table(" information_schema.tables ")
                            )
                            .where("table_schema = " + table)
                            .fetchOne(0, int.class);

            return numberOfTables == 1;
        }
        return true;
    }

    private static boolean isValid(Schema schema) {
        if (schema == null || schema.getName().isBlank()) {
            return false;
        }

        ctx
                .selectCount()
                .from(ctx
                        .meta(schema)
                        .getTables()
                );
        ;

    return true;
    }

    private static void parseSql(Parser parser, Query query) {
        Tools.title("                       Jooq DSL and JDBC Parse Connection Settings ");

        Settings parseSettings = Database.getParseSettings();

        Database.printParseSettings(parseSettings);

        Tools.title("                       Jooq DSL and JDBC Configuration Parser reference ")
        ;
        parser = Database
                .getParserWithIgnoreComments(
                        )
        ;
    }
}





