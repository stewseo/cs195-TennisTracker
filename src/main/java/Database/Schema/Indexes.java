package Database.Schema;

import com.example.cs195tennis.model.Organization.*;
import com.example.cs195tennis.model.Player.AtpRank;
import com.example.cs195tennis.model.Statistics.ServeStats;
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import static com.example.cs195tennis.model.Tables.MATCH;
import static com.example.cs195tennis.model.Organization.Tournament.TOURNAMENT;


@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    public static final Index IDX_FK_COUNTRY_ID =
            Internal.createIndex(
                    DSL.name("idx_fk_player_country_id"),
                    Player.PLAYER,
                    new OrderField[] { Player.PLAYER.COUNTRY_ID },
                    false
            );

    public static final Index IDX_TOURNAMENT_NAME_DATE =
            Internal.createIndex(
                    DSL.name("idx_tournament_date"),
                    TOURNAMENT,
                    new OrderField[] { TOURNAMENT.DATE },
                    false
            );

    public static final Index IDX_FK_TOURNAMENT_ID =
        Internal.createIndex(
                DSL.name("idx_fk_tournament_id"),
                Match.MATCH,
                new OrderField[] { MATCH.TOURNAMENT_ID},
                false
        );

    public static final Index IDX_FK_MATCH_ID =
            Internal.createIndex(
                    DSL.name("idx_fk_match_id"),
                    Set.SET,
                    new OrderField[] { Set.SET.MATCH_ID },
                    false
            );

    public static final Index IDX_FK_SET_ID =
            Internal.createIndex(
                    DSL.name("idx_fk_set_id"),
                    Game.GAME,
                    new OrderField[] { Game.GAME.SET_ID },
                    false
            );

    public static final Index INX_FK_GAME_ID =
            Internal.createIndex(DSL.name("idx_fk_game_id"),
                    ServeStats.SERVESTATS, new OrderField[] {
                            ServeStats.SERVESTATS.GAME_ID },
                    false
            );


    public static final Index IDX_FK_MATCH_PLAYER_ID =
            Internal.createIndex(
                    DSL.name("idx_fk_match_player_id"),
                    Match.MATCH,
                    new OrderField[] { Match.MATCH.PLAYER_ID },
                    false
            );

    public static final Index IDX_FK_RANK_PLAYER_ID =
            Internal.createIndex(
                    DSL.name("idx_fk_rank_player_id"),
                    AtpRank.ATP_RANK,
                    new OrderField[] { AtpRank.ATP_RANK.PLAYER_ID },
                    false
            );

    public static final Index IDX_FK_WTA_PLAYER_ID =
            Internal.createIndex(
                    DSL.name("idx_fk_wta_player_id"),
                    WtaRank.WTA_RANK,
                    new OrderField[] {  WtaRank.WTA_RANK.PLAYER_ID },
                    false
            );

    public static final Index IDX_ATP_RANK =
            Internal.createIndex(DSL.name("idx_atp_rank"),
                    AtpRank.ATP_RANK, new OrderField[] { AtpRank.ATP_RANK.PLAYER_ID },
                    false
            );

    public static final Index IDX_WTA_RANK =
            Internal.createIndex(DSL.name("idx_wta_rank"),
                    WtaRank.WTA_RANK, new OrderField[] { WtaRank.WTA_RANK.PLAYER_ID },
                    false
            );


}
