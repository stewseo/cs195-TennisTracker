package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.Inventory;
import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class InventoryRecord extends CustomRecord<InventoryRecord> {
    protected InventoryRecord(Table<InventoryRecord> table) {
        super(table);
    }

    protected InventoryRecord() {
        super(Inventory.INVENTORY);
    }
}

