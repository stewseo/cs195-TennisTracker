package Data.Schema.Tables;

import com.example.cs195tennis.Util.Tools;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.exception.DataAccessException;

import java.util.List;

import static Data.Database.ctx;
import static org.jooq.impl.DSL.constraint;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.SQLDataType.INTEGER;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class CreateTables {

    public static void createTableGrandSlams() {

        Tools.title("Creating TABLE GrandSlams, Primary key: grandSlamTournament_id, Foreign key: tour_id");
        try {
            Tools.print(
                    ctx()
                            .createTableIfNotExists("GrandSlamTournament")
                            .column("grandSlamTournament_id", VARCHAR(255))
                            .column("year", INTEGER)
                            .column("slam_name", VARCHAR(255))
                            .column("player1", VARCHAR(255))
                            .column("player2", VARCHAR(255))
                            .primaryKey("grandSlamTournament_id")
                            .execute());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createMatchTable() {

            Tools.print(
                    ctx()
                            .createTable("Match")
                            .column("match_id", INTEGER.identity(true))
                            .column("player1", VARCHAR(255))
                            .column("player2", VARCHAR(255))
                            .column("match_num", INTEGER)
                            .column("grandSlamTournament_id", VARCHAR(255))
                            .primaryKey("match_id")
                            .constraints(
                                    constraint("fk").foreignKey("grandSlamTournament_id").references("GrandSlamTournament", "grandSlamTournament_id")
                            )
                            .execute());

    }

    public static void createTableSet() {
        Tools.title("Creating TABLE SET, Primary key: set_num, auto increment. Foreign key: match_id");
        try {
            Tools.print(
                    ctx()
                            .createTableIfNotExists("Set")
                            .column("set_id", INTEGER)
                            .column("set_num", INTEGER)
                            .column("point_num",INTEGER)
                            .column("matchid",VARCHAR(255))
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
        Tools.title("Create Table Game, Primary key: game_id. Foreign key: set_id");
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
