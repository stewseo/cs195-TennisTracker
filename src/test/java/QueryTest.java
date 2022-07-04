import Data.Database;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

import static com.example.cs195tennis.Util.Tools.title;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class QueryTest {
    Object ACTOR = DSL.using(Database.connect()).meta().getSchemas("sakila");
    Table<?>TABLE. = ctx().eta();
    @Test
    public void typeSafetySimpleQuery() {
        title("A simple type safe query");
        Result<Record2<String, String>> r =
                ctx.select(field("actor.first_name"), ACTOR)
                        .from(table("actor"))
                        .where(".LAST_NAME.like("A"))
                        .orderBy(table("ACTOR.FIRST").asc())
                        .fetch();
}

