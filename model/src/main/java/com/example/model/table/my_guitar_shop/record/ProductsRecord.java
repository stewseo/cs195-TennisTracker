package com.example.model.table.my_guitar_shop.record;

import com.example.model.table.Inventory;
import com.example.model.table.Record.InventoryRecord;
import com.example.model.table.my_guitar_shop.Products;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class ProductsRecord extends CustomRecord<ProductsRecord> {
    protected ProductsRecord(Table<ProductsRecord> table) {
        super(table);
    }

    protected ProductsRecord() {
        super(Products.PRODUCTS);
    }
}
