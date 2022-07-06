package Database.Alter;

import com.example.cs195tennis.model.Enum.TournamentLevel;
import com.example.cs195tennis.Util.Tools;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.SQLDataType;

import static Database.Database.ctx;
import static org.jooq.impl.DSL.constraint;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.unquotedName;
import static org.jooq.impl.SQLDataType.*;


public class CreateDatabase {

    public void createSchema() {
        ctx()
                .createSchema("Stats").execute();
    }

    public static void createTableTournament() {
        Tools.title("Creating TABLE Tournament, Primary key: tournament_id");
        try {
            Tools.print(
                    ctx()
                            .createTable("Tournament")
                            .column("tournament_id", INTEGER.identity(true))
                            .column("tournament_date", INTEGER)
                            .column("tournament_name", VARCHAR(65))
                            .column("court_id", SQLDataType.INTEGER)
                            .column("tournament_level_id", SQLDataType.INTEGER.asEnumDataType(TournamentLevel.class))
                            .constraints(
                                    constraint("tournament_pkey")
                                            .primaryKey("tournament_id"))
//                            .constraints(
//                                    constraint("fk_tournament_tournament_level")
//                                            .foreignKey("tournament_level_id")
//                                            .references("TournamentLevel",
//                                                    "tournament_level_id")
//                            )
                            .execute());

            ctx().createIndex("idx_tournament_name").on("tournament", "tournament_name").execute();

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createTableMatch() {
        Tools.title("Creating TABLE Match, Primary key: match_id. Foreign Key: tournament_id.");
        Tools.print(
                ctx()
                        .createTable("Match")
                        .column("match_id", INTEGER.identity(true))
                        .column("player1", VARCHAR(65))
                        .column("player2", VARCHAR(65))
                        .column("match_num", INTEGER)
                        .column("tournament_id", INTEGER)
                        .column("player_id", INTEGER)
                        .constraints(constraint("match_pkey")
                                .primaryKey("match_id"))

                        .constraints(constraint("fk_match_tournament")
                                .foreignKey("tournament_id")
                                .references("Tournament",
                                        "tournament_id")
                        )

                        .constraints(constraint("fk_match_player")
                                .foreignKey("player_id")
                                .references("Player",
                                        "player_id")
                        )
                        .execute());
        ctx().createIndex("idx_fk_tournament_id").on("Tournament", "tournament_id").execute();
        ctx().createIndex("idx_fk_match_player_id").on("Player", "player_id").execute();

    }

    public static void createTablePlayer() {
        Tools.title("Create Table Player, Primary key: player_id. foreign key: country_id");

        try {
            Tools.print(
                    ctx()
                            .createTable("Player")
                            .column("player_id", INTEGER)
                            .column("name_last", VARCHAR(65))
                            .column("name_first", VARCHAR(65))
                            .column("country_id", INTEGER)
                            .constraints(constraint("player_pkey")
                                    .primaryKey("player_id"))

                            .constraints(constraint("fk_player_country")
                                    .foreignKey("country_id")
                                    .references("Country",
                                            "country_id")
                            )
                            .execute());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createTableAddress() {

    }

    public static void createTableCity() {

    }

    public static void createTableCountry() {
        Tools.title("Creating TABLE Country, Primary key: country_id");
        try {
            Tools.print(
                    ctx()
                            .createTable("Country")
                            .column("country_id", INTEGER.identity(true))
                            .column("country", VARCHAR(50))
                            .constraints(
                                    constraint("country_pkey")
                                            .primaryKey("country_id"))
                            .execute());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createTableSetGame() {
        Tools.title("Create TABLE SET, Primary key: set_id. Foreign key: match_id");
        try {
            Tools.print(
                    ctx()
                            .createTableIfNotExists("Set")
                            .column("set_id", INTEGER)
                            .column("set_num", INTEGER)
                            .column("point_num", INTEGER)
                            .column("match_id", INTEGER)
                            .constraints(
                                    constraint("set_pkey")
                                            .primaryKey("set_id"))

                            .constraints(
                                    constraint("fk_set_match")
                                            .foreignKey("match_id")
                                            .references("Match", "match_id")
                            )
                            .execute());

            ctx().createIndex("idx_fk_match_id").on("Match", "match_id").execute();

        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        Tools.title("Create Table Game, Primary key: game_id. Foreign key: set_id");
        try {
            Tools.print(
                    ctx()
                            .createTableIfNotExists("Game")
                            .column("game_id", INTEGER)
                            .column("game_number", INTEGER)
                            .column("point_winner", INTEGER)
                            .column("p1_score", INTEGER)
                            .column("p2_score", INTEGER)
                            .column("p1_winner", INTEGER)
                            .column("p2_winner", INTEGER)
                            .column("set_id", INTEGER)
                            .constraints(constraint("game_pkey")
                                    .primaryKey("game_id"))
                            .constraints(
                                    constraint("fk_game_set")
                                            .foreignKey("set_id")
                                            .references("Set", "set_id")
                            )
                            .execute());
            ctx().createIndex("idx_fk_set_id").on("Set", "set_id").execute();

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}


