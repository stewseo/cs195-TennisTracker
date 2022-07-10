package com.example.database.sakila_database.SakilaModel.Record;

import com.example.database.sakila_database.SakilaModel.Table.FilmActor;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class FilmActorRecord extends CustomRecord<FilmActorRecord> {

        protected FilmActorRecord(Table<FilmActorRecord> table) {
            super(table);
        }

        protected FilmActorRecord() {
            super(FilmActor.FILM_ACTOR);
        }
    }
