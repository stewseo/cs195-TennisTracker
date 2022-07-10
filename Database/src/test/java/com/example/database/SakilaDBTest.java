package com.example.database;

import com.example.database.sakila_database.SakilaModel.Table.Customer;
import com.example.database.sakila_database.verifyData.VerifyDatabase;
import com.example.database.sakila_database.verifyData.VerifySakilaDB;
import org.jooq.*;
import org.jooq.Record;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.database.sakila_database.SakilaModel.Table.Actor.ACTOR;
import static com.example.database.sakila_database.SakilaModel.Table.Address.ADDRESS;
import static com.example.database.sakila_database.SakilaModel.Table.City.CITY;
import static com.example.database.sakila_database.SakilaModel.Table.Country.COUNTRY;
import static com.example.database.sakila_database.SakilaModel.Table.Customer.CUSTOMER;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SakilaDBTest extends VerifyDatabase {
    @Test
    void verifySchema() {
    }

    @Test
    void testSyntaxJooqQueryPartsMySql() {
        title("Verify aggregate function count(*)");
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

        Result<Record> resultJooqQuery = ctx.fetch(query.getSQL());
        Result<Record> resultSqlString = ctx.fetch(sqlString);

        assertEquals(resultJooqQuery, resultSqlString);
    }

    @Test
    void verifyConsumingLargeRecords() {
    }

    @Test
    void verifyActiveRecords() {
    }

    @Test
    void verifyUnions() {
    }

    @Test
    void verifyWithInPredicates() {
    }

    @Test
    void standardisationLimitTest() {
    }

    @Test
    void joinTest() {
    }
    @Test
    void columnAlisTest() {
    }

    @Test
    void tableAliasTest() {
    }

    @Test
    void implicitJoinsTest() {
    }

    @Test
    void nestedRecordsTest() {
        ResultQuery<Record> resultQuery = null;
        String description = null;

        title("Produce all films and their actors");

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
        description = "return all columns: customer, address, city, country\n" +
                "customer_first_name = 'Mario' ";

        Result<Record> result = ctx.fetch(resultQuery);

        assertEquals(result.size(), 1);

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

        result = ctx.fetch(resultQuery);

        assertEquals(result.fieldsRow().size(), 6);

        assertEquals(result.size(), 3);

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

        result = ctx.fetch(resultQuery);


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

        result = ctx.fetch(resultQuery);

        assertEquals(result.size(), 108);

    }

    @Test
    void nestedRecordsWithAdHocConverterTest(){
        record Country(String name) {}
        record Customer(String firstName, String lastName, Country country) {}

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

        r.forEach(SakilaDBTest::println);

        Customer customer1 = new Customer("AARON","SELBY", new Country("Congo, The Democratic Republic of the"));
        assertEquals(r.get(0),customer1);

        Customer customer5 = new Customer("ALAN","KAHN", new Country("China"));
        assertEquals(r.get(4),customer5);
    }
    @Test
    void testDynamicSql() {
    }
}