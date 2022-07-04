package Data.Listeners;

import com.example.cs195tennis.Util.ConvertDataTypes;
import com.example.cs195tennis.Util.Tools;
import Data.Catalog.MyCatalog;
import Data.Schema.Public;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.QOM;

import java.sql.SQLDataException;
import java.util.List;
import java.util.Objects;

import static com.example.cs195tennis.Util.Tools.print;
import static Data.Database.ctx;
import static com.example.cs195tennis.model.Match.MATCH;
import static com.example.cs195tennis.model.Player.PLAYER;
import static com.example.cs195tennis.model.Tournament.TOURNAMENT;
import static java.lang.System.out;
import static org.jooq.impl.DSL.*;

public class VerifyTable {

    public static void verifyQueryModel(){
        Field<String> field = substring(TOURNAMENT.TOURNAMENT_NAME, 2, 4);

        if (field instanceof QOM.Substring substring) {
            Field<String> string = substring.$string();
            Field<? extends Number> startingPosition = substring.$startingPosition();
            Field<? extends Number> length = substring.$length();
        }
        QOM.Substring substring =
                (QOM.Substring) substring(
                        TOURNAMENT.TOURNAMENT_NAME,
                        2,
                        4
                );

        substring
                .$startingPosition(val(3))
                .$length(val(5)
                );

    }

    public static boolean verifyQueryParts(){
        return false;
    }

    private static void verifySchema(Schema paramSchema) {

        Objects.requireNonNull(
                        MyCatalog
                                .CATALOG
                                .getSchema(paramSchema.getName()
                                )
                )
                .tableStream()
                .filter(Objects::nonNull)
                .map(Table::fieldsRow)
                .forEach(out::println);

    }

    private static boolean verifyTables(List<Table<?>> tables) {

        for (int i = 0; i < tables.size(); i++) {
            verifyTable(tables.get(i));
        }
        return true;
    }

    private static boolean verifyTable(Table<?> table) {
        Tools.title("Testing Table: " + table.getName());

        Field<?> PLAYER1_NAME    = field("Match.player1");
        Field<?> COUNT1       = field("count(*) x");
        Field<Integer> COUNT2 = field("count(*) y", Integer.class);
        ctx()
                .select(PLAYER1_NAME, COUNT1, COUNT2)

                .from("Tournament a")
                .join("Match b")

                .on("a.id = b.tournament_id")

                .where("a.tournament_name != ?", "usopen")

                .groupBy(PLAYER1_NAME)
                .orderBy(PLAYER1_NAME)
                .fetch();
        return true;
    }

    private static boolean verifyFieldNames(String tableName) throws SQLDataException {
        Result<Record> rs = ConvertDataTypes.getRecordsFromTable(tableName);

        for (int i = 0; i < rs.fields().length; i++) {
            print(rs.get(i));
        }
        return true;
    }

    private static void verifyFieldDataTypes(String tableName) {
        Table<?> table =
                Public
                        .SCHEMA
                        .getTable(tableName);

        Field<?>[] select = {
                MATCH.PLAYER_1,
                MATCH.PLAYER_2,
                count()
        };

        Table<?> from = MATCH.join(PLAYER).on(MATCH.PLAYER_ID.eq(PLAYER.PLAYER_ID));

        GroupField[] groupBy = {PLAYER.PLAYER_ID, PLAYER.FIRST_NAME};

        SortField<?>[] orderBy = {count().asc()};

        ctx()
                .select(select)
                .from(from)
                .groupBy(groupBy)
                .orderBy(orderBy)
                .fetch();
    }
}
