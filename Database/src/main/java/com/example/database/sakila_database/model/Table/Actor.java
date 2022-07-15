package com.example.database.sakila_database.model.Table;

import com.example.database.sakila_database.model.Table.Record.ActorRecord;
import com.example.database.sakila_database.schema.Indexes;
import com.example.database.sakila_database.schema.Keys;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.List;

import java.time.LocalDateTime;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class Actor extends TableImpl<ActorRecord> {
    public static final Actor ACTOR = new Actor();

    public final TableField<ActorRecord, Long> ACTOR_ID =
            createField(name("ACTOR_ID"), BIGINT);

    public final TableField<ActorRecord, String> FIRST_NAME  =
            createField(name("FIRST_NAME"), VARCHAR);

    public final TableField<ActorRecord, String> LAST_NAME  =
            createField(name("LAST_NAME"), VARCHAR);

    public final TableField<ActorRecord, LocalDateTime> LAST_UPDATE =
            createField(DSL.name("last_update"),
                    SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()",
                            SQLDataType.LOCALDATETIME)), this, "");


    private Actor(Name alias, Table<ActorRecord> aliased) {
        this(alias, aliased, null);
    }

    private Actor(Name alias, Table<ActorRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public Actor(Name alias) {
        this(alias, ACTOR);
    }

    public Actor(String alias) {
        this(DSL.name(alias), ACTOR);
    }

    public Actor() {
        this(DSL.name("actor"), null);
    }

    public <O extends Record> Actor(Table<O> child, ForeignKey<O, ActorRecord> key) {
        super(child, key, ACTOR);
    }

    @Override
    public List<Index> getIndexes() {
        return List.of(Indexes.IDX_ACTOR_LAST_NAME);
    }

    @Override
    public Identity<ActorRecord, Long> getIdentity() {
        return (Identity<ActorRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ActorRecord> getPrimaryKey() {
        return Keys.ACTOR_PKEY;
    }

    @Override
    public Actor as(String alias) {
        return new Actor(DSL.name(alias), this);
    }

    @Override
    public Actor as(Name alias) {
        return new Actor(alias, this);
    }


    @Override
    public Actor rename(String name) {
        return new Actor(DSL.name(name), null);
    }


    @Override
    public Actor rename(Name name) {
        return new Actor(name, null);
    }



    @Override
    public Class<? extends ActorRecord> getRecordType() {
        return ActorRecord.class;
    }
}
