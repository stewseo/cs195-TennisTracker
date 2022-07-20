package com.example.model.table;

import com.example.model.table.Record.InventoryRecord;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.io.Serial;
import java.time.LocalDateTime;

@SuppressWarnings("NullableProblems")
public class Inventory extends TableImpl<InventoryRecord> {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final Inventory INVENTORY = new Inventory();

    @Override
    public Class<InventoryRecord> getRecordType() {
        return InventoryRecord.class;
    }
    public Inventory() {
        this(DSL.name("inventory"), null);
    }

    public <O extends Record> Inventory(Table<O> child, ForeignKey<O, InventoryRecord> key) {
        super(child, key, INVENTORY);
    }

    public final TableField<InventoryRecord, Long> INVENTORY_ID = createField(DSL.name("inventory_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");


    public final TableField<InventoryRecord, Long> FILM_ID = createField(DSL.name("film_id"), SQLDataType.BIGINT.nullable(false), this, "");


    public final TableField<InventoryRecord, Long> STORE_ID = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false), this, "");

    public final TableField<InventoryRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    private Inventory(Name alias, Table<InventoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private Inventory(Name alias, Table<InventoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public Inventory(String alias) {
        this(DSL.name(alias), INVENTORY);
    }

    public Inventory(Name alias) {
        this(alias, INVENTORY);
    }
}
