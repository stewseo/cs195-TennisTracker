package com.example.database.sakila_database.verifyData;

import com.example.database.sakila_database.SakilaModel.Record.ActorRecord;
import com.example.database.sakila_database.SakilaModel.Table.Actor;
import com.example.database.sakila_database.SakilaModel.Table.City;
import com.example.database.sakila_database.SakilaModel.Table.FilmActor;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;


import static com.example.database.sakila_database.SakilaModel.Table.Actor.ACTOR;
import static com.example.database.sakila_database.SakilaModel.Table.Address.ADDRESS;
import static com.example.database.sakila_database.SakilaModel.Table.City.CITY;
import static com.example.database.sakila_database.SakilaModel.Table.Country.COUNTRY;
import static com.example.database.sakila_database.SakilaModel.Table.Customer.CUSTOMER;
import static com.example.database.sakila_database.SakilaModel.Table.Film.FILM;
import static com.example.database.sakila_database.SakilaModel.Table.FilmActor.FILM_ACTOR;
import static java.lang.System.out;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.*;

public class VerifySakilaDB extends VerifyDatabase {
    static Query query;
    static ResultQuery<Record> resultQuery;
    static String sql;
    static String description;

    //=====================================================================================
    //                 Constructors to create instance of DSLContext ctx
    //=====================================================================================
    public VerifySakilaDB() {
        this("sakila");
    }
    public VerifySakilaDB(String schemaName) {
        if(ctx == null) {
            create(schemaName);
        }
    }

    //============================================================================================================
    //         Verify current schema in use is 'Sakila', number of tables in sakila, name of each table in sakila
    //         Verify Number of Fields in each table, Name of each field, Data type of each field.
    //=============================================================================================================
    public void verifySchema(String schemaName) throws Exception {
        title(schemaName + " information being logged to sakila_table_constraints.txt and sakila_general_table_info.txt ");

        Schema schema = getSchema(schemaName);
        schemaToTxt(schema);
        log.info("Logged to output_txt/schema_info/"+ schemaName);

    }

    //=================================================================================
    //    Verify my sql string and jooq methods return same result
    //    write to output_txt/verifyMySqlSyntax
    //==================================================================================
    public void verifyMySqlSyntax(String schemaName) throws IOException, SQLException {
        log.info(schemaName);
        Field<?> COUNT1 = field("count(*) x");

        Query query = ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME, COUNT1)
                .from(ACTOR)
                .where("Actor.LAST_NAME like 'A%' ")
                .groupBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .orderBy(ACTOR.FIRST_NAME.asc());

        String sqlString = """
                SELECT Actor.first_name, Actor.last_name, count(*)
                FROM Sakila.Actor
                WHERE Actor.last_name LIKE 'a%'
                GROUP BY Actor.first_name, Actor.last_name
                ORDER BY Actor.first_name asc
                """;

        description = "Consume records for each";
        queryInfoToTxt(query, //Query
                description,
                "consume_records_for_each/consume_records_for_ea",  //file_name
                List.of("actor"),
                "sakila" //schema in use
        );


