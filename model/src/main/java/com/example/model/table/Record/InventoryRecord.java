package com.example.model.table.Record;

import com.example.model.table.Inventory;
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

