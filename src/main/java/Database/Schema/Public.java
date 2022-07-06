package Database.Schema;
import Database.Catalog.MyCatalog;

import com.example.cs195tennis.model.Location.Country;
import com.example.cs195tennis.model.Organization.*;
import com.example.cs195tennis.model.Player.AtpRank;
import org.jooq.*;
import org.jooq.impl.SchemaImpl;

import java.io.Serial;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static Database.Database.ctx;
import static com.example.cs195tennis.Util.Tools.print;

public class Public extends SchemaImpl {

    @Serial
    private static final long serialVersionUID = -2049410122;


    public static final Public SCHEMA = new Public();
    public static final Public MY_GUITAR_SHOP = new Public("my_guitar_shop");

    public final Tournament GRAND_SLAM_TOURNAMENT = Tournament.TOURNAMENT;
    public final Match MATCH = Match.MATCH;
    public final Set SET = Set.SET;
    public final Game GAME = Game.GAME;
    public final WtaPlayer WTA_PLAYER = WtaPlayer.WTA_PLAYER;
    public final WtaRank WTA_RANK = WtaRank.WTA_RANK;
    public final Player ATP_PLAYER = Player.PLAYER;
    public final AtpRank ATP_RANK = AtpRank.ATP_RANK;
    public final Country COUNTRY = Country.COUNTRY;

    private Public() {
        super("my_guitar_shop", null);
    }

    private Public(String name) {
        super(name, null);
    }

    @Override
    public org.jooq.Catalog getCatalog() {
        return MyCatalog.CATALOG;
    }

    public Schema getMetaSchema() throws SQLException {
        List<Schema> schemas =
                ctx()
                        .meta()
                        .getSchemas();
        if (schemas.isEmpty()) {
            return null;
        } else {
            return schemas.get(0);
        }

    }


    @Override
    public final List<Table<?>> getTables() {

        return Arrays.<Table<?>>asList(
                Tournament.TOURNAMENT,
                Match.MATCH,
                Set.SET,
                Game.GAME,
                Player.PLAYER,
                WtaPlayer.WTA_PLAYER,
//                WtaRank.WTA_RANK,
                Player.PLAYER

//                AtpRank.ATP_RANK
        );
    }

    public UniqueKey<?> getMetaPrimaryKey() throws SQLException {

        List<UniqueKey<?>> primaryKeys = getMetaSchema().getPrimaryKeys();

        print("primary keys in db: " + primaryKeys.size());

        return primaryKeys.isEmpty() ?
                null :
                primaryKeys.get(0);
    }

    public List<Index> getMetaIndexes() throws SQLException {
        List<Index> indexes =
                getMetaSchema()
                        .getIndexes();

        return indexes.isEmpty() ?
                null :
                indexes;
    }

    public List<ForeignKey<?, ?>> getMetaForeignKeys() throws SQLException {
        List<ForeignKey<?, ?>> foreignKeys =
                getMetaSchema()
                        .getForeignKeys();

        return foreignKeys.isEmpty() ?
                null :
                foreignKeys;
    }
}