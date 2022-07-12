package com.example.database.sakila_database;
import com.example.database.sakila_database.schema.Public;
import org.jooq.Schema;
import org.jooq.impl.CatalogImpl;
import org.jooq.Constants;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultCatalog extends CatalogImpl {

    private static final long serialVersionUID = 1L;

    public static final DefaultCatalog DEFAULT_CATALOG = new DefaultCatalog();

    public final Public PUBLIC = Public.PUBLIC;

    private DefaultCatalog() {
        super("");
    }
    @Override
    public final List<Schema> getSchemas() {
        return Arrays.asList(
                Public.PUBLIC
        );
    }
    private static final String REQUIRE_RUNTIME_JOOQ_VERSION = Constants.VERSION;
}