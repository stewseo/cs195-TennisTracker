package com.example.cs195tennis.model;


import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.Dao.Table.DefaultCatalog;
import org.jooq.*;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;
import org.jooq.impl.SchemaImpl;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.using;

public class Public extends SchemaImpl {

    @Serial
    private static final long serialVersionUID = -2049410122;


    public static final Public PUBLIC = new Public();

    public final MatchPointByPoint MATCH = MatchPointByPoint.MATCHPOINT;
    public final GrandSlam TOURNAMENT = GrandSlam.GRANDSLAM;
    public final WtaPlayer PLAYER = WtaPlayer.WTA_PLAYER;
    public final WtaRank RANK = WtaRank.WTA_RANK;


    private Public() {

        super("");
    }

    private void addSchema(){
        Settings settings = new Settings()
                .withRenderMapping(new RenderMapping()
                        .withSchemata(
                                new MappedSchema().withInput("Test")
                                        .withOutput("Public")));

        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE, settings);


    }


    private static Schema schema(Name name) {
        return null;
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
                MatchPointByPoint.MATCHPOINT,
                GrandSlam.GRANDSLAM,
                WtaPlayer.WTA_PLAYER,
                WtaRank.WTA_RANK
        );
    }

}