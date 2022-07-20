package com.example.model.table.Record;

import com.example.model.table.Film;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

import static org.jooq.impl.DSL.name;

public class FilmRecord extends CustomRecord<FilmRecord> {

    protected FilmRecord(Table<FilmRecord> table) {
        super(table);
    }

    protected FilmRecord() {
        super(Film.FILM);
    }

    public FilmRecord(String s) {
        super(Film.FILM);
    }
}
