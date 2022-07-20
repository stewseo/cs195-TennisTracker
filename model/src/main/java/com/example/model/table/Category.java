package com.example.model.table;

import com.example.model.table.Record.CategoryRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;

import static org.jooq.impl.DSL.name;

public class Category extends TableImpl<CategoryRecord> {

    public static final Category CATEGORY = new Category();
    @Override
    public Class<CategoryRecord> getRecordType() {
        return CategoryRecord.class;
    }

    public final TableField<CategoryRecord, Long> CATEGORY_ID = createField(DSL.name("category_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    public final TableField<CategoryRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(25).nullable(false), this, "");

    public final TableField<CategoryRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");
    public Category() {
        super(name("category"));
    }

    Category(String name) {
        super(name(name));
    }
    protected Category(Name name, Schema schema) {
        super(name, schema);
    }
}
