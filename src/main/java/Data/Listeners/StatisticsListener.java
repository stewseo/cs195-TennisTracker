package Data.Listeners;

import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultExecuteListener;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.cs195tennis.Util.Tools.print;
import static java.lang.System.out;
import static org.jooq.impl.DSL.val;
import static org.jooq.tools.StringUtils.abbreviate;

public class StatisticsListener extends DefaultExecuteListener {


    private static final long serialVersionUID = 7399239846062763212L;

    public static final Map<ExecuteType, Integer> STATISTICS = new ConcurrentHashMap<>();

    @Override
    public void executeStart(ExecuteContext ctx) {


        DSLContext create = DSL.using(ctx.dialect(),

                new Settings().withRenderFormatted(true));

        if (ctx.query() != null) {
            System.out.println(create.renderInlined(ctx.query()));
        }

        else if (ctx.routine() != null) {
            System.out.println(create.renderInlined(ctx.routine()));
        }
    }


    @Override
    public void start(ExecuteContext ctx) {
        STATISTICS.compute(ctx.type(), (k, v) ->
                v == null ? 1 : v + 1)
        ;

        DSLContext create = DSL.using(
                ctx.dialect(),
                new Settings().withRenderFormatted(true)
        )
                ;

        if (ctx.query() != null) {
            out.println(
                    create.renderInlined(ctx.query()
                    )
            );
        }
        if (ctx.routine() != null) {
            out.println(
                    create.renderInlined(ctx.routine()
                    )
            );
        }
    }

    @Override
    public void exception(ExecuteContext ctx) {
        DSLContext create = DSL.using(
                ctx.dialect(),
                new Settings().withRenderFormatted(true)
        );

        SQLException sqlException = ctx.sqlException();

        if (sqlException != null) {
            out.println(sqlException.toString()
            );
        }
        RuntimeException exception = ctx.exception();
        if (exception != null) {
            out.println("Exception: " + exception);
        }
        String[] serverOutput = ctx.serverOutput();

        out.println("serverOutput: " + Arrays.toString(serverOutput)
        );
    }

    @Override
    public void warning(ExecuteContext ctx) {
        print("sql warning: " + ctx.sqlWarning()
        );
    }

    @Override
    public void renderStart(ExecuteContext ctx) {
        print("Render Start: " + ctx);
//        print("Connection: " + ctx.connection());
//        print("Settings: " + ctx.settings());
//        print("Dialect: " + ctx.dialect());
//        ctx.rows();
//
//        ctx.family();
//        ctx.result();
//        ctx.routine();
//        ctx.resultLevel();
//        ctx.sql();
//        ctx.serverOutput();
//        ctx.statementExecutionCount();
    }
    @Override
    public void renderEnd(ExecuteContext ctx) {
        if (Objects.requireNonNull(ctx.sql()).matches("^(?i:(UPDATE|DELETE)(?!.* WHERE ).*)$")) {
            out.println("Delete or Update without exception ");
        }
//        print("Render End: " + ctx);
//        ctx.statementExecutionCount();
//        ctx.data();
//        ctx.query();
//        ctx.record();
//        ctx.batchSQL();
//        ctx.recordLevel();
    }

}


