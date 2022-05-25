package com.example.cs195tennis.Dao;

import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.jooq.impl.DSL.*;


public class AtpPlayerDao {

    AtpPlayerDao() {
    }

    static {
        updateTournaments();
    }

    static DSLContext ctx() {
        return using(Database.connect(), SQLDialect.SQLITE);
    }

    public static void main(String[] args) {
        getAtpPlayerRank();
    }

    private static void updateTournaments() {

    }
//    static ObservableList<AtpPlayer> getAtpPlayer() {
//        ctx().selectQuery(table("AtpPlayer")).fetchMany().forEach(player -> {
//
//    }}

    static ObservableList<AtpPlayer> getAtpPlayerRank() {

        ObservableList<AtpPlayer> m = FXCollections.observableArrayList();

        ctx().selectQuery(table("AtpPlayerRanking")).fetch().stream().filter(Objects::nonNull).forEach(rank -> {

            var v1 = rank.get(1);
            var v2 = rank.get(2);
            var v3 = rank.get(3);
            var v4 = rank.get(4);

            System.out.println(v3);

            ctx().select(field(v3.toString()))
                    .from("AtpPlayer")
                    .forEach(record -> {

                        String match_num = record.getValue("player").toString();

                        String name = record.get(2).toString() + " "  + record.get(2).toString();

                        m.add(new AtpPlayer(record.get(0).toString(), name, new String[]{record.get(3).toString(), record.get(4).toString()},
                                new PlayerRanking(v1.toString(),v2.toString(),v3.toString(),v4.toString())));

                    });


//            m.add(new PlayerRanking(v1.toString(), v2.toString(), v3.toString(), v4.toString()));
        });

        return m;
    }
}















