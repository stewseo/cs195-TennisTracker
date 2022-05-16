package com.example.cs195tennis.Dao.DataModel;

import com.example.cs195tennis.Dao.TournamentDao;
import com.example.cs195tennis.database.Database;
import com.example.cs195tennis.model.Tournament;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.CustomField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.meta.TableDefinition;

import java.beans.ConstructorProperties;
import java.sql.SQLException;
import java.util.*;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.SMALLINT;
import static org.jooq.impl.SQLDataType.VARCHAR;


public class TournamentTable extends CustomTable<TournamentRecord> {
    private Collection<Field<?>> fields;
    public static final TournamentTable TOURNAMENT1 = new TournamentTable();
    public final TableField<TournamentRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), TOURNAMENT1, "");
    public final TableField<TournamentRecord, String> TOURNEY_NAME = createField(DSL.name("TOURNEY_NAME"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");
    public final TableField<TournamentRecord, String> SURFACE = createField(DSL.name("SURFACE"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");
    public final TableField<TournamentRecord, String> LEVEL = createField(DSL.name("LEVEL"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");
    public final TableField<TournamentRecord, String> DATE = createField(DSL.name("TOURNEY_DATE"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");


    @SuppressWarnings({ "all", "unchecked", "rawtypes" })
    public class Tournament {
        public final String name;
        public final int id;

        @ConstructorProperties({"title", "id"})
        public Tournament(String tournamentName, int id) {
            this.name = tournamentName;
            this.id = id;

        }
    }


    protected TournamentTable() {super(name("GrandSlamTable"));}

    @Override
    @SuppressWarnings({ "all", "unchecked", "rawtypes" })
    public Class<? extends TournamentRecord> getRecordType() {
        return TournamentRecord.class;
    }

    public static Field<String> toChar(Field<?> field, String format) {
        return CustomField.of("to_char", VARCHAR, ctx -> {});
    }

    public void tableMeta(String tableRef){

        DSLContext ctx = using(Database.connect(), SQLDialect.SQLITE);
    }


    public Collection<Field<?>> getFields() {
        return fields;
    }

    public void setFields(Collection<Field<?>> fields) {
        this.fields = fields;
    }
}







