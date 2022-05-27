package TestData;

import TestData.TestDatabase.TestConnectSqliteDB;
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

    //20220103	1	202458	7582	16
    @Test
    public void fetchCurrentWtaRanks() {
        TestConnectSqliteDB conn = new TestConnectSqliteDB();
        conn.testSqlLiteConnection();

        Table<?> wtaPlayerTable = table("WtaPlayer");
        Table<?> wtaRankTable = table("WtaPlayerRank");
        Result<?> result =
                DSL.using(conn.testSqlLiteConnection(), SQLDialect.SQLITE)
                        .select(field("player_id",field("player"),field("ranking_date"),field("rank"),field("fullName"), field("tours"), field("points")))
                        .from(wtaPlayerTable)
                        .join(wtaRankTable)
                        .on(field("player_id").eq(field("player").as("player_id"))
                        .and(field("ranking_date").between("20220509").and("202205010")))
                        .orderBy(field("rank"))
                        .fetch();

        assertEquals(result.getValues(field("ranking_date")).toString(),"20220509");
        assertEquals(result.size(), 1601);
        //Expected order by rank. rank 1 player on 05/09/2022 = Ashleigh Bartay
        assertEquals(result.getValues(field("fullName")).get(0).toString(), "Ashleigh Bartay");
    }
}
