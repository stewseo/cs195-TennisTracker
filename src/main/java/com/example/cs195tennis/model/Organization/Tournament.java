package com.example.cs195tennis.model.Organization;

import Database.Schema.Keys;
import Database.Schema.Public;
import com.example.cs195tennis.model.Record.MatchRecord;
import com.example.cs195tennis.model.Record.TournamentRecord;
import com.example.cs195tennis.model.Enum.TournamentLevel;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.lang.Record;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tournament extends TableImpl<TournamentRecord> {

    private static final long serialVersionUID = -798376522;

    public static final Tournament TOURNAMENT = new Tournament();

    public Tournament(Match child, ForeignKey<MatchRecord, TournamentRecord> key) {
        super(child, key, TOURNAMENT);
    }

    @Override
    public Class<TournamentRecord> getRecordType() {
            return TournamentRecord.class;
        }

        public final TableField<TournamentRecord, Integer> TOURNAMENT_ID = createField(DSL.name("tournament_id"), SQLDataType.INTEGER.nullable(false), TOURNAMENT, "");

        public final TableField<TournamentRecord, String> TOURNAMENT_NAME = createField(DSL.name("tournament_name"), SQLDataType.VARCHAR(65).nullable(false), this, "");

        public final TableField<TournamentRecord, Integer> COURT_ID = createField(DSL.name("court_id"),  SQLDataType.INTEGER.nullable(false), this, "");

        public final TableField<TournamentRecord, TournamentLevel> TOURNAMENT_LEVEL = createField(DSL.name("tournament_level"),  SQLDataType.INTEGER.asEnumDataType(TournamentLevel.class), this, "");

        public final TableField<TournamentRecord, Integer> DATE = createField(DSL.name("tournament_date"), SQLDataType.INTEGER, this, "");


        public Tournament() {
            this(DSL.name("Tournament"), null);
        }

        public Tournament(String alias) {
            this(DSL.name(alias), TOURNAMENT);
        }

        public Tournament(Name alias) {
            this(alias, TOURNAMENT);
        }

        private Tournament(Name alias, Table<TournamentRecord> aliased) {
            this(alias, aliased, null);
        }

        private Tournament(Name alias, Table<TournamentRecord> aliased, Field<?>[] parameters) {
            super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
        }

        public <O extends Record & org.jooq.Record> Tournament(Table<O> child, ForeignKey<O, TournamentRecord> key) {
            super(child, key, TOURNAMENT);
        }

        @Override
        public Schema getSchema() {
            return Public.SCHEMA;
        }

        @Override
        public UniqueKey<TournamentRecord> getPrimaryKey() {
            return Keys.TOURNAMENT_PKEY;
        }

        @Override
        public List<UniqueKey<TournamentRecord>> getKeys() {
            return Arrays.<UniqueKey<TournamentRecord>>asList(Keys.TOURNAMENT_PKEY);
        }

//    @Override
//    public List<ForeignKey<TournamentRecord, ?>> getReferences() {
//        return List.<ForeignKey<TournamentRecord, ?>>of(Keys.T);
//    }

        @Override
        public Tournament as(String alias) {
            return new Tournament(DSL.name(alias), this);
        }

        @Override
        public Tournament as(Name alias) {
            return new Tournament(alias, this);
        }

        @Override
        public Tournament rename(String name) {
            return new Tournament(DSL.name(name), null);
        }

        @Override
        public Tournament rename(Name name) {
            return new Tournament(name, null);
        }

        // -------------------------------------------------------------------------
        // Row5 type methods
        // -------------------------------------------------------------------------

        @Override
        public  Row5<Integer, String ,Integer,TournamentLevel, Integer> fieldsRow() {
            return (Row5) super.fieldsRow();
        }
    }

