package com.example.database.sakila_database.SakilaModel.Record;

import com.example.database.sakila_database.SakilaModel.Table.Film;
import com.example.database.sakila_database.SakilaModel.Table.Store;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class StoreRecord extends CustomRecord<StoreRecord> {

        protected StoreRecord(Table<StoreRecord> table) {
            super(table);
        }

        protected StoreRecord() {
            super(Store.STORE);
        }
    }


