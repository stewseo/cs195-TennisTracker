package com.example.database.sakila_database.SakilaModel.Table;

import com.example.database.sakila_database.SakilaModel.Record.FilmActorRecord;
import com.example.database.sakila_database.SakilaModel.Record.StoreRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;

public class Store extends CustomTable<StoreRecord> {
    public static final Store STORE = new Store();

    public Store() {
        super(name("store"));
    }

    protected Store(Name name, Schema schema) {
        super(name, schema);
    }


    @Override
    public Class<? extends StoreRecord> getRecordType() {
        return StoreRecord.class;
    }
}
