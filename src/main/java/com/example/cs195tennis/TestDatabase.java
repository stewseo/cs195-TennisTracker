package com.example.cs195tennis;

import com.example.cs195tennis.Dao.DataHandeler;
import com.example.cs195tennis.database.*;
import org.jooq.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class TestDatabase {
    static DataHandeler data;

    static DSLContext ctx() {
        data = new DataHandeler();
        return using(Database.connect(), SQLDialect.SQLITE);
    }
    static DSLContext mySqlCtx() {
        data = new DataHandeler();
        return using(Database.connect(), SQLDialect.MYSQL);
    }

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306";

    public static void main(String[] args) throws SQLException, IOException {

        DataHandeler dataHandeler = new DataHandeler();
        List<Field<?>> list = new ArrayList<>();
        Tools.meta();


        TestGrandSlamMatches.joinTableTest();

//        System.out.println("player rank " + ctx().select(ATP_RANK.ID).from(ATP_RANK).fetch().size());
    }



//        TestWtaPlayerWtaRank.testPrimaryKeyToForeignKey("WtaPlayer", "WtaRank");
//
//        Result<Record4<Object,Object,Object,Object>> expected = ctx().select(field("ranking_date"),field("player"),field("rank"),field("points")).from("WtaRanks2000_2022").fetch();
//        System.out.println("expected result set size: " + expected.size());
//        Result<Record5<Integer,Integer,Integer,Integer,Integer>> actual = ctx().select(WTA_RANK.ID, WTA_RANK.RANKING_DATE, WTA_RANK.PLAYER_ID, WTA_RANK.RANKING, WTA_RANK.POINTS).from(WTA_RANK).fetch();
//        System.out.println("actual result set size: " + actual.size());

//        result.stream().flatMap(record -> Arrays.stream(record.intoArray())).forEach(System.out::println);

    }


