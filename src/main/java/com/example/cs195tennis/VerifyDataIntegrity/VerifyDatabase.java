package com.example.cs195tennis.VerifyDataIntegrity;

import Database.Connection.Database;
import Database.Listeners.CustomVisitListener;
import com.example.cs195tennis.Util.Tools;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.conf.ParseWithMetaLookups;
import org.jooq.conf.Settings;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.NoDataFoundException;
import org.jooq.impl.*;
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

import static com.example.cs195tennis.Util.Tools.print;
import static java.lang.System.out;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public abstract class VerifyDatabase {

    private static Parser parser;
    public static DSLContext ctx;
    protected static final JooqLogger log = JooqLogger.getLogger(VerifyDatabase.class);

    static Predicate<Schema> schemasInCatalog = schema ->
            schema.getName().equals("sakila") ||
                    schema.getName().equals("atpwtagrandslams") ||
                    schema.getName().equals("speed_dating") ||
                    schema.getName().equals("my_guitar_shop") ||
                    schema.getName().equals("mysql") ||
                    schema.getName().equals("sys") ||
                    schema.getName().equals("information_schema");

    public static void main(String[] args) {


//                        .orderBy(Actor.FIRST_NAME);
//
//        log.info("Result Query:\n" + rq);
//
//        log.trace("trace ");
//        for (var r : ctx
//                .select(Actor.FIRST_NAME, Actor.LAST_NAME)
//                .from("ACTOR ")
//                .where("Actor.ACTOR_ID < 5")
//        ) {
//            print("Actor: %s %s".formatted(r.value1(), r.value2()));
//        }
//
//        ctx.select(FILM.FILM_ID, FILM.TITLE)
//                .from("FILM ")
//                .limit(5)
//                .forEach(r -> print("Film %s: %s".formatted(r.value1(), r.value2()
//                                )
//                        )
//                );
//
//
//
//        try {
//             table_m = "men_participants";
//
//             table_f = "women_participants";
//
//            String aliasSelectString_ = "women_participants.women_participant_id as wpid";
//
//            String aliasSelectString_m = "women_participants.women_participant_id as wpid";
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//        try {
//            Query query =
//                    query("" +
//                            "SELECT men_participants.first_name, women_participants.first_name " +
//                            "FROM women_participants INNER JOIN men_participants on  men_participants.first_name <= women_participants.first_name " +
//                            "UNION ALL " +
//                            "SELECT women_participants.first_name,  men_participants.first_name " +
//                            "FROM women_participants inner join men_participants on women_participant_id <  men_participant_id " +
//                            ""
//                    );

//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
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
        } catch (ParserException e) {
            e.printStackTrace();
        }

        try {
            productTable = lookupTable(tableName);

            getAllFields(productTable.getName());

            getField(tableName, "product_name");

//            getColumnSize(tableName);

        } catch (Exception e) {
            e.printStackTrace();

        }

        sqlLogicalOrder("select", "from", "condition");
    }

    //============================================================================
    //              @params:
    //                      Sql queryString,
    //                      table reference,
    //                      column reference
    //============================================================================
    private static void sqlLogicalOrder(String tableString1, String tableString2, String conditionString) {
        Query query = null;
        try {
            Table<?> table1 = table(tableString1);

            Table<?> table2 = table(tableString2);

            Condition condition = condition(conditionString);

            table1
                    .innerJoin(table2)
                    .on(condition)
            ;
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //============================================================================
    //              @params:
    //                      Built Query
    //============================================================================
    private static void sqlLogicalOrder(Query query) {
        try {
            Tools.title("                           Fetch Records returned by Query String execution");
            if (!query.isExecutable()) {
                throw new DataAccessException("Query not executable: " + query.toString()
                );
            }
            out.println(query.getSQL());

            Result<Record> result = ctx.fetch(query.getSQL());

            String tableFormat = result.format();

            Tools.title("                           Result<Record> size " + result.size() + " Record Values: ");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sqlLogicalOrder(String[] tablesArray)
            throws SQLDataException {

        Field<?>[] selectedFields =
                getFieldsFromJoinedTables(tablesArray
                );

    }


    //============================================================================
    //              Predicate filters for catalog
    //============================================================================

    Predicate<Table<?>> informationSchemaFilter = VerifyDatabase::selectInformationSchemaTables;

    public static boolean selectInformationSchemaTables(Table<?> e) {
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


    //============================================================================
    //                          Schema Getters
    //============================================================================
    public static List<Schema> getSchemaList() {
        List<Schema> schemaList = DSL.using(Database.connect()
                )
                .meta()
                .getSchemas()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList()
                );
    println(schemaList);
    return schemaList;
    }

    public static Schema getSchema(String schemaName) {

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
                return schemaList.get(0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //============================================================================
    //                          Table<?> Getters
    //============================================================================
    private static Table<?> getTable(String schemaName, String tableName)
            throws SQLException {

        print(tableName +" " + schemaName);
        return create(schemaName)
                .meta()
                .getSchemas(schemaName).get(0)
                .getTable(tableName);

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

        Field<?> field =
                meta
                        .getTables("speed_dating")
                        .stream()
                        .toList()
                        .get(0)
                        .field(1);


        try {
            c.parser().parseSelect("select id from book");
        } catch (ParserException e) {

            e.printStackTrace();
        }
        return table("speed_dating");
    }


    public static List<Table<?>> getTableList(String schemaName) {

        Schema schema = getSchema("my_guitar_shop");

        return schema.getTables();
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
     * @return List of Correct query strings used to Check the Lexical vs Logical SQL order
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
     * @return List of Incorrect query strings used to Check the Lexical vs Logical SQL order
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
    protected static DSLContext create(String dbName) {

        Connection connection =
                Database.connect(dbName);


        Configuration configuration =
                Database.getVisitConfiguration();


        ctx = DSL.using(configuration);
        return ctx;
    }

    List<Table<?>> listOfTablesInSchema(String schemaName) {
        List<Table<?>> listOfTables = ctx
                .meta()
                .getSchemas(schemaName)
                .get(0)
                .getTables()
                .stream()
                .collect(Collectors.toList()
                );
        if (listOfTables.isEmpty()) {
            throw new DataAccessException("no tables in schema");
        }
        return listOfTables;
    }


    //======================================================================================
    //                       String to Query Object
    //                       String to ResultQuery Object 
    //=======================================================================================
    protected Query stringToQuery(String queryString) {
        Query query1 = query(
                "" +
                        "SELECT customers.first_name as name " +
                        "FROM customers " +
                        "WHERE first_name LIKE  '_m' "
        );
        return query1;
    }

    //======================================================================================
    //                Check syntax of valid mySql String against Query Sql String
    //=======================================================================================
    void explainQueryPlan(Explain explain) {

    }

    //======================================================================================
    //                Check syntax of valid mySql String against Query Sql String
    //=======================================================================================
    void verifyQuerySyntax(Query query, String correctSqlSyntax) throws IOException {
        ResultQuery resultQuery = ctx.resultQuery(query.getSQL());

        resultQuery = ctx.resultQuery(correctSqlSyntax);

        Explain explain = ctx.explain(resultQuery);

        QueryPart queryPart = list(query);

        queryAndSqlStringToTxtFile(query, correctSqlSyntax);

    }

    void verifyQueryResults(ResultQuery resulyQuery) throws FileNotFoundException {
        Explain explain = ctx.explain(resulyQuery);
        writeResultQueryPlanToTxt(explain);
    }

    boolean constraintsToTxt(List<Table<?>> listOfTables) throws Exception {
        writeContraintsToText(listOfTables);
        return true;
    }



    int getSteps(Cursor cursor) {
        int count = 0;
        while(cursor.hasNext()){
            cursor.fetchNext();
            count++;
        }
        return count;
    }

    public static void title(Object title) {
        println("");
        println(title);
        println("-".repeat(("" + title).length()));
    }

    public static <T> T println(T t) {
        System.out.println(t);
        return t;
    }

    //======================================================================================
    //                       Check if Database has Data
    //=======================================================================================
    private static boolean tableHasFields(Table<?> table) {
        DSL.using(Database.connect())
                .configuration()
                .derive(() -> ctx.meta()
                )
                .derive(new DefaultVisitListenerProvider(new CustomVisitListener())
                );

        int numberOfTables =
                ctx.select(field("count(*) > 0 ")
                        )
                        .from(table)
                        .fetchOne(0, int.class);

        return numberOfTables > 0;
    }

    private static boolean schemaHasTables(Schema schema) {
        if (schema == null || schema.getName().isBlank()) {
            return false;
        }

        Cursor cursor = ctx
                .selectCount()
                .from(ctx.meta(schema)
                        .getTables()
                )
                .fetchLazy();
        
        return cursor.hasNext();
    }

    //======================================================================================
    //                           Write output to txt file
    //=======================================================================================
    void tablesToTxtFile(String schemaName)
            throws IOException {
        if (schemaName == null || schemaName.isBlank()) {
            throw new NoDataFoundException();
        }
        log.debug("Debug");


        File file = createTxtFile("sakila_schema.txt");
        Result<Record1<String>> result = null;
        List<Table<?>> tablesInSchema = null;

        try {
            tablesInSchema = listOfTablesInSchema(schemaName);

            file = createTxtFile("sakila_schema.txt");

            Files.deleteIfExists(file.toPath());

        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer.write("Schema: " + schemaName +
                    "\nNumber of tables in schema: " + tablesInSchema.size());

            tablesInSchema.forEach(e -> {
                try {
                    writer.write("" +
                            "\n\nTable: " + e.getName() +
                            "\nRecords: "+ ctx.fetchCount(e) +
                            "\nNumber of fields in table: " + e.fieldsRow().size() +
                            "\nField names in table: " + e.fieldsRow()
                    );
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });

        }

        log.trace("Trace");


    }

    void tablesToTxtFile(List<Table<?>> tablesInSchema)
            throws IOException {

        if (tablesInSchema == null || tablesInSchema.isEmpty()) {
            throw new org.jooq.exception.NoDataFoundException();
        }

        File file = null;

        try {

            file = createTxtFile("sakila_table_info");

            Files.deleteIfExists(file.toPath());

        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            tablesInSchema.forEach(e-> {
                try {
                    writer.write("" +
                            "Schema: " + e.getSchema().getName() + ", Table: " + e.getName()+
                            " Fields: "+ e.fields().length + " Records: "+ ctx.fetchCount(e)
                    );
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });

        }

        log.info("Table name, number of fields in each table, number of records in each table logged to sakila_table_info.txt");

    }
    private void queryAndSqlStringToTxtFile(QueryPart queryPart, String sqlString) throws IOException {
        File file = null;

        try {
            file = createTxtFile("queryParts_mySqlString_syntax.txt");

            Files.deleteIfExists(file.toPath());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }


        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer.write("QueryParts: \n" + queryPart);
            writer.write("\n\nSqlString: \n" + sqlString);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Logged parsed QueryPart object and mySql String to queryParts_mySqlString_syntax.txt");
    }

    private void writeResultQueryPlanToTxt(Explain explainResultQuery)
            throws FileNotFoundException {

        File file = null;
        Result<Record> result = null;

        file = createTxtFile("sql_syntax.txt");

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer.write("Estimated Plan:\n " + explainResultQuery.plan());
            writer.write("\nEstimated Rows: " + explainResultQuery.rows());
            writer.write("\nEstimated Cost:  " + explainResultQuery.cost());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeSyntax(String fileName, Query query){
        StringBuilder m5QueryBuilder = new StringBuilder(
                fileName)
                .append("\nDynamic Sql Parts:\n")
                .append(query.getSQL()
                )
                .append("\n")
                .append("\nParams:\n")
                .append(query.getParams()
                )
                .append("\nBind Values:\n")
                .append(query.getBindValues()
                )
                ;

        File file = createTxtFile(fileName.concat(".txt"));

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer.write(m5QueryBuilder.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String build(Query query){

        Result<Record> result = ctx.fetch(query.getSQL());

        ResultQuery<Record> resultQuery = resultQuery(query.getSQL());

        Explain explainResultQuery = ctx.explain(resultQuery(query.getSQL()));

        StringBuilder sb = new StringBuilder();

        sb
                .append("\n\nQuery Sql String:\n")
                .append(resultQuery.getSQL()
                )
                .append("\nThe formatted plan as returned by the database:\n " )
                .append(explainResultQuery.plan()
                )
                .append("\nThe cost the database associated with the execution of the query:  " )
                .append(explainResultQuery.cost()
                )
                .append("\nThe estimated number of rows (cardinality) that is to be returned by the query: ")
                .append(explainResultQuery.rows()
                )
                .append("\nThe actual number of rows (cardinality) returned by the query:  " )
                .append(result.size()
                )
                .append("\nResult Records:\n  " )
                .append(result.format());


        return sb.toString();
    }

    void writeResultsToTxt(Query query, String problem, String fileName, String table1Name, String table2Name, String schemaName)
            throws FileNotFoundException, SQLException {

        StringBuilder m5QueryBuilder = new StringBuilder(
                fileName)
                .append("\nProblem:\n")
                .append(problem)
                .append("\n");

        File file = null;

        if(table1Name != null && !table1Name.isBlank()) {
            Table<?> table1 = getTable(schemaName, table1Name);

            Result<Record> table1Records  =
                    ctx
                            .select()//does not use select *
                            .from(table1)
                            .limit(25)
                            .fetch();

             m5QueryBuilder
                     .append("Table1: ")
                    .append(table1.getName()
                    )
                    .append("\nRecords: ")
                    .append(ctx.fetchCount(table1)
                    )
                    .append("\nPrimary Key: ")
                    .append(table1.getPrimaryKey()
                    )
                    .append("\nReferences: ")
                    .append(table1.getReferences()
                    )
                     .append("\nIndexes: ")
                     .append(table1.getIndexes()
                     )
                     .append("\nIdentity: ")
                     .append(table1.getIdentity()
                     )
                    .append("\n Records in ")
                    .append(table1.getName()
                    )
                    .append("\n")
                    .append(table1Records);
        }

        if(table2Name != null && !table2Name.isBlank()) {
            Table<?> table2 = getTable(schemaName, table2Name);

            Result<Record> table2Records =
                    ctx
                            .select()//does not use select *
                            .from(table2)
                            .limit(25)
                            .fetch();

            m5QueryBuilder
                    .append("\n\nTable2: ")
                    .append(table2.getName())
                    .append("\nRecords: ")
                    .append(ctx.fetchCount(table2)
                    )
                    .append("\nPrimary Key: ")
                    .append(table2.getPrimaryKey())
                    .append("\nReferences: ")
                    .append(table2.getReferences()
                    )
                    .append("\nIndexes: ")
                    .append(table2.getIndexes()
                    )
                    .append("\nIdentity: ")
                    .append(table2.getIdentity()
                    )
                    .append("\n Records in ")
                    .append(table2.getName()
                    )
                    .append("\n")
                    .append(table2Records);
        }

        m5QueryBuilder.append(build(query));

        file = createTxtFile(fileName.concat(".txt"));

        Result<Record> result = ctx.fetch(query.getSQL());

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            writer.write(m5QueryBuilder.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeContraintsToText(List<Table<?>> table) throws Exception {
        if (table == null || table.isEmpty()) {
            throw new Exception("Empty Table in writeContraintsToText line 940");
        }

        File file = null;

        try {

            file = createTxtFile("sakila_table_constraints.txt");

            Files.deleteIfExists(file.toPath());

        } catch (DataAccessException | IOException e) {
            e.printStackTrace();
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {

            table.forEach(t -> {
                try {
                    writer.write("" +
                            "\n\nTable: " + t.getName()+
                            "\nPrimary Key: " + t.getPrimaryKey() +
                            "\nReferences: " + t.getReferences()+
                            "\nIndexes: "+ t.getIndexes() +
                            "\nIdentity: "+ t.getIdentity() +
                            "\nChecks: "+ t.getChecks()
                    );
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
        log.info("Logged all table constraints, indentities to sakila_table_constraints.txt ");
    }

    private File createTxtFile(String fileName) {

            StringBuilder sb = new StringBuilder("Database/output_txt/").append(fileName);

            File file = new File(sb.toString());

            return file;
    }

}





