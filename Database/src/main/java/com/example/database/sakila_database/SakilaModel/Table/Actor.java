package com.example.database.sakila_database.SakilaModel.Table;

import com.example.database.sakila_database.SakilaModel.Record.ActorRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class Actor extends CustomTable<ActorRecord> {
    public static final Actor ACTOR = new Actor();

    public final TableField<ActorRecord, Long> ACTOR_ID = createField(name("ACTOR_ID"), BIGINT);

    public final TableField<ActorRecord, String> FIRST_NAME  = createField(name("FIRST_NAME"), VARCHAR);
    public final TableField<ActorRecord, String> LAST_NAME  = createField(name("LAST_NAME"), VARCHAR);

    public Actor(){
        super(name("ACTOR"));
    }

    protected Actor(Name name, Schema schema) {
        super(name, schema);
    }

    @Override
    public Class<? extends ActorRecord> getRecordType() {
        return ActorRecord.class;
    }
}
