package com.example.cs195tennis.Dao;

import com.example.cs195tennis.Dao.Table.DefaultCatalog;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.*;
import org.jooq.*;
import org.jooq.impl.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.cs195tennis.Dao.PlayerDao.ctx;
import static org.jooq.impl.DSL.*;

public class Schem extends SchemaImpl  {

    public static final Schem DEFAULT_SCHEMA = new Schem();


    public final GrandSlam GRANDSLAM = GrandSlam.GRANDSLAM;


    public final MatchPointByPoint MATCHPOINT = MatchPointByPoint.MATCHPOINT;

    public final WtaPlayer WTA_PLAYER = WtaPlayer.WTA_PLAYER;

    public final WtaRank WTARANK = WtaRank.WTA_RANK;

    public final AtpPlayer ATP_PLAYER = AtpPlayer.ATP_PLAYER;

    /**
     * No further instances allowed
     */
    private Schem() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
                GrandSlam.GRANDSLAM,
                MatchPointByPoint.MATCHPOINT,
                WtaPlayer.WTA_PLAYER,
                WtaRank.WTA_RANK,
                AtpPlayer.ATP_PLAYER);
    }

    public static <T extends Number> Sequence<T> sequence(Name name, DataType<T> type) {
        if (name == null)
            throw new NullPointerException();
        if (name.getName().length < 1 || name.getName().length > 2)
            throw new IllegalArgumentException("Must provide a qualified name of length 1 or 2 : " + name);
        String n = name.getName()[name.getName().length - 1];
        Schem s = name.getName().length == 2 ? (Schem) schema(name(name.getName()[0])) : null;
        return new SequenceImpl<T>(n, s, type);
    }

    public static List<Field<?>> getColumns(String tableName) {
        List<Table<?>> tables = ctx().meta().getTables();
        List<Field<?>> fields = new ArrayList<>();
        for(int i = 0; i < tables.size(); i++) {

            if(tables.get(i).getName().equals(tableName)) {
                fields.addAll(Arrays.stream(tables.get(i).fields()).toList());
                return fields;
            }
        }return null;
    }

    public static void removeEmptyTables() {
        DSLContext ctx = DSL.using(Database.connect(), SQLDialect.SQLITE);

        List<Table<?>> tables = ctx.meta().getTables();

        for (int i = 0; i < tables.size(); i++) {

            if (ctx().select().from(tables.get(i)).fetch().size() == 0) {
                DataHandeler dh = new DataHandeler();
                System.out.println("table: " + tables.get(i).getName());
//                dh.dropTable(tables.get(i).getName());
            }
        }
    }

    public static void tableInfo() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);

        List<Table<?>> tables = ctx.meta().getTables();
        System.out.println("numbers of tables in db: " + tables.size());

        Field<?> f1 = field("Test");

        tables.forEach(e->{
//            if(e.getName().equals("GrandSlams") || e.getName().equals("GrandSlamPointByPoint")) {
                System.out.println("Table Name: " + e.getName());
                System.out.println("Primary Key: " + e.getPrimaryKey());
//                System.out.println("Schema: " + e.getSchema());
                System.out.println("Columns / Fields: " + e.fieldsRow());
//                Arrays.stream(e.fields()).forEach(field->{
//                    System.out.println("field: " + field + " DataType: " + field.getDataType());
//                });
//            }
        });
    }


    public void dbMeta() {
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);

        List<org.jooq.Schema> schema = ctx.meta().getSchemas();
        List<Catalog> catalog = ctx.meta().getCatalogs();
        List<Domain<?>> domain = ctx.meta().getDomains();
        List<ForeignKey<?, ?>> foreignKeys = ctx.meta().getForeignKeys();
        List<UniqueKey<?>> uniqueKeys = ctx.meta().getUniqueKeys();
        List<UniqueKey<?>> primaryKeys = ctx.meta().getPrimaryKeys();
        List<Sequence<?>> sequences = ctx.meta().getSequences();
        List<Index> indices = ctx.meta().getIndexes();

    }
    public Schem(String name) {
        super(name);
    }

    public Schem(String name, Catalog catalog) {
        super(name, catalog);
    }

    public Schem(String name, Catalog catalog, String comment) {
        super(name, catalog, comment);
    }

    public Schem(Name name) {
        super(name);
    }

    public Schem(Name name, Catalog catalog) {
        super(name, catalog);
    }

    public Schem(Name name, Catalog catalog, Comment comment) {
        super(name, catalog, comment);
    }
}
