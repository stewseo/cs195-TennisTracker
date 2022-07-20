package com.example.model.table.Record;

import com.example.model.table.FilmActor;
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