        for (var r : ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.lessThan(5L))
        ) {
            println("Actor: %s %s".formatted(r.value1(), r.value2()));
        }

        ctx.select(FILM.FILM_ID, FILM.TITLE)
                .from(FILM)
                .limit(5)
                .forEach(r -> println("Film %s: %s".formatted(r.value1(), r.value2()))
                );

        description = "Result<Record> extends Iterable";

        query = ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where("ACTOR.ACTOR_ID < '5L'");

        queryInfoToTxt(query, //Query
                description,
                "consume_records_for_each/result_extends_iterable_example",  //file_name
                List.of("actor"),
                "sakila" //schema in use
        );
    }


    protected void verifyConsumingLargeRecordsWithCursor(String schemaName) throws IOException, SQLException {

        try (Cursor<Record2<String, String>> c = ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.lt(5L))
                .fetchLazy()
        ) {
            for (Record2<String, String> r : c)
                println("Actor: %s %s".formatted(r.value1(), r.value2()));
        }
        Query query =
                ctx
                        .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.ACTOR_ID.lt(5L));

        Cursor<Record> cursor = ctx.fetchLazy(query.getSQL());

        String sqlString = """
                SELECT Actor.first_name, Actor.last_name
                FROM Sakila.Actor
                WHERE Actor.actor_id < '5L'
                """;

        description = "imperative consumption of large results using Cursor, keeping an open ResultSet behind the scenes";

        queryInfoToTxt(query, //Query
                description,
                "consume_large_records/imperative_consumption_using_cursor",  //file_name
                List.of("actor"),
                "sakila" //schema in use
        );

        try (Stream<Record2<String, String>> s = ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.lt(5L))
                .fetchStream()
        ) {
            s.forEach(r -> println("Actor: %s %s".formatted(r.value1(), r.value2())));
        }

        query =
                ctx
                        .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.ACTOR_ID.lt(5L));

        description = "Functional consumption of large results using Stream, keeping an open ResultSet behind the scenes";

        queryInfoToTxt(query, //Query
                description,
                "consume_large_records/functional_consumption_using_stream",  //file_name
                List.of("actor"),
                "sakila" //schema in use
        );

    }

    public void verifyActiveRecords(String schemaName) throws IOException {
        title("Verify selecting each field from table actor");

        Query query = ctx
                .selectFrom(ACTOR)
                .where(ACTOR.ACTOR_ID.eq(1L));

        ActorRecord actor = ctx
                .selectFrom(ACTOR)
                .where(ACTOR.ACTOR_ID.eq(1L))
                .fetchSingle();


        String sqlString = """
                SELECT  Actor.actor_id, Actor.first_name, Actor.last_name
                FROM Sakila.Actor
                WHERE Actor.actor_id = '1L'
                """;

        verifyQuerySyntax(query, sqlString);

        println("Resulting actor: %s %s".formatted(actor.get(ACTOR.FIRST_NAME), actor.get(ACTOR.LAST_NAME)));

    }

    public void verifyUnions(String schemaName) throws IOException {
        title("Verify UNION / INTERSECT / EXCEPT ");

        Query query =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where("Actor.first_name like 'A%' ")
                        .union(select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                                .from(CUSTOMER)
                                .where("Customer.first_name LIKE 'A%'"));

        String sqlString = """
                SELECT  Actor.first_name, Actor.last_name
                FROM Actor
                WHERE Actor.first_name LIKE 'a%'
                UNION
                SELECT Customer.first_name, Customer.last_name
                FROM Customer
                WHERE Customer.first_name LIKE 'a%'
                """;

        verifyQuerySyntax(query, sqlString);

        var result = ctx.resultQuery(query.getSQL());

        verifyQueryResults(result);

    }

    public void verifyWithInPredicates(String schemaName) throws IOException {

        title("In Predicates\n" +
                "Verification for Models: ActorTable, FilmActorTable. Verify Record Data Type safety: ActorRecord, FilmActorRecord");
        Query query1 =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.FIRST_NAME.like("A%"))
                        .and(ACTOR.ACTOR_ID.in(select(FILM_ACTOR.ACTOR_ID).from(FILM_ACTOR)));
        var v1 = query1;
        String sqlString = query1.getSQL();
        verifyQuerySyntax(query1, sqlString);

        title("Row Value expressions\n" +
                "Verify Models: ActorTable, FilmActorTable. Verify Record Data Type safety: ActorRecord, FilmActorRecord");
        Query query2 =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.FIRST_NAME.like("A%"))
                        .and(row(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                                .in(select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                                        .from(CUSTOMER)));
        var v2 = query2;

        sqlString = query1.getSQL();
        verifyQuerySyntax(query2, sqlString);
    }

    public void verifyStandardisationLimit(String schemaName) {
        title("Verify LIMIT, OFFSET select Fields Actor.first_name and Actor.last_name");
        Query query =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.FIRST_NAME.like("A%"))
                        .orderBy(ACTOR.ACTOR_ID)
                        .limit(10)
                        .offset(10);


        var r1 = ctx.fetch(query.getSQL());

    }

    public void verifyJoin(String schemaName) throws IOException {
        title("Verify MySQL JOIN syntax using JOOQ methods and actual sql String");

        Query query =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .join(FILM_ACTOR)
                        .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                        .where("Actor.first_name LIKE 'a%'");

        String sql = """
                SELECT Actor.first_name, Actor.last_name
                FROM Actor
                    INNER JOIN Film_Actor
                        ON Actor.actor_id = Film_Actor.actor_id
                WHERE Actor.first_name LIKE 'a%'
                """;

        verifyQuerySyntax(query, sql);

        var result = ctx.resultQuery(query.getSQL());

        verifyQueryResults(result);
    }

    public void verifyAliasing(String schemaName) throws IOException {
        title("Verify MySql Table alias syntax using Jooq Methods and actual sql String");

        Actor a = (Actor) ACTOR.as("a");
        FilmActor fa = (FilmActor) FILM_ACTOR.as("fa");

        Query query =
                ctx.select(a.FIRST_NAME, a.LAST_NAME)
                        .from(a)
                        .join(fa)
                        .on(a.ACTOR_ID.eq(fa.ACTOR_ID))
                        .where(a.FIRST_NAME.like("A%"));

        String sql = """
                SELECT a.first_name, a.last_name
                FROM Actor a
                INNER JOIN Film_Actor fa
                ON a.actor_id = fa.actor_id
                WHERE a,first_name LIKE 'a%'
                """;

        verifyQuerySyntax(query, sql);

        var result = ctx.resultQuery(query.getSQL());

        verifyQueryResults(result);
    }

    public void verifyImplicitJoins(String schema) throws IOException {
        title("Customer -> address -> city -> country -> COUNTRY_");


        List<? extends ForeignKey<?, ?>> customerReferences = CUSTOMER.getCustomerReferences();

        List<? extends ForeignKey<?, ?>> addressReferences = customerReferences
                .stream()
                .filter(fk ->
                        fk.getName()
                                .equals("fk_customer_address"))
                .toList();

        println("references " + CUSTOMER.getCustomerReferences());
        println("identity " + CUSTOMER.getIdentity());
        println("index " + CUSTOMER.getCustomerIndex());
        println("pk " + CUSTOMER.getCustomerPrimaryKey());

        String sql = """
                SELECT cu.first_name, cu.last_name, 
                a.address, 
                c.city AS city
                 FROM customer cu
                 JOIN address a ON cu.address_id = a.address_id
                 JOIN city c ON a.city_id = c.city_id;
                """;


        String filmTitlesAndActors = """
                SELECT f.title, cu.last_name, 
                a.address, 
                c.city AS city
                 FROM customer cu
                 JOIN address a ON cu.address_id = a.address_id
                 JOIN city c ON a.city_id = c.city_id;
                """;

        Query query = query("""
                SELECT
                                c.first_name, c.last_name, a.address, ci.city As City
                                FROM customer c, address a, city ci
                                WHERE c.address_id = a.address_id
                                """);
        String sql2 = """
                SELECT
                c.first_name, c.last_name, a.address, ci.city As City
                FROM customer c, address a, city ci
                WHERE c.address_id = a.address_id
                """;


        verifyQuerySyntax(query, sql);

        var result = ctx.resultQuery(query.getSQL());

        verifyQueryResults(result);


    }

    public void verifyNestedRecords(String schema) throws IOException, SQLException {
        title("Produce all films and their actors");

        Table<?> custTable = ctx.meta().getTables("customer").get(0);

        List<? extends ForeignKey<?, ?>> customerFks = custTable.getReferences();

        resultQuery = resultQuery("""
                SELECT cu.first_name, a.*, c.*, co.*
                FROM customer cu
                    JOIN address a
                        ON cu.address_id = a.address_id
                    JOIN city c
                        ON a.city_id = c.city_id
                    JOIN country co 
                        ON c.country_id = co.country_id
                WHERE cu.first_name = 'Mario'
                """
        );
        description = "return all columns:customer, address, city, country\n" +
                "customer_first_name = 'Mario' ";

        queryInfoToTxt(resultQuery, //Query
                description,
                "nested_records/customer_address_city_country",  //file_name
                List.of("customer", "address", "city","country"),
                "sakila" //schema in use
        );


        resultQuery = resultQuery("""   
                SELECT 
                       cu.first_name, cu.last_name, 
                       a.address, a.postal_code, 
                       c.city,
                       co.country
                FROM customer cu
                    JOIN address a
                        ON cu.address_id = a.address_id
                    JOIN city c
                        ON a.city_id = c.city_id
                    JOIN country co 
                        ON c.country_id = co.country_id
                WHERE co.country = 'Algeria'
                """
        );
        description = "return customer first name, last name, address, postalcode, city, country\n" +
                "country = 'Algeria' ";
        queryInfoToTxt(resultQuery, //Query
                description,
                "nested_records/customer_country_algeria",  //file_name
                List.of("customer", "address", "city","country"),
                "sakila" //schema in use
        );
        resultQuery = resultQuery("""
                     SELECT
                       cu.first_name,
                       cu.last_name,
                       (
                         SELECT count(*)
                         FROM customer as cu2
                           JOIN address
                             USING (address_id)
                           JOIN city as ci2
                             USING (city_id)
                         WHERE ci2.country_id = ci.country_id
                       ) as customers_from_same_country
                     FROM customer as cu
                       JOIN address
                         USING (address_id)
                       JOIN city as ci
                         USING (city_id)
                """
        );
        description = "return customers in same country";
        queryInfoToTxt(resultQuery,
                description,
                "nested_records/find_number_customers_same_country",
                List.of("customer", "address", "city","country"),
                "sakila"
        );

        resultQuery = resultQuery("""
                SELECT
                  co.country,
                  COUNT(*),
                  COUNT(DISTINCT city.city)
                FROM customer AS cu
                JOIN address USING (address_id)
                JOIN city USING (city_id)
                JOIN country AS co USING (country_id)
                GROUP BY co.country
                ORDER BY co.country
                """
        );
        description = "return all columns from tables customer, address, city, country\n" +
                "group by country, order by country";
        queryInfoToTxt(resultQuery, //Query
                description,
                "nested_records/group_by_country_order_by_country",  //file_name
                List.of("customer", "address", "city","country"),
                "sakila"
        );
        Query query = query("""
                        SELECT f.title, array(
                            SELECT json_array(a.actor_id, a.first_name, a.last_name) AS `nested` 
                             FROM actor AS a
                              JOIN film_actor AS fa
                                using (actor_id)
                            WHERE fa.film_id = f.film_id
                            ORDER BY a.actor_id   
                         )         
                        FROM film AS f
                        ORDER BY f.title        
                """
        );

        resultQuery = resultQuery("""
                            SELECT json_array(
                                a.actor_id, 
                                a.first_name, 
                                a.last_name) 
                                AS `nested`
                            FROM actor AS a
                              JOIN film_actor AS fa
                                using (actor_id)
                            ORDER BY a.actor_id  
                """
        );
        Query jsonArrayAsNested =
                select(
                        jsonArray(
                                field("a.actor_id"),
                                field("a.first_name"),
                                field("a.last_name")).as("nested"))
                        .from(table("actor a")
                                .join(table("film_actor as fa"))
                                .using(field(unquotedName("actor_id")))
                        )
                        .orderBy(field("a.actor_id").asc()
                        );


    }

    public void testDynamicSql(String schema) throws IOException, SQLException {

        ResultQuery<Record2<String, String>> res =
                ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .from(ACTOR)
                        .where(ACTOR.ACTOR_ID.in(1L, 2L, 3L))
                        .orderBy(ACTOR.FIRST_NAME)
                        .limit(5);

        sql = """
                SELECT 
                    a.first_name, 
                    a.last_name
                FROM actor a
                WHERE a.actor_id 
                    IN ('1L', '2L', '3L')
                ORDER BY first_name
                LIMIT 5
                """;

        query = query(sql);

        queryInfoToTxt(query, //Query object
                sql, //title
                "dynamic_sql/dynamic_sql1", //file_name
                List.of("actor"),
                schema); //schema

        List<SelectField<?>> select = List.of(ACTOR.FIRST_NAME, ACTOR.LAST_NAME);
        Table<?> from = ACTOR;
        Condition where = ACTOR.ACTOR_ID.in(1L, 2L, 3L);
        List<OrderField<?>> orderBy = List.of(ACTOR.FIRST_NAME);
        Field<Integer> limit = val(5);

        query =
                ctx.select(select)
                        .from(from)
                        .where(where)
                        .orderBy(orderBy)
                        .limit((Param<? extends Number>) limit)
        ;

        sql = """
            SELECT a.first_name, a.last_name
            FROM actor a
            WHERE a.actor_id IN (
            '1L', '2L', '3L'
            )
            ORDER BY a.first_name
            LIMIT 5
                """;

        querySyntaxToTxt("dynamic_sql/dynamic_sql_to_sql_string", query);

        List<Integer> ids = List.of(1, 2, 3);

        query =
        ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ids.isEmpty()
                        ? noCondition()
                        : ACTOR.ACTOR_ID.in(ids.stream().map(Long::valueOf).map(DSL::val).toList()))
                .orderBy(ACTOR.FIRST_NAME)
                .limit(5)
        ;
        querySyntaxToTxt("dynamic_sql/dynamic_sql_to_sql_string3", query);
    }

    public void writeNestedRowsWithAdHocConverters(String schemaName) throws SQLException, FileNotFoundException {
        record Country(String name) {
        }
        record Customer(String firstName, String lastName, Country country) {
        }

        title("Convert to Result<Record3<String, String, Country>> or List<Customer>");


        List<Customer> r =
                ctx.select(
                                CUSTOMER.FIRST_NAME,
                                CUSTOMER.LAST_NAME,
                                COUNTRY.COUNTRY_.convertFrom(Country::new))
                        .from(CUSTOMER)
                        .join(ADDRESS).on(CUSTOMER.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID))
                        .join(CITY).on(ADDRESS.CITY_ID.eq(CITY.CITY_ID))
                        .join(COUNTRY).on(CITY.COUNTRY_ID.eq(COUNTRY.COUNTRY_ID))
                        .orderBy(1, 2)
                        .limit(5)
                        .fetch(mapping(Customer::new));

        String sql = """
                SELECT cu.first_name, cu.last_name, co.country
                FROM customer cu
                INNER JOIN address a
                    ON cu.address_id = a.address_id
                INNER JOIN city c
                    ON a.city_id = c.city_id
                INNER JOIN country co
                    ON c.country_id = co.country_id
                ORDER BY 1, 2
                LIMIT 5
                """;


        r.forEach(VerifySakilaDB::println);


        query = query(sql);

        description = "Nested records with ad hoc converter";

        queryInfoToTxt(query, //Query
                description,
                "nested_records_ad_hoc_converter/customer_first_last_country_convertFrom",  //file_name
                List.of("customer", "address", "city","country"),
                "sakila" //schema in use
        );


        r =
                ctx.select(DSL.row(
                                CUSTOMER.FIRST_NAME,
                                CUSTOMER.LAST_NAME,
                                row(COUNTRY.COUNTRY_).mapping(Country::new)
                        ).mapping(Customer::new))
                        .from(CUSTOMER)
                        .join(ADDRESS).on(CUSTOMER.ADDRESS_ID.eq(ADDRESS.ADDRESS_ID))
                        .join(CITY).on(ADDRESS.CITY_ID.eq(CITY.CITY_ID))
                        .join(COUNTRY).on(CITY.COUNTRY_ID.eq(COUNTRY.COUNTRY_ID))
                        .orderBy(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                        .limit(5)
                        .fetch(Record1::value1);

        r.forEach(VerifySakilaDB::println);


        sql = """
                SELECT 
                    cu.first_name as 'row.first_name',
                    cu.last_name as 'row.last_name',
                    co.country as 'row.row.country'
                FROM customer cu
                    INNER JOIN address a
                        ON cu.address_id = a.address_id
                    INNER JOIN city c
                        ON a.city_id = c.city_id
                    INNER JOIN country co
                        ON c.country_id = co.country_id
                ORDER BY cu.first_name, cu.last_name
                """;

        query = query(sql);

        description = "deeply_nested_row_value_ad_hoc_converter";

        queryInfoToTxt(query, //Query
                description,
                "nested_records_ad_hoc_converter/deeply_nested_row_value_ad_hoc_converter",  //file_name
                List.of("customer", "address", "city","country"),
                "sakila" //schema in use
        );

    }
    
    private Condition reduceCondition(List<Integer> ids) {
        title("List: " + ids);
        return ids
                .stream()
                .map(Long::valueOf)
                .map(ACTOR.ACTOR_ID::eq)
                .reduce(noCondition(), Condition::or);
    }

    public void testQueryParts() {
        println(reduceCondition(List.of()));
        println(reduceCondition(List.of(1)));
        println(reduceCondition(List.of(1, 2, 3)));
    }
}


