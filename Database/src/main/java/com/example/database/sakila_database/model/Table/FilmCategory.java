package com.example.database.sakila_database.model.Table;

import com.example.database.sakila_database.model.Table.Record.FilmCategoryRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.LocalDateTime;

import static org.jooq.impl.DSL.name;

public class FilmCategory extends CustomTable<FilmCategoryRecord> {

    public static final FilmCategory FILM_CATEGORY = new FilmCategory();
    @Override
    public Class<FilmCategoryRecord> getRecordType() {
        return FilmCategoryRecord.class;
    }

    public final TableField<FilmCategoryRecord, Long> FILM_ID =
            createField(DSL.name("film_id"),
                    SQLDataType.BIGINT.nullable(false),
                    this,
                    "");

    public final TableField<FilmCategoryRecord, Long> CATEGORY_ID =
            createField(DSL.name("category_id"),
                    SQLDataType.BIGINT.nullable(false),
                    this,
                    "");

    public final TableField<FilmCategoryRecord, LocalDateTime> LAST_UPDATE =
            createField(DSL.name("last_update"),
                    SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()",
                    SQLDataType.LOCALDATETIME)),
                    this,
                    "");

    public FilmCategory() {
        super(name("film_category"));
    }

    protected FilmCategory(Name name, Schema schema) {
        super(name, schema);
    }

    transient Category category_;
    public Category category() {
        if(category_ == null){
            category_ = Category.CATEGORY;
        }
        return category_;
    }
}
