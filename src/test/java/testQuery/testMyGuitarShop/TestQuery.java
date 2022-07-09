package testQuery.testMyGuitarShop;

import com.example.cs195tennis.Util.Tools;
import org.jooq.*;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static Database.Connection.Database.ctx;
import static org.jooq.impl.DSL.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestQuery {

    //============================================================================
    //              Test Visit Listener
    //              Test Exception Handling
    //============================================================================

    @Test
    public void fetchDual() {
        Tools.print(ctx().selectOne().fetch());
    }


    Predicate<Schema> schemaFilter = schema -> schema.getName().equals("sakila")
            || schema.getName().equals("speed_dating")
            || schema.getName().equals("atpwtagrandslams")
            || schema.getName().equals("sys")
            || schema.getName().equals("information_schema");

    @Test
    public void getSchema() {
        Schema sakilaSchema =
                ctx()
                        .meta()
                        .getSchemas("sakila")
                        .stream()
                        .filter(schemaFilter)
                        .filter(Objects::nonNull)
                        .toList()
                        .get(0);
    }

    @Test
    public void typeSafetySimpleQuery() {

        Field<?> FIRST_NAME = field("a.FIRST_NAME");
        Field<?> LAST_NAME = field("a.LAST_NAME");

        Field<?> COUNT1 = field("count(*) x");
        Field<Integer> COUNT2 = field("count(*) y", Integer.class);

        SortField<?> orderBy =
                Stream.of("ORDER BY ACTOR.FIRST_NAME asc".split(", "))
                        .map(String::trim)
                        .map(s -> s.split(" +"))
                        .map(s -> {
                            Field<?> field = field(s[0]);
                            return s.length == 1
                                    ? field.sortDefault()
                                    : field.sort("DESC".equalsIgnoreCase(s[1])
                                    ? SortOrder.DESC
                                    : SortOrder.ASC
                            );
                        }
                        )
                        .toList()
                        .get(0);

        assertNotNull(orderBy);

        // Adding / removing columns from the projection
        // Change the LIKE predicate's argument to an int
        Result<? extends Record3<?, ?, ?>> rs =

                ctx()
                        .select(FIRST_NAME, LAST_NAME, COUNT1)
                        .from("ACTOR ")
                        .where("ACTOR.LAST_NAME like (A%) ")
                        .orderBy(orderBy)
                        .fetch()
                ;
        assertNotEquals(0, rs.size());
    }

}





