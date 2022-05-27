package TestData;

import TestData.TestDatabase.TestConnectSqliteDB;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestPlayerWithRank {

    @Test
    public void shouldListPlayersAndRanks() {
        TestConnectSqliteDB conn = new TestConnectSqliteDB();
        conn.testSqlLiteConnection();

        Table<?> a = table("WtaPlayer");

        Result<?> result =
                DSL.using(conn.testSqlLiteConnection(), SQLDialect.SQLITE)
                        .select()
                        .from(a)
                        .join(table("WtaPlayerRank"))
                        .on(field("player_id").eq(field("player").as("player")))
                        .orderBy(field("id"))
                        .fetch();

        assertNotEquals(0, result.size());
    }
}
