package com.example.database;

import com.example.database.db_connection.Connect;
import com.example.database.db_connection.Database;
import org.jooq.*;
import org.jooq.impl.CatalogImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.jooq.impl.DSL.using;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MyCatalog extends CatalogImpl {

    private static final long serialVersionUID = 1L;

    public static final MyCatalog CATALOG = new MyCatalog();

    public final String SAKILA = "sakila";
    public final String ATP_WTA_GRANDSLAMS = "atpwtagrandslams";
    public final String AP = "ap";
    public final String MY_GUITAR_SHOP = "my_guitar_shop";
    public final String speed_dating = "speed_dating";
    public final String INFORMATION_SCHEMA = "information_schema";
    public final String MY_SQL = "my_sql";
    public final String PERFORMANCE_SCHEMA = "performance_schema";
    public final String SYS = "sys";

    private MyCatalog() {
        super("");
    }


    public Schema getSchemaByName(String name) {
        return getSchemas()
                .stream()
                .filter(schema->
                        schema.getName()
                                .equals(name)
                )
                .toList()
                .get(0);
    }

}

