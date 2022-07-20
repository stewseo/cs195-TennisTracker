package com.example.model.table.my_guitar_shop.record;

import com.example.model.table.Inventory;
import com.example.model.table.my_guitar_shop.Categories;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class CategoriesRecord extends CustomRecord<CategoriesRecord> {

    protected CategoriesRecord(Table<CategoriesRecord> table) {
        super(table);
    }

    protected CategoriesRecord() {
        super(Categories.CATEGORIES);
    }
}
