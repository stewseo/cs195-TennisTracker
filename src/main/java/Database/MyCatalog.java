package Database;

import Database.Connection.Database;
import org.jooq.*;
import org.jooq.impl.CatalogImpl;

import java.sql.SQLException;
import java.util.Collections;
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

    @Override
    public final List<Schema> getSchemas() {
        DSLContext ctx = using(
                Database.connect(),
                SQLDialect.MYSQL
        );

        Meta meta = ctx.meta();

        List<Schema> schemas = meta.getSchemas();

        if (meta.getSchemas().size() == 0) {
            try {
                throw new SQLException();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return schemas;
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

    public final Table<?> getTables(String tableName) {
        return
                using(
                        Database.connect(),
                        SQLDialect.MYSQL
                )
                        .meta()
                        .getTables(tableName)
                        .stream()
                        .filter(Objects::nonNull)
                        .limit(1)
                        .toList()
                        .get(0)
                ;
    }
}

