package Database.Create;

import com.example.cs195tennis.Util.Tools;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.exception.DataAccessException;
import org.jooq.types.YearToMonth;

import java.time.Year;
import java.util.List;

import static Database.Connection.Database.ctx;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class CreateTables {

    public static void createTableGrandSlams() {

        Tools.title("""
                CREATE TABLE tournament (   
                    tournament_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                    year YEAR DEFAULT NULL,
                    slam_name VARCHAR(45) NOT NULL,
                    player1 VARCHAR(45) NOT NULL,
                    player2 VARCHAR(45) NOT NULL,
                    PRIMARY KEY  (tournament_id),
                    KEY idx_fk_organization_id (organization_id),
                      CONSTRAINT `fk_tournament_organization` FOREIGN KEY (organization_id) REFERENCES organization (organization_id) ON DELETE RESTRICT ON UPDATE CASCADE
                    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
                    """);

        try {
            Tools.print(
                    ctx()
                            .createTable("tournament")
                            .column("tournament_id", BIGINT)
                            .column("year", INTERVALYEARTOMONTH)
                            .column("slam_name", VARCHAR(255))
                            .column("player1", VARCHAR(255))
                            .column("player2", VARCHAR(255))
                            .primaryKey("tournament_id")
                            .constraints(
                                    constraint("fk_tournament_organization").foreignKey("organization_id").references("organization", "organization_id")
                            )
                            .check(field(name("column1"), BIGINT).gt(1L))
                            .execute());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createMatchTable() {

        Tools.title("""
                CREATE TABLE Match (   
                    match_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                    player1 VARCHAR(45) NOT NULL,
                    player2 VARCHAR(45) NOT NULL,
                    match_num VARCHAR(45) TINYINT UNSIGNED NOT NULL,
                    tournament_id VARCHAR(45) NOT NULL,
                    PRIMARY KEY  (match_id),
                    KEY idx_fk_tournament_id (tournament_id),
                      CONSTRAINT `fk_match_tournament` FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id) ON DELETE RESTRICT ON UPDATE CASCADE
                    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
                    """);
            Tools.print(
                    ctx()
                            .createTable("Match")
                            .column("match_id", BIGINT)
                            .column("player1", VARCHAR(45))
                            .column("player2", VARCHAR(45))
                            .column("match_num", INTEGER)
                            .column("tournament_id", BIGINT)
                            .primaryKey("match_id")
                            .constraints(
                                    constraint("fk_match_tournament").foreignKey("tournament_id").references("tournament", "tournament_id")
                            )
                            .execute());

    }


    //=============================================================================
    //     TODO: Verify Create Table Set Syntax to AtpWta.sql MySqlCreate script
    //============================================================================
    private class verifyCreateSyntax {
        public static void createTableSet() {
            try {
                Tools.print(
                        ctx()
                                .createTableIfNotExists("Set")
                                .column("set_id", INTEGER)
                                .column("set_num", INTEGER)
                                .column("point_num", INTEGER)
                                .column("matchid", VARCHAR(255))
                                .column("match_id", VARCHAR(255))
                                .primaryKey("set_id")
                                .constraints(
                                        constraint("fk").foreignKey("match_id").references("Match", "match_id")
                                )
                                .execute());

            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }

        public static void createTableGame() {
            try {
                Tools.print(
                        ctx()
                                .createTable("Game")
                                .column("game_id", INTEGER)
                                .column("game_number", INTEGER)
                                .column("point_winner", INTEGER)
                                .column("p1_score", INTEGER)
                                .column("p2_score", INTEGER)
                                .column("p1_winner", INTEGER)
                                .column("p2_winner", INTEGER)
                                .column("backup_key", VARCHAR(255))
                                .column("set_id", VARCHAR(255))
                                .primaryKey("game_id")
                                .constraints(
                                        constraint("fk").foreignKey("set_id").references("Set", "set_id")
                                )
                                .execute());
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }

        public static void createTableServeStats() {
            Tools.title("Creating TABLE ServeStats, Primary key: ranking_date");
            try {
                Tools.print(
                        ctx()
                                .createTable("ServeStats")
                                .column("serveStats_id", INTEGER)
                                .column("point_num", INTEGER)
                                .column("point_server", INTEGER)
                                .column("p1_ace", INTEGER)
                                .column("p2_ace", INTEGER)
                                .column("serve_num", INTEGER)
                                .column("p1_double_fault", INTEGER)
                                .column("p2_double_fault", INTEGER)
                                .column("speed_kmh", INTEGER)
                                .primaryKey("point_num")
                                .execute());
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }

        public static void createTableRallyStats() {
            Tools.title("Creating TABLE RallyStats, Primary key: point_num");
            try {
                Tools.print(
                        ctx()
                                .createTable("RallyStats")
                                .column("point_num", INTEGER)
                                .column("p1_unforced_err", INTEGER)
                                .column("p2_unforced_err", INTEGER)
                                .column("test", INTEGER)
                                .constraints(
                                        constraint("fk").foreignKey("test").references("Game", "game_id")
                                )
                                .primaryKey("point_num")
                                .execute());
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }

        public static void createTableReturnStats() {
            Tools.title("Creating TABLE ServeStats, Primary key: point_num");
            try {
                Tools.print(
                        ctx()
                                .createTable("ReturnStats")
                                .column("point_num", INTEGER)
                                .column("p1_ace", INTEGER)
                                .column("p2_ace", INTEGER)
                                .column("serve_num", INTEGER)
                                .column("p1_double_fault", INTEGER)
                                .column("p2_double_fault", INTEGER)
                                .column("speed_kmh", INTEGER)
                                .primaryKey("point_num")
                                .execute());
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }

        public static Table<Record> createTable(String name, String primaryKey, String foreignKey, List<Field<?>> fieldArr) {
            Tools.title(" Creating TABLE " + name + "  Primary key: match_id, Foreign Key: grandSlamTournament_id");

            try {
                Tools.print(
                        ctx()
                                .createTableIfNotExists(name)
                                .column("match_id", VARCHAR(255))
                                .column("player1", VARCHAR(255))
                                .column("player2", VARCHAR(255))
                                .column("match_num", INTEGER)
                                .column("grandSlamTournament_id", VARCHAR(255))
                                .constraints(
                                        constraint("fk").foreignKey("grandSlamTournament_id").references("GrandSlamTournament", "grandSlamTournament_id")
                                )
                                .primaryKey("match_id")
                                .execute());
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
            return table(name);
        }
    }

}
