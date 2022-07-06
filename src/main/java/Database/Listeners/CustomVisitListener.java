package Database.Listeners;

import org.jooq.*;
import org.jooq.impl.DefaultVisitListener;
import org.jooq.tools.JooqLogger;

import java.util.function.Predicate;

import static com.example.cs195tennis.Util.Tools.print;
import static java.lang.System.out;
import static org.jooq.impl.DSL.*;
import static org.jooq.tools.StringUtils.abbreviate;

public class CustomVisitListener extends DefaultVisitListener {

    private QueryPart queryPart;
    JooqLogger log;
    private boolean anyAbbreviations = false;

    final Predicate<VisitContext> matcher;
    int hits;

    public CustomVisitListener(Predicate<VisitContext> matcher) {
        this.matcher = matcher;
    }
    public CustomVisitListener(){
        matcher = null;
    }


//    public void executeListener() {
//
//        DSLContext c = ctx().configuration().derive(
//                ExecuteListener
//                        .onRenderEnd(e -> e.sql("/* some telemetry comment */ " + e.sql()))
//                        .onExecuteStart(e -> out.println("Executing: " + e.sql()))
//                        .onRecordEnd(e -> out.println("Fetched record: " + e.record().formatJSON()))
//        ).dsl();
//
//        c.select(
//                field("Actor.first_name").as("a.first_name"),
//                        field("Actor.last_name").as("a.last_name")
//                )
//                .from(table("actor").as("a")
//                )
//                .where("a.ACTOR_ID < 4L")
//                .fetch();
//    }

    @Override
    public void visitStart(VisitContext context) {
        
        if (context.renderContext() != null) {
            QueryPart part = context.queryPart();
//
//            if(part instanceof Table) {
//                out.println("QueryPart: " + part.toString()
//                );
//            }
//            if(part instanceof Field) {
//                out.println("Field: " + part.toString()
//                );
//            }
            
            if (part instanceof Param<?> param) {
                Object value = param.getValue();
                out.println("Param: " + param);
                if (value instanceof String && ((String) value).length() > 10) {
                    anyAbbreviations = true;
                    out.println("value: " + value);
            
                    context.queryPart(val(abbreviate((String) value, 5)));
                }
                out.println("Query Part: " + part);
            }
        }
    }
    @Override
    public void visitEnd(VisitContext context) {

        if (anyAbbreviations) {
            out.println("Abreviations ");
            if (context.queryPartsLength() == 1) {
                context.renderContext().sql(" -- Bind values abbreviated");
            }
            if(context.queryPart() instanceof Table) {
                out.println("" +
                        "\n: Clause" + context.clause() +
                        "query part: " + queryPart
                );

        }
    }
    }


//
//        boolean invalidateCache = Arrays.stream(
//                context.clauses()
//                )
//                .anyMatch(QUERY_PART::contains);
//
//        boolean dataBoolean =
//                context.data().isEmpty();

//        if (invalidateCache) {
//            String tableName =
//                    context.queryPart() instanceof Table ?
//                    ((Table<?>) context.queryPart()).getName() : null
//                    ;

//            if (tableName != null) {
//                List<Table<?>> tables =
//                        getTableList(tableName)
//                        ;
//
//                if (!tables.isEmpty()) {
//                    tables.forEach(CustomVisitListener::accept)
//                    ;
//
//                }
//            }
//        }


//    private QueryPart start(QueryPart part) {
//        VisitContext visitContext;
//            return visitParts.peekLast();
//    }


//    private static void accept(Table<?> t) {
//        Arrays.stream(getTable
//                        (t.getName()
//                        )
//                        .fields()
//                )
//                .forEach(out::println
//                );
//    }

//    private final static Set<Clause> CLAUSE =
//            Set.of(BuildQuery.SELECT, BuildQuery.WHERE, BuildQuery.FROM);


//    static SelectSelectStep<?> SELECT_SELECT_STEP = select();
//    static SelectFromStep<?> SELECT_FROM_STEP;
//    static SelectJoinStep<?> SELECT_JOIN_STEP;
//    static SelectWhereStep SELECT_WHERE_STEP;
//    static SelectOnStep<?> SELECT_ON_STEP;
//    static SelectHavingStep<?> SELECT_HAVING_STEP;
//    private final static Set<Object> QUERY_PART =
//            Set.of(
//                    SELECT_SELECT_STEP,
//                    SELECT_FROM_STEP,
//                    SELECT_JOIN_STEP,
//                    SELECT_WHERE_STEP,
//                    SELECT_ON_STEP,
//                    SELECT_HAVING_STEP
//            );
}
