package com.example.model.table.my_guitar_shop;

import com.example.model.table.Actor;
import com.example.model.table.Film;
import com.example.model.table.FilmActor;
import com.example.model.table.Record.CountryRecord;
import com.example.model.table.Record.FilmActorRecord;
import com.example.model.table.my_guitar_shop.record.CategoriesRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;

public class Categories extends CustomTable<CategoriesRecord> {

        public static final Categories CATEGORIES = new Categories();

        public final TableField<CategoriesRecord, Long> CATEGORY_ID = createField(name("category_id"), BIGINT);

        public Categories() {
            super(name("categories"));
        }

        protected Categories(Name name, Schema schema) {
            super(name, schema);
        }

        @Override
        public Class<? extends CategoriesRecord> getRecordType() {
            return CategoriesRecord.class;
        }

}
