package com.example.model.table.my_guitar_shop;

import com.example.model.table.my_guitar_shop.record.CategoriesRecord;
import com.example.model.table.my_guitar_shop.record.ProductsRecord;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.SQLDataType.BIGINT;

public class Products extends CustomTable<ProductsRecord> {

    public static final Products PRODUCTS = new Products();

    public final TableField<ProductsRecord, Long> CATEGORY_ID = createField(name("product_id"), BIGINT);

    public Products() {
        super(name("products"));
    }

    protected Products(Name name, Schema schema) {
        super(name, schema);
    }

    @Override
    public Class<? extends ProductsRecord> getRecordType() {
        return ProductsRecord.class;
    }

}
