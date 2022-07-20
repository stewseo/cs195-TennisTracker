package com.example.model.table.Record;

import com.example.model.table.FilmCategory;
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
