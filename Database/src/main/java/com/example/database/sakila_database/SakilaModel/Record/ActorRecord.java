package com.example.database.sakila_database.SakilaModel.Record;


import com.example.database.sakila_database.SakilaModel.Table.Actor;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class ActorRecord extends CustomRecord<ActorRecord> {

    protected ActorRecord(Table<ActorRecord> table) {
        super(table);
    }

    protected ActorRecord() {
            super(Actor.ACTOR);
        }

}
