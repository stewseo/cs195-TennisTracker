package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.Category;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class CategoryRecord extends CustomRecord<CategoryRecord> {

    protected CategoryRecord(Table<CategoryRecord> table) {
        super(table);
    }

    protected CategoryRecord() {
        super(Category.CATEGORY);
    }

    public CategoryRecord(String categoryRecord) {
        super(Category.CATEGORY);
    }
}


