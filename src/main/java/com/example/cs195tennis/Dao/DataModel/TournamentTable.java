package com.example.cs195tennis.Dao.DataModel;

import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.SMALLINT;
import static org.jooq.impl.SQLDataType.VARCHAR;


public class TournamentTable extends CustomTable<TournamentRecord> {
    public static final TournamentTable TOURNAMENT = new TournamentTable();

    public final TableField<TournamentRecord, Short>  ID = createField(name("TOURNEY_ID"), SMALLINT);
    public final TableField<TournamentRecord, String> NAME = createField(name("TOURNEY_NAME"), VARCHAR);
    public final TableField<TournamentRecord, String> SURFACE = createField(name("SURFACE"), VARCHAR);
    public final TableField<TournamentRecord, String> LEVEL = createField(name("LEVEL"), VARCHAR);
    public final TableField<TournamentRecord, String> DATE = createField(name("TOURNEY_DATE"), VARCHAR);
    public final TableField<TournamentRecord, String> DRAW_SIZE = createField(name("DRAW_SIZE"), VARCHAR);
    public final TableField<TournamentRecord, String> UNMATCHED = createField(name("UNMATCHED"), VARCHAR);

    protected TournamentTable() {
        super(name("TOURNAMENT"));
    }

    @Override
    public Class<? extends TournamentRecord> getRecordType() {
        return TournamentRecord.class;
    }
}







