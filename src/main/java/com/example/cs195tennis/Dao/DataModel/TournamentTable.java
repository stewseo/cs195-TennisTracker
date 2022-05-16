package com.example.cs195tennis.Dao.DataModel;

import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.database.Database;
import org.jooq.*;
import org.jooq.impl.*;
import org.jooq.meta.TableDefinition;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.using;
import static org.jooq.impl.SQLDataType.SMALLINT;
import static org.jooq.impl.SQLDataType.VARCHAR;


public class TournamentTable extends CustomTable<TournamentRecord> {

    private static Collection<Field<?>> fields;
    public static final TournamentTable TOURNAMENT = new TournamentTable();
    public final TableField<TournamentRecord, Short>  ID = createField(name("ID"), SMALLINT);
    public final TableField<TournamentRecord, String> NAME = createField(name("TOURNEY_NAME"), VARCHAR);
    public final TableField<TournamentRecord, String> SURFACE = createField(name("SURFACE"), VARCHAR);
    public final TableField<TournamentRecord, String> LEVEL = createField(name("LEVEL"), VARCHAR);
    public final TableField<TournamentRecord, String> DATE = createField(name("TOURNEY_DATE"), VARCHAR);
    public final TableField<TournamentRecord, String> DRAW_SIZE = createField(name("DRAW_SIZE"), VARCHAR);

    protected TournamentTable() {
        super(name("GRANDSLAM"));
    }

    @Override
    public Class<? extends TournamentRecord> getRecordType() {
        return TournamentRecord.class;
    }

    public static Field<String> toChar(Field<?> field, String format) {
        return CustomField.of("to_char", VARCHAR, ctx -> {});
    }

    public void tableMeta(String tableRef){
        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
    }

    public List<Table<?>> allTablesInDb() {

        DSLContext create = using(Database.connect(), SQLDialect.SQLITE);

        List<Table<?>> list = create.meta().getTables().stream().toList();

        return list;

    }

    public void listenColumn(){

        var parser =
                DSL.using(new DefaultConfiguration().set(new DefaultVisitListener() {
                    @Override
                    public void visitStart(VisitContext ctx) {
                        if (ctx.queryPart() instanceof Field
                                && !(ctx.queryPart() instanceof Param))
                            System.out.println(((Named) ctx.queryPart()).getQualifiedName());
                    }
                })).parser();
    }

    public static void getFields() throws SQLException {
        fields = TournamentDao.getTournamentObservable();
    }

    public static void main(String[] args) throws SQLException {
        getFields();
    }

}







