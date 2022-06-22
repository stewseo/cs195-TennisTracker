package com.example.cs195tennis.model;

import com.example.cs195tennis.Dao.Record.MatchPointRecord;
import com.example.cs195tennis.Dao.Record.GrandSlamRecord;
import com.example.cs195tennis.Dao.Table.Keys;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.lang.Record;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GrandSlam extends TableImpl<GrandSlamRecord> {

    /**
     * serialize/deserialize bytecode
     */
        private static final long serialVersionUID = -798376522;

    /**
     * The reference instance of public.TOURNAMENT
     */
        public static final GrandSlam GRANDSLAM = new GrandSlam();

    public GrandSlam(MatchPointByPoint child, ForeignKey<MatchPointRecord, GrandSlamRecord> key) {
        super(child,key, GRANDSLAM);
    }


    /**
     *  class holding records for this type
     */
    @Override
        public Class<GrandSlamRecord> getRecordType() {
            return GrandSlamRecord.class;
        }
    /**
     *  column public.Touranment.id.
     */
        public final TableField<GrandSlamRecord, Integer> ID = createField(DSL.name("match_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), GRANDSLAM, "");

        public final TableField<GrandSlamRecord, String> NAME = createField(DSL.name("slam"), org.jooq.impl.SQLDataType.VARCHAR(65).nullable(false), this, "");

        public final TableField<GrandSlamRecord, Integer> MATCH_NUM = createField(DSL.name("match_num"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

        public final TableField<GrandSlamRecord, String> PLAYER_ONE = createField(DSL.name("player1"), org.jooq.impl.SQLDataType.VARCHAR(65).nullable(false), this, "");

        public final TableField<GrandSlamRecord, String> PLAYER_TWO = createField(DSL.name("player2"), org.jooq.impl.SQLDataType.VARCHAR(65).nullable(false), this, "");

        public final TableField<GrandSlamRecord, String> MATCH_ID = createField(DSL.name("matchId"),  org.jooq.impl.SQLDataType.VARCHAR(65).nullable(false), this, "");

        public final TableField<GrandSlamRecord, Integer> YEAR = createField(DSL.name("year"), SQLDataType.INTEGER, this, "");

        public final TableField<GrandSlamRecord, Integer> P1ID = createField(DSL.name("player1id"), SQLDataType.INTEGER, this, "");

        public final TableField<GrandSlamRecord, Integer> P2ID = createField(DSL.name("player2id"), SQLDataType.INTEGER, this, "");



    /**
     * Create a public.TournamentModel table reference
     */
        public GrandSlam() {
            this(DSL.name("GrandSlams"), null);
        }

    /**
     * Create an aliased public.TournamentModel table reference
     */
        public GrandSlam(String alias) {
            this(DSL.name(alias), GRANDSLAM);
        }


        public GrandSlam(Name alias) {
            this(alias, GRANDSLAM);
        }

        private GrandSlam(Name alias, Table<GrandSlamRecord> aliased) {
            this(alias, aliased, null);
        }

        private GrandSlam(Name alias, Table<GrandSlamRecord> aliased, Field<?>[] parameters) {
            super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
        }

        public <O extends Record & org.jooq.Record> GrandSlam(Table<O> child, ForeignKey<O, GrandSlamRecord> key) {
            super(child, key, GRANDSLAM);
        }


        @Override
        public Schema getSchema() {
            return Public.PUBLIC;
        }

        @Override
        public UniqueKey<GrandSlamRecord> getPrimaryKey() {
            return Keys.GRANDSLAM_PKEY;
        }

        @Override
        public List<UniqueKey<GrandSlamRecord>> getKeys() {
            return Arrays.<UniqueKey<GrandSlamRecord>>asList(Keys.GRANDSLAM_PKEY);
        }

        @Override
        public GrandSlam as(String alias) {
            return new GrandSlam(DSL.name(alias), this);
        }

        @Override
        public GrandSlam as(Name alias) {
            return new GrandSlam(alias, this);
        }

        /**
         * Rename this table
         */
        @Override
        public GrandSlam rename(String name) {
            return new GrandSlam(DSL.name(name), null);
        }


        @Override
        public GrandSlam rename(Name name) {
            return new GrandSlam(name, null);
        }

        // -------------------------------------------------------------------------
        // Row5 type methods
        // -------------------------------------------------------------------------

        @Override
        public  Row9<Integer, String ,Integer,String, String, String,Integer,Integer, Integer> fieldsRow() {
            return (Row9) super.fieldsRow();
        }
    }

