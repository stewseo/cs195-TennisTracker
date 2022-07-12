package com.example.database.sakila_database.verifyData;

import com.example.database.sakila_database.model.Table.Record.ActorRecord;
import com.example.database.sakila_database.model.Table.*;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;


import static com.example.database.sakila_database.model.Table.Actor.ACTOR;
import static com.example.database.sakila_database.model.Table.Address.ADDRESS;
import static com.example.database.sakila_database.model.Table.Category.CATEGORY;
import static com.example.database.sakila_database.model.Table.City.CITY;
import static com.example.database.sakila_database.model.Table.Country.COUNTRY;
import static com.example.database.sakila_database.model.Table.Customer.CUSTOMER;
import static com.example.database.sakila_database.model.Table.Film.FILM;
import static com.example.database.sakila_database.model.Table.FilmActor.FILM_ACTOR;
import static com.example.database.sakila_database.model.Table.FilmCategory.FILM_CATEGORY;
import static com.example.database.sakila_database.model.Table.Inventory.INVENTORY;
import static com.example.database.sakila_database.model.Table.Payments.Payment.PAYMENT;
import static com.example.database.sakila_database.model.Table.Rental.RENTAL;
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

    void testDynamicSql(String schema) throws IOException, SQLException {

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

    void writeNestedRowsWithAdHocConverters(String schemaName) throws SQLException, FileNotFoundException {
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

    void nestingToManyRelationShips(String currentSchemaName) throws SQLException, FileNotFoundException {

        ResultQuery<Record4<String, String, String, String>> resultQuery = ctx.select(
                        FILM.TITLE,
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME,
                        CATEGORY.NAME
                )
                .from(ACTOR)
                .join(FILM_ACTOR)
                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                .join(FILM)
                .on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                .join(FILM_CATEGORY)
                .on(FILM.FILM_ID.eq(FILM_CATEGORY.FILM_ID))
                .join(CATEGORY)
                .on(FILM_CATEGORY.CATEGORY_ID.eq(CATEGORY.CATEGORY_ID))
                .orderBy(1, 2, 3, 4);

        description = "Actors not grouped, cartesian product as result";

        queryInfoToTxt(resultQuery,
                description,
                "nested_records/nesting_to_many_relationships/film_title_actor_name_category_name",
                List.of("actor", "film_actor", "film","film_category", "category"),
                "sakila"
        );

        ResultQuery<Record3<String, Result<Record2<String, String>>, Result<Record1<String>>>> rq =
                ctx.
                        select(
                                FILM.TITLE,
                                multiset(
                                        select(
                                                ACTOR.FIRST_NAME,
                                                ACTOR.LAST_NAME)
                                                .from(ACTOR)
                                                .join(FILM_ACTOR)
                                                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ).as("actors"),       multiset(
                                        select(CATEGORY.NAME)
                                                .from(CATEGORY)
                                                .innerJoin(FILM_CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                ).as("categories")
                        )
                        .from(FILM);

        description = """
                MultiSet, Actor's grouped as Result
                 film_actor.actor_id = actor.actor_id,
                 film_actor.film_id = film.film_id,
                 film_category.film_id = film.film_id
                """;

        queryInfoToTxt(rq,
                description,
                "nested_records/nesting_to_many_relationships/multiset_actor_name__category_name",
                List.of("actor", "film_actor", "film","film_category", "category"),
                "sakila"
        );


        String q = """
                select(
                      FILM.TITLE,
                      multiset(
                        select(
                         ACTOR.FIRST_NAME,
                          ACTOR.actor().LAST_NAME)
                        .from(FILM_ACTOR)
                        .join actor as a
                          on film_actor.actor_id = a.actor_id
                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                      ).as("actors")
                """;

        Query multi = query("""
                select
                  film.title,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object(
                            'first_name', t.first_name,
                            'last_name', t.last_name
                          ) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select a.first_name, a.last_name
                      from film_actor
                        join actor as a
                          on film_actor.actor_id = a.actor_id
                      where film_actor.film_id = film.film_id
                    ) as t
                  ) as actors,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object('name', t.name) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select c.name
                      from film_category
                        join category as c
                          on film_category.category_id = c.category_id
                      where film_category.film_id = film.film_id
                    ) as t
                  ) as categories
                from film
                order by film.title
                """);

        description = "MySQL syntax for multiset when off";

        queryInfoToTxt(multi, //Query
                description,
                "nested_records/nesting_to_many_relationships/my_sql_syntax",  //file_name
                List.of("film_actor", "actor", "film","film_category", "category"),
                "sakila" //schema in use
        );


        Result<Record3<String, Result<Record2<String, String>>, Result<Record1<String>>>> r =
                ctx.select(
                                FILM.TITLE,
                                multiset(
                                        select(
                                                FILM_ACTOR.actor().FIRST_NAME,
                                                FILM_ACTOR.actor().LAST_NAME)
                                                .from(FILM_ACTOR)
                                                .innerJoin(ACTOR)
                                                .on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ),
                                multiset(
                                        select(CATEGORY.NAME)
                                                .from(FILM_CATEGORY)
                                                .join(CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                )
                        )
                        .from(FILM)
                        .orderBy(FILM.TITLE)
                        .limit(5)
                        .fetch();


        title("Formatted as JSON");
        println(r.formatJSON(JSONFormat.DEFAULT_FOR_RESULTS.format(true).header(false)));

        title("Formatted as XML");
        println(r.formatXML(XMLFormat.DEFAULT_FOR_RESULTS.format(true).header(false)));


    }

    void nestingToManyRelationshipsAdHocConverter(String currentSchemaName) throws SQLException, FileNotFoundException {
        record Name(String firstName, String lastName) {
        }
        record Actor(Name name) {
        }
        record Category(String name) {
        }
        record Film(String title, List<Actor> actors, List<Category> categories) {
        }

        List<Film> listFilm =
                ctx.select(
                                FILM.TITLE,
                                multiset(
                                        select(
                                                row(
                                                        ACTOR.FIRST_NAME,
                                                        ACTOR.LAST_NAME
                                                ).mapping(Name::new))
                                                .from(ACTOR)
                                                .join(FILM_ACTOR)
                                                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ).convertFrom(r -> r.map(mapping(Actor::new))),
                                multiset(
                                        select(CATEGORY.NAME)
                                                .from(CATEGORY)
                                                .innerJoin(FILM_CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                ).convertFrom(r -> r.map(mapping(Category::new)))
                        )
                        .from(FILM)
                        .orderBy(FILM.TITLE)
                        .limit(5)
                        .fetch(mapping(Film::new));


        ResultQuery<Record3<String, List<Actor>, List<Category>>> query =
                ctx.select(
                                FILM.TITLE,
                                multiset(
                                        select(
                                                row(
                                                        ACTOR.FIRST_NAME,
                                                        ACTOR.LAST_NAME
                                                ).mapping(Name::new))
                                                .from(ACTOR)
                                                .join(FILM_ACTOR)
                                                .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ).convertFrom(r -> r.map(mapping(Actor::new))),
                                multiset(
                                        select(CATEGORY.NAME)
                                                .from(CATEGORY)
                                                .innerJoin(FILM_CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                ).convertFrom(r -> r.map(mapping(Category::new)))
                        )
                        .from(FILM)
                        .orderBy(FILM.TITLE);

        SelectQuery<Record3<String, List<Actor>, List<Category>>> selectQuery = ctx.select(
                        FILM.TITLE,
                        multiset(
                                select(
                                        row(
                                                ACTOR.FIRST_NAME,
                                                ACTOR.LAST_NAME
                                        ).mapping(Name::new))
                                        .from(ACTOR)
                                        .join(FILM_ACTOR)
                                        .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                        ).convertFrom(r -> r.map(mapping(Actor::new))),
                        multiset(
                                select(CATEGORY.NAME)
                                        .from(CATEGORY)
                                        .innerJoin(FILM_CATEGORY)
                                        .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                        .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                        ).convertFrom(r -> r.map(mapping(Category::new)))
                )
                .from(FILM)
                .orderBy(FILM.TITLE).getQuery();



        for (Film film : listFilm) {
            println("Film %s with categories %s and actors %s ".formatted(film.title, film.categories, film.actors));
        }

        description = "MULTISET combined with ad-hoc converters and nested rows";
        Result<Record> res = ctx.fetch(selectQuery.getSQL());
        resultQueryToTxt(selectQuery, //Query
                res,
                description,
                "nested_records/nesting_to_many_relationships/multiset_map_to_ad_hoc_converters",  //file_name
                List.of("film_actor", "actor", "film","film_category", "category"),
                "sakila" //schema in use
        );

    }

    void multisetMappingIntoJavaRecords() throws SQLException, FileNotFoundException {
        record Actor(String firstname, String lastname){}
        record Category(String name){}

        record Film(
                List<Film> films,
                String title,
                List<Actor> actors
        ) {}
        record Title(String title) {}

        Field<Title> title = FILM.TITLE.convertFrom(Title::new);

            Result<Record3<String, List<Actor>, List<Category>>> res =
                    ctx
                    .select(
                            FILM.TITLE,
                            multiset(
                                    select(
                                            ACTOR.FIRST_NAME,
                                            ACTOR.LAST_NAME
                                    )
                                            .from(ACTOR).join(FILM_ACTOR)
                                            .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                            .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                            ).convertFrom(r -> r.map(mapping(Actor::new))),
                            multiset(
                                    select(CATEGORY.NAME)
                                            .from(CATEGORY)
                                            .innerJoin(FILM_CATEGORY)
                                            .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                            .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                            ).convertFrom(r -> r.map(mapping(Category::new)))
                    )
                    .from(FILM)
                    .where(FILM.TITLE.like("A%"))
                    .orderBy(FILM.TITLE)
                    .limit('5')
                    .fetch();

            println(res);


        Result<Record4<
                String,                   // FILM.TITLE
                Result<Record2<
                        String,               // ACTOR.FIRST_NAME
                        String                // ACTOR.LAST_NAME
                        >>,                       // "actors"
                Result<Record1<String>>,  // CATEGORY.NAME
                Result<Record4<
                        String,               // CUSTOMER.FIRST_NAME
                        String,               // CUSTOMER.LAST_NAME
                        Result<Record2<
                                LocalDateTime,    // PAYMENT.PAYMENT_DATE
                                BigDecimal        // PAYMENT.AMOUNT
                                >>,
                        BigDecimal            // "total"
                        >>                        // "customers"
                >> result =
                ctx.select(

                                // Get the films
                                FILM.TITLE,

                                // and all actors that played in the film
                                multiset(
                                        select(
                                                ACTOR.FIRST_NAME,
                                                ACTOR.LAST_NAME
                                        )
                                                .from(FILM_ACTOR)
                                                .innerJoin(ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                                .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                                ).as("actors"),

                                // ... and all categories that categorise the film
                                multiset(
                                        select(CATEGORY.NAME)
                                                .from(FILM_CATEGORY)
                                                .join(CATEGORY)
                                                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                                ).as("categories"),

                                // ... and all customers who rented the film, as well
                                // as their payments
                                multiset(
                                        select(
                                                CUSTOMER.FIRST_NAME,
                                                CUSTOMER.LAST_NAME,
                                                multisetAgg(
                                                        PAYMENT.PAYMENT_DATE,
                                                        PAYMENT.AMOUNT
                                                ).as("payments"),
                                                sum(PAYMENT.AMOUNT).as("total"))
                                                .from(PAYMENT)
                                                .join(CUSTOMER).on(PAYMENT.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                                                .join(RENTAL).on(PAYMENT.RENTAL_ID.eq(RENTAL.RENTAL_ID))
                                                .join(INVENTORY).on(RENTAL.INVENTORY_ID.eq(INVENTORY.INVENTORY_ID))
                                                .where(INVENTORY.FILM_ID.eq(FILM.FILM_ID))
                                                .groupBy(
                                                        CUSTOMER.CUSTOMER_ID,
                                                        CUSTOMER.FIRST_NAME,
                                                        CUSTOMER.LAST_NAME)
                                ).as("customers")
                        )
                        .from(FILM)
                        .where(FILM.TITLE.like("A%"))
                        .orderBy(FILM.TITLE)
                        .limit('5')
                        .fetch();

        query =
        ctx.select(

                        // Get the films
                        FILM.TITLE,

                        // and all actors that played in the film
                        multiset(
                                select(
                                        ACTOR.FIRST_NAME,
                                        ACTOR.LAST_NAME
                                )
                                        .from(FILM_ACTOR)
                                        .innerJoin(ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                        ).as("actors"),

                        // ... and all categories that categorise the film
                        multiset(
                                select(CATEGORY.NAME)
                                        .from(FILM_CATEGORY)
                                        .join(CATEGORY)
                                        .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                        .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                        ).as("categories"),

                        // ... and all customers who rented the film, as well
                        // as their payments
                        multiset(
                                select(
                                        CUSTOMER.FIRST_NAME,
                                        CUSTOMER.LAST_NAME,
                                        multisetAgg(
                                                PAYMENT.PAYMENT_DATE,
                                                PAYMENT.AMOUNT
                                        ).as("payments"),
                                        sum(PAYMENT.AMOUNT).as("total"))
                                        .from(PAYMENT)
                                        .join(CUSTOMER).on(PAYMENT.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID))
                                        .join(RENTAL).on(PAYMENT.RENTAL_ID.eq(RENTAL.RENTAL_ID))
                                        .join(INVENTORY).on(RENTAL.INVENTORY_ID.eq(INVENTORY.INVENTORY_ID))
//                                                .join(FILM).on(INVENTORY.FILM_ID.eq(FILM.FILM_ID))
                                        .where(INVENTORY.FILM_ID.eq(FILM.FILM_ID))
                                        .groupBy(
                                                CUSTOMER.CUSTOMER_ID,
                                                CUSTOMER.FIRST_NAME,
                                                CUSTOMER.LAST_NAME)
                        ).as("customers")
                )
                .from(FILM)
                .where(FILM.TITLE.like("A%"))
                .orderBy(FILM.TITLE)
                .limit('5');

        println(query(ctx.select(multiset(
                select(ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME
                )
                        .from(FILM_ACTOR)
                        .innerJoin(ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                        .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
        ).as("actors")).getSQL()
        ).getSQL());

        resultQueryToTxt(
                query,
                result,
                description,
        "nested_records/mySql_multiset/multiset_agg_aggregate_function",  //file_name
                List.of("film_actor", "actor", "film","film_category", "category"),
                "sakila");

        }

    void nestingToManyRelationshipsBenchmark(String currentSchemaName) {
        record DNCategory (String name) {}
        record DNFilm (long id, String title, List<DNCategory> categories) {}
        record DNName (String firstName, String lastName) {}
        record DNActor (long id, DNName name, List<DNFilm> films) {}

        ResultQuery<Record> res = resultQuery("""
                select
                  film.title,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object(
                            'first_name', t.first_name,
                            'last_name', t.last_name
                          ) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select alias_78509018.first_name, alias_78509018.last_name
                      from film_actor
                        join actor as alias_78509018
                          on film_actor.actor_id = alias_78509018.actor_id
                      where film_actor.film_id = film.film_id
                    ) as t
                  ) as actors,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object('name', t.name) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select alias_130639425.name
                      from film_category
                        join category as alias_130639425
                          on film_category.category_id = alias_130639425.category_id
                      where film_category.film_id = film.film_id
                    ) as t
                  ) as categories,
                  (
                    select coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object(
                            'first_name', t.first_name,
                            'last_name', t.last_name,
                            'payments', t.payments,
                            'total', t.total
                          ) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select
                        alias_63965917.first_name,
                        alias_63965917.last_name,
                        json_merge_preserve(
                          '[]',
                          concat(
                            '[',
                            group_concat(json_object(
                              'payment_date', payment.payment_date,
                              'amount', payment.amount
                            ) separator ','),
                            ']'
                          )
                        ) as payments,
                        sum(payment.amount) as total
                      from payment
                        join (
                          rental as alias_102068213
                            join customer as alias_63965917
                              on alias_102068213.customer_id = alias_63965917.customer_id
                            join inventory as alias_116526225
                              on alias_102068213.inventory_id = alias_116526225.inventory_id
                        )
                          on payment.rental_id = alias_102068213.rental_id
                      where alias_116526225.film_id = film.film_id
                      group by alias_63965917.customer_id, alias_63965917.first_name, alias_63965917.last_name
                    ) as t
                  ) as customers
                from film
                where film.title like 'A%'
                order by film.title
                limit 5
                """);

        ctx.fetch(res.getSQL()).forEach(out::println);

        Query query = query("""
                SELECT
                  film.title,
                  (
                    SELECT coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object(
                            'first_name', t.first_name,
                            'last_name', t.last_name
                          ) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    FROM (
                      SELECT a.first_name, a.last_name
                      FROM film_actor
                        JOIN actor a
                          ON film_actor.actor_id = a.actor_id
                      WHERE film_actor.film_id = film.film_id
                    ) AS t
                  ) AS actors,
                  (
                    SELECT coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object('name', t.name) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    FROM (
                      SELECT c.name
                      from film_category
                        join category c
                          on film_category.category_id = c.category_id
                      where film_category.film_id = film.film_id
                    ) AS t
                  ) AS categories,
                  (
                    SELECT coalesce(
                      json_merge_preserve(
                        '[]',
                        concat(
                          '[',
                          group_concat(json_object(
                            'first_name', t.first_name,
                            'last_name', t.last_name,
                            'payments', t.payments,
                            'total', t.total
                          ) separator ','),
                          ']'
                        )
                      ),
                      json_array()
                    )
                    from (
                      select
                        c.first_name,
                        c.last_name,
                        json_merge_preserve(
                          '[]',
                          concat(
                            '[',
                            group_concat(json_object(
                              'payment_date', payment.payment_date,
                              'amount', payment.amount
                            ) separator ','),
                            ']'
                          )
                        ) as payments,
                        sum(payment.amount) as total
                      from payment
                        join (
                          rental as r
                            join customer as c
                              on r.customer_id = c.customer_id
                            join inventory as i
                              on r.inventory_id = i.inventory_id
                        )
                          on payment.rental_id = r.rental_id
                      where i.film_id = film.film_id
                      group by c.customer_id, c.first_name, c.last_name
                    ) as t
                  ) as customers
                FROM FILM
                """);


        List<DNActor> list = ctx.select(
                        ACTOR.ACTOR_ID,
                        row(
                                ACTOR.FIRST_NAME,
                                ACTOR.LAST_NAME
                        ).mapping(DNName::new),
                        multiset(
                                select(
                                        FILM.FILM_ID,
                                        FILM.TITLE,
                                        multiset(
                                                select(CATEGORY.NAME)
                                                        .from(CATEGORY)
                                                        .join(FILM_CATEGORY)
                                                        .on(CATEGORY.CATEGORY_ID.eq(CATEGORY.CATEGORY_ID))
                                                        .where(FILM_CATEGORY.FILM_ID.eq(FILM_ACTOR.FILM_ID))
                                        ).convertFrom(r -> r.map(mapping(DNCategory::new)))
                                )
                                        .from(FILM)
                                        .join(FILM_ACTOR).on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
                                        .where(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                        ).convertFrom(r -> r.map(mapping(DNFilm::new))))
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.eq(1L))
                .fetch(mapping(DNActor::new));

        ResultQuery<Record4<Long, String, String, Result<Record3<Long, String, Result<Record1<String>>>>>> resultQuery = ctx.select(
                        ACTOR.ACTOR_ID,
                                ACTOR.FIRST_NAME,
                                ACTOR.LAST_NAME,
                        multiset(
                                select(
                                        FILM.FILM_ID,
                                        FILM.TITLE,
                                        multiset(
                                                select(CATEGORY.NAME)
                                                        .from(CATEGORY)
                                                        .join(FILM_CATEGORY)
                                                        .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                                                        .where(FILM_CATEGORY.FILM_ID.eq(FILM_ACTOR.FILM_ID))
                                        )
                                )
                                        .from(FILM)
                                        .join(FILM_ACTOR)
                                        .on(FILM.FILM_ID.eq(FILM_ACTOR.FILM_ID))
                                        .where(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                        ))
                .from(ACTOR);

        println(resultQuery.getSQL());
        ctx.fetch(resultQuery.getSQL()).forEach(out::println);


        //       where film.title LIKE 'A%'
        //                order by film.title
        //                limit 5

        ctx.fetch(resultQuery.getSQL()).forEach(out::println);
        description = "Benchmark";

        resultQueryToTxt(resultQuery, //Query
                resultQuery.fetch(),
                description,
                "nested_records/nesting_to_many_relationships/benchmark",  //file_name
                List.of("film_actor", "actor", "film","film_category", "category"),
                "sakila" //schema in use
        );


    }

}


