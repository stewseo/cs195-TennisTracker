package com.example.database.sakila_database.verifyData;

import com.example.database.sakila_database.connection.Database;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.NoDataFoundException;
import org.jooq.impl.DSL;
import org.jooq.tools.JooqLogger;

import java.io.File;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.System.out;
import static org.jooq.impl.DSL.*;

public abstract class VerifyDatabase {

    protected static DSLContext ctx;
    protected static final JooqLogger log = JooqLogger.getLogger(VerifyDatabase.class);

    static Predicate<Schema> schemasInCatalog = schema ->
            schema.getName().equals("sakila") ||
                    schema.getName().equals("atpwtagrandslams") ||
                    schema.getName().equals("speed_dating") ||
                    schema.getName().equals("my_guitar_shop") ||
                    schema.getName().equals("mysql") ||
                    schema.getName().equals("sys") ||
                    schema.getName().equals("information_schema");


    //============================================================================
    //              information schema filter
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
    //                          get list of schemas in catalog
    //                          get schema by name
    //============================================================================
    public List<Schema> getSchemaList() {
        List<Schema> schemaList = ctx
                .meta()
                .getSchemas()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList()
                );
    println(schemaList);
    return schemaList;
    }

    public Schema getSchema(String schemaName) {

        try {
            List<Schema> schemaList = ctx.meta().getSchemas(schemaName);
            if (schemaList.size() > 0) {
                return schemaList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //============================================================================
    //                          get list of tables in schema
    //                          get table by name
    //============================================================================
    private Table<?> getTable(String schemaName, String tableName)
            throws SQLException {

        return ctx
                .meta()
                .getSchemas(schemaName).get(0)
                .getTable(tableName);

    }

    public List<Table<?>> getTableList(String schemaName) {

        Schema schema = getSchema(schemaName);

        return schema.getTables();
    }

    //===================================================================================
    //                          Fields
    //===================================================================================
    private Field<?> getField(String tableName, String fieldName) throws Exception {
        String currentSchema = currentSchema().getName();

        Field<?> field = getTable(
                currentSchema, tableName)
                .field(fieldName);

        return field;
    }

    private Field<?>[] getAllFields(String tableName, Schema schema) throws Exception {
        Field<?>[] fields = getTable(schema.getName(), tableName)
                .fields();
        return fields;
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

    //======================================================================================
    //                          Example Query Strings
    //=======================================================================================


    //======================================================================================
    //
    //=======================================================================================

    List<Table<?>> listOfTablesInSchema(String schemaName) {

        List<Table<?>> listOfTables = new ArrayList<>(ctx
                .meta()
                .getSchemas(schemaName)
                .get(0)
                .getTables());
        if (listOfTables.isEmpty()) {
            throw new DataAccessException("no tables in schema");
        }
        return listOfTables;
    }

    //======================================================================================
    //                Check syntax of valid mySql String against Query Sql String
    //=======================================================================================

    //======================================================================================
    //                Check syntax of valid mySql String against Query Sql String
    //=======================================================================================
    void verifyQuerySyntax(Query query, String correctSqlSyntax) throws IOException {
        ResultQuery resultQuery = ctx.resultQuery(query.getSQL());

        resultQuery = ctx.resultQuery(correctSqlSyntax);

        Explain explain = ctx.explain(resultQuery);

        QueryPart queryPart = list(query);

        queryPartsSqlStringToTxt(query, correctSqlSyntax);

    }

    void verifyQueryResults(ResultQuery<Record> resultQuery) throws FileNotFoundException {
        Explain explain = ctx.explain(resultQuery);
    }

    int getSteps(Cursor<Record> cursor) {
        int count = 0;
        while(cursor.hasNext()){
            cursor.fetchNext();
            count++;
        }
        return count;
    }

    //======================================================================================
    //                           Write output to txt file
    //=======================================================================================

    void schemaToTxt(Schema schema) throws Exception {
        List<Table<?>> listOfTables = getTableList(schema.getName());
        log.info("list of tables size: " + listOfTables.size());
        tablesToTxt(listOfTables, schema.getName());
        log.info("schema constraints, field data types, table info in output_txt");
    }
    void tablesToTxt(List<Table<?>> tablesInSchema, String schemaName)
            throws IOException {
        File file = null;

        try {
            String schema_file_name = "schema_info/schema_" + schemaName;
            file = createTxtFile(schema_file_name);

            Files.deleteIfExists(file.toPath());

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        StringBuilder tableInfo = new StringBuilder("Schema: " + schemaName);
        StringBuilder fieldInfo = new StringBuilder("Schema: " + schemaName);

        tablesInSchema.forEach(table -> {
                    tableInfo
                            .append(getTableInfo(table))
                            .append("\nFields:\n")
                            .append(table.fieldsRow());

                    fieldInfo
                            .append("Table: ")
                            .append(table.getName())
                            .append(getAllFieldInfo(table));
                }
        );

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writer.write(tableInfo.toString());

        }
        String fields_file_name =  "schema_info/field_types_data_types";
        file = createTxtFile(fields_file_name);

        try (Writer writeFields = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writeFields.write(fieldInfo.toString());

        }
        log.info("Table name, number of fields in each table, number of records in each table logged to sakila_table_info.txt");
    }

    private void queryPartsSqlStringToTxt(QueryPart queryPart, String sqlString) throws IOException {
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

    void querySyntaxToTxt(String fileName, Query query){
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
                new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writer.write(m5QueryBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String multiSetQueryInfo(ResultQuery<?> query, Result<?> result){
        QueryPart queryPart = list(query);
        Explain explainResultQuery = ctx.explain(query);
        StringBuilder sb = new StringBuilder();
        sb
                .append("\n\nQuery Parts:\n")
                .append(queryPart
                )
                .append("\nSql String")
                .append(sql(query.getSQL())
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
                .append("\n\nResult Records:\n  " )
                .append(result);

        return sb.toString();
    }
    protected <T> String queryInfo(T t) {
        StringBuilder sb = new StringBuilder();


        if(t instanceof Select<?> select) {
            Query query = query(select.getSQL());
            Explain explainSelect = ctx.explain(select);

            var result  = ctx.fetch(select);

            QueryPart queryPart = list(select);
            sb
                    .append("\n\nQuery Parts:\n")
                    .append(queryPart)
                    .append("\nSql String")
                    .append(query.getSQL())
                    .append("\nThe formatted plan as returned by the database:\n " )
                    .append(explainSelect.plan()
                    )
                    .append("\nThe cost the database associated with the execution of the query:  " )
                    .append(explainSelect.cost()
                    )
                    .append("\nThe estimated number of rows (cardinality) that is to be returned by the query: ")
                    .append(explainSelect.rows()
                    )
                    .append("\nThe actual number of rows (cardinality) returned by the query:  ")
                    .append(result.size()
                    )
                    .append("\n\nResult Records:\n  ")
                    .append(result.format());
        }

        else if (t instanceof Query query) {
            log.info("checking sql string from query");
            String sql = query.getSQL();
            
            QueryPart queryPart = list(query);

            var result  = ctx.fetch(query.getSQL());

            log.info("appending explain info");
            Explain explainResultQuery = ctx.explain(query);
            sb
                    .append("\n\nQuery Parts:\n")
                    .append(queryPart)
                    .append("\nSql String")
                    .append(sql)
                    .append("\nThe formatted plan as returned by the database:\n ")
                    .append(explainResultQuery.plan()
                    )
                    .append("\nThe cost the database associated with the execution of the query:  ")
                    .append(explainResultQuery.cost()
                    )
                    .append("\nThe estimated number of rows (cardinality) that is to be returned by the query: ")
                    .append(explainResultQuery.rows()
                    )
                    .append("\nThe actual number of rows (cardinality) returned by the query:  ")
                    .append(result.size()
                    )
                    .append("\n\nResult Records:\n  ")
                    .append(result.format());
        }

        return sb.toString();
    }

    public<T> T resultQueryToTxt(T resultQuery,Result<?> result,String description, String fileName, List<String> listOfTableNames, String schemaName){
        StringBuilder outputBuilder = new StringBuilder(
                fileName)
                .append("\n")
                .append(description)
                .append("\n");

        File file = null;
        outputBuilder.append(multiSetQueryInfo((ResultQuery<?>) resultQuery, result));

        file = createTxtFile(fileName.concat(".txt"));

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writer.write(outputBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultQuery;
    }

    public <T>T queryInfoToTxt(T query, String problem, String fileName, List<String> listOfTableNames, String schemaName)
            throws FileNotFoundException, SQLException {

        StringBuilder outputBuilder = new StringBuilder(
                fileName)
                .append("\nProblem:\n")
                .append(problem)
                .append("\n");

        File file = null;

        outputBuilder.append(queryInfo((Query) query));

        if(!listOfTableNames.isEmpty()) {
            outputBuilder.append("\ndata source(s):\n");
            listOfTableNames.forEach(tableName -> {
                Table<?> table = null;

                try {
                    table = getTable(schemaName, tableName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                outputBuilder
                        .append(getTableInfo(table))
                        .append("\n Records in ")
                        .append(table.getName())
                        .append("\n")
                        .append(getRecords(table, 5));
            });
        }


        file = createTxtFile(fileName.concat(".txt"));

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writer.write(outputBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    //===============================================================================================================
    //                    @return:
    //                      table String name, unqualified name, qualified name
    //                      table identity,
    //                      number of records,
    //                      number of columns
    //==============================================================================================================
    String getTableInfo(Table<?> table){
        if(tableIsEmpty(table)){
            throw new NoDataFoundException("table has no fields");
        }

        StringBuilder tableInfo = new StringBuilder(
                "\n\nTable Name: ")
                .append(table.getName());

        Name unqualifiedName = table.getUnqualifiedName();

        if(unqualifiedName != null) {
            tableInfo
                    .append("\nunqualified name: ")
                    .append(unqualifiedName);
        }

        Name qualifiedName = table.getQualifiedName();

        if(qualifiedName != null) {
            tableInfo
                    .append("\nqualified name: ")
                    .append(qualifiedName);
        }

        Identity identity = table.getIdentity();

        if(identity != null) {
            tableInfo
                    .append("\nIdentity: ")
                    .append(table.getIdentity());
        }

        tableInfo
                .append(getConstraints(table))
                .append("\nNumber of records: ")
                .append(ctx.fetchCount(table)
                )
                .append("\nNumber of fields: ")
                .append(table.fields().length)
                ;

        return tableInfo.toString();
    }

    //===============================================================================================================
    //                    @return Field:
    //                      name,
    //                      data type,
    //                      java type
    //==============================================================================================================
    String getAllFieldInfo(Table<?> table){
        StringBuilder fields = new StringBuilder();
        Arrays.stream(table.fields()).forEach(field->
                fields
                        .append("\nField: ")
                        .append(field.getName())
                        .append("\n\tDataType: ")
                        .append(field.getDataType())
                        .append("\n\tType: ")
                        .append(field.getType())
                );
        return fields.toString();
    }
    Result<Record> getRecords(Table<?> table, int numberOfRecords) {
        if(tableIsEmpty(table)){
            throw new NoDataFoundException("table has no fields");
        }

        return
                ctx
                        .select()//does not use select *
                        .from(table)
                        .limit(numberOfRecords)
                        .fetch();
    }
    //===============================================================================================================
    //                    @return Table:
    //                      primary key,
    //                      foreign keys,
    //                      unique keys,
    //                      indexes,
    //                      checks
    //==============================================================================================================
    String getConstraints(Table<?> table){
        StringBuilder constraints = new StringBuilder("\nPrimary Key: ")
                .append(table.getPrimaryKey());

        List<? extends Check<?>> checks = table.getChecks();
        List<? extends UniqueKey<?>> uniqueKeys = table.getUniqueKeys();
        List<? extends ForeignKey<?, ?>> foreignKeys = table.getReferences();
        if(!foreignKeys.isEmpty()){
            constraints.append("\nForeign Keys: ").append(foreignKeys);
        }
        if(!table.getIndexes().isEmpty()){
            constraints.append("\nIndexes: ").append(table.getIndexes());
        }
        if(!uniqueKeys.isEmpty()) {
            constraints.append("\nUnique Keys: ")
                    .append(table.getUniqueKeys());
        }
        if(!checks.isEmpty()) {
            constraints.append("\nChecks: ")
                    .append(table.getChecks());
        }
        return constraints.toString();
    }

    //======================================================================================
    //                       Check if Database has Data
    //=======================================================================================
    private static boolean tableIsEmpty(Table<?> table) {
        return table.fields().length == 0;
    }

    private static boolean schemaIsEmpty(Schema schema) {
        if (schema == null || schema.getName().isBlank()) {
            return true;
        }
        return false;
    }

    protected File createTxtFile(String fileName) {
        log.info("Creating file" + fileName);
        return new File("output_txt/".concat(fileName));

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

    void create(String schemaName){
        log.info("Connecting with: " + schemaName);
        ctx = DSL.using(Database.getConfigurationWithVisitListener(schemaName));
        log.info("Connected");
    }

    static {
        log.info("Connecting");
        ctx = DSL.using(Database.getConfigurationWithVisitListener("sakila"));
        log.info("Connected");
    }

}





