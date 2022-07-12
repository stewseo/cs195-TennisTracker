package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.FilmCategory;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class FilmCategoryRecord extends CustomRecord<FilmCategoryRecord> {

    protected FilmCategoryRecord(Table<FilmCategoryRecord> table) {
        super(table);
    }

    protected FilmCategoryRecord() {
        super(FilmCategory.FILM_CATEGORY);
    }
}
