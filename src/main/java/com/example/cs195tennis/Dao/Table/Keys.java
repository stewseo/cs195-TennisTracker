package com.example.cs195tennis.Dao.Table;

import com.example.cs195tennis.Dao.Record.*;
import com.example.cs195tennis.model.*;
import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {


    public static final UniqueKey<AtpRankRecord> ATP_RANK_PKEY = UniqueKeys0.ATPRANK_PKEY;
    public static final UniqueKey<WtaRankRecord> WTA_RANK_PKEY = UniqueKeys0.WTARANK_PKEY;
    public static final UniqueKey<AtpPlayerRecord> ATPPLAYER_PKEY = UniqueKeys0.ATPPLAYER_PKEY;
    public static final UniqueKey<WtaPlayerRecord> WTAPLAYER_PKEY = UniqueKeys0.WTAPLAYER_PKEY;
    public static final UniqueKey<MatchPointRecord> MATCH_PKEY = UniqueKeys0.MATCH_PKEY;
    public static final UniqueKey<GrandSlamRecord> GRANDSLAM_PKEY = UniqueKeys0.GRANDSLAM_PKEY;



    // -------------------------------------------------------------------------
    // FOREIGN KEY: Match to Tournament
    // FOREIGN KEY: Court to Tournament
    // FOREIGN KEY: Player to Match
    // -------------------------------------------------------------------------
    public static final ForeignKey<MatchPointRecord, GrandSlamRecord> MATCH__XXX = ForeignKeys0.MATCH__XXX;
    public static final ForeignKey<AtpRankRecord, AtpPlayerRecord> ATPRANK__XXX = ForeignKeys0.ATPRANK__XXX;
    public static final ForeignKey<AtpPlayerRecord, MatchPointRecord> ATP_PLAYER__XXX = ForeignKeys0.ATPPLAYER__XXX;

    public static final ForeignKey<WtaRankRecord, WtaPlayerRecord> WTARANK__XXX = ForeignKeys0.WTARANK__XXX;
    public static final ForeignKey<WtaPlayerRecord, MatchPointRecord> WTAPLAYER__XXX = ForeignKeys0.WTAPLAYER__XXX;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    /**
     * Unique and primary key definitions
     * the public schema
     */
    private static class UniqueKeys0 {
        public static final UniqueKey<ServeStatsRecord> SERVE_STATS_RECORD = Internal.createUniqueKey(ServeStats.SERVESTATS, "wtaPlayer_pkey", new TableField[]{ServeStats.SERVESTATS.SERVE_NUMBER_ID}, true);

        public static final UniqueKey<WtaPlayerRecord> WTAPLAYER_PKEY = Internal.createUniqueKey(WtaPlayer.WTA_PLAYER, "wtaPlayer_pkey", new TableField[]{WtaPlayer.WTA_PLAYER.ID}, true);
        public static final UniqueKey<AtpPlayerRecord> ATPPLAYER_PKEY = Internal.createUniqueKey(AtpPlayer.ATP_PLAYER, "atpPlayer_pkey", new TableField[]{AtpPlayer.ATP_PLAYER.ID}, true);
        public static final UniqueKey<AtpRankRecord> ATPRANK_PKEY = Internal.createUniqueKey(AtpRank.ATP_RANK, "atpRank_pkey", new TableField[]{AtpRank.ATP_RANK.ID}, true);
        public static final UniqueKey<WtaRankRecord> WTARANK_PKEY = Internal.createUniqueKey(WtaRank.WTA_RANK, "wtaRank_pkey", new TableField[]{WtaRank.WTA_RANK.ID}, true);
        public static final UniqueKey<MatchPointRecord> MATCH_PKEY = Internal.createUniqueKey(MatchPointByPoint.MATCHPOINT, "matchPoint_pkey", new TableField[]{MatchPointByPoint.MATCHPOINT.ID}, true);
        public static final UniqueKey<GrandSlamRecord> GRANDSLAM_PKEY = Internal.createUniqueKey(GrandSlam.GRANDSLAM, "tourney_pkey", new TableField[]{GrandSlam.GRANDSLAM.ID}, true);
    }

    /**
     * class for all foreign keys
     *
     */
    private static class ForeignKeys0 {
        public static final ForeignKey<AtpPlayerRecord, MatchPointRecord> ATPPLAYER__XXX = Internal.createForeignKey(Keys.ATPPLAYER_PKEY, AtpPlayer.ATP_PLAYER, "xxx", new TableField[]{AtpPlayer.ATP_PLAYER.ID}, true);
        public static final ForeignKey<WtaPlayerRecord, MatchPointRecord> WTAPLAYER__XXX = Internal.createForeignKey(Keys.WTAPLAYER_PKEY, WtaPlayer.WTA_PLAYER, "xxx", new TableField[]{WtaPlayer.WTA_PLAYER.ID}, true);
        public static final ForeignKey<AtpRankRecord, AtpPlayerRecord> ATPRANK__XXX = Internal.createForeignKey(Keys.ATP_RANK_PKEY, AtpRank.ATP_RANK, "xxx", new TableField[]{AtpRank.ATP_RANK.ID}, true);
        public static final ForeignKey<WtaRankRecord, WtaPlayerRecord> WTARANK__XXX = Internal.createForeignKey(Keys.WTA_RANK_PKEY, WtaRank.WTA_RANK, "xxx", new TableField[]{WtaRank.WTA_RANK.ID}, true);
        public static final ForeignKey<GrandSlamRecord, MatchPointRecord> GRANDSLAM__XXX =  Internal.createForeignKey(Keys.GRANDSLAM_PKEY, GrandSlam.GRANDSLAM, "xxx", new TableField[]{GrandSlam.GRANDSLAM.ID}, true);
        public static final ForeignKey<MatchPointRecord, GrandSlamRecord> MATCH__XXX =  Internal.createForeignKey(Keys.MATCH_PKEY, MatchPointByPoint.MATCHPOINT, "xxx", new TableField[]{MatchPointByPoint.MATCHPOINT.ID}, true);

    }
}