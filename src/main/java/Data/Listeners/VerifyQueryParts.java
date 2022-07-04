package Data.Listeners;

import org.jooq.Query;
import org.jooq.Select;
import org.jooq.SelectWhereStep;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.out;
import static org.jooq.impl.DSL.table;

public class VerifyQueryParts {

    public VerifyQueryParts() {}

    public static Query verifyQuery(Query query) {

        testQueryString(query);

        if(query.getBindValues().size() > 1) {
            out.println("Bind values size: " + query.getBindValues().size());
            out.println("Bind values: " + query.getBindValues());
        }
        if(query.getParams().size() > 1) {
            out.println("Params Size: " + query.getParams().size());
        }

        return query;
    }

    private static void testData(Query query) {

        if(query.configuration().data().size() > 100) {
            out.println("config data size: " +
                    Objects.requireNonNull(
                                    query.configuration()
                            )
                            .data().size()
            );

            out.println("config data: " +
                    Objects.requireNonNull(
                                    query.configuration()
                            )
                            .data()
            );
        }



    }

    private static void selectSteps(Select<?> select){
        SelectWhereStep step;
    }
    private static void testQueryString(Query query) {
        out.println("Query: " + query.getSQL());
    }
    private static void testListeners(Query query) {

        if (query.configuration().visitListenerProviders().length > 100) {
            out.println("config visitListenerProviders: " +
                    Arrays.toString(Objects.requireNonNull(
                                    query.configuration())
                            .visitListenerProviders())
            );

            out.println("config recordUnmapperProvider: " +
                    Objects.requireNonNull(
                                    query.configuration())
                            .recordUnmapperProvider()
            );

            out.println("config recordUnmapperProvider: " +
                    Arrays.toString(
                            Objects.requireNonNull(
                                            query.configuration())
                                    .recordListenerProviders()
                    )
            );
        }
    }

}
