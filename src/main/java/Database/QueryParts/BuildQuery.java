package Database.QueryParts;

import Database.Database;
import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;



import java.util.List;

import static com.example.cs195tennis.VerifyDataIntegrity.VerifyDatabase.getColumnValue;
import static com.example.cs195tennis.Util.Tools.print;
import static java.lang.System.out;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DefaultBinding.binding;

public class BuildQuery {
    private Query query;
    private String sql;
    private QueryPart queryPart;
    private Condition condition;
    private Field<?> field;
    public BuildQuery() {
    }
    public BuildQuery(String sql) {
        query = DSL.query(sql);
    }
    public BuildQuery(String sql, Field<?>[] fields) {

    }

    private static Parser parser;

    //==================================================================================
    //                  Build Query:
    //                      Clauses SELECT, FROM, WHERE, GROUP BY
    //                      Condition condition, Operator operator
    //==================================================================================
    public static void build(Query query) {

        Settings settings = Database.getParseSettings();

        parser = Database
                .getParserWithIgnoreComments(
                )
        ;

        print("Parser: " + parser);

        Query parsedQuery =
                parser.parseQuery(
                        query.getSQL()
                );
        print("Query: " + query);
    }

    public static void build(List<String> sqlStringList) {

        Settings settings =
                Database.getParseSettings();

        parser = Database
                .getParserWithIgnoreComments(
                )
        ;
        print("Parser: " + parser);

        for (String s : sqlStringList) {
            Query parsedQuery =
                    parser
                            .parseQuery(
                                    s
                            );
            out.println("Sql String: " + s);
        }
    }

    //==================================================================================
    //             Constructors
    //==================================================================================
    public BuildQuery(String selectString, String fromString, String where, String condition, String having, String grouping) {
        SelectField<?>[] select = {
                DSL.field(selectString),
                count()
        };

        Table<?>[] from = {
                DSL.table(fromString)
        };

        GroupField[] groupBy = {
                DSL.field(grouping)
        };

        SortField<?>[] orderBy = {
                count().desc()
        };

    }

    public void query(String sql){
    }
    public void condition(String con) {
        Condition condition = DSL.condition(con);
    }

    //==================================================================================
    //             Dynamic Sql SelectSelectStep, SelectFromStep, SelectJoinStep
    //==================================================================================
    private Select<?> select;
    private Binding binding;
    private Param<?> param;

    //-- Correct
    //SELECT first_name, count(*)
    //FROM customer
    //GROUP BY first_name
    //HAVING count(*) > 1
    public static Query queryPartsHaving(String select, String from, String groupBy, String having) {
        return
                DSL
                        .query(
                                select +
                                        from +
                                        groupBy +
                                        having
                        );


    }

    //-- Wrong
    //SELECT first_name, count(*)
    //FROM customer
    //WHERE count(*) > 1
    //GROUP BY first_name

    public static Query queryPartsWhere(String select, String from, String where, String groupBy) {
        return
                DSL
                        .query(
                                select +
                                        from +
                                        where +
                                        groupBy
                        );

    }


    public static Query sqlStringToQueryParts(String sql, int fromStart, int fromEnd) {
        if(sql.substring(0, 6).equals("SELECT")){
            print("SELECT " + sql.substring(0, 6));
        }
        if(sql.substring(fromStart, fromEnd).contains("FROM")){
            print("FROM " + sql.substring(fromStart, fromEnd));
        }
        if(sql.substring(fromEnd+1).contains("GROUP")){
            print("GROUP BY" + sql.substring(fromEnd+1, sql.length()));
        }
      return null;
    }
    public void addSelectField(SelectField<?> select) {
    }
    public void addSelectStepWhere(SelectJoinStep<?> queryPart) {
    }
    public void addSelectWhereStep(SelectWhereStep<?> queryPart) {
    }

    public static enum QueryPart{
        SELECT,
        FROM,
        WHERE,
        JOIN
    }
}
