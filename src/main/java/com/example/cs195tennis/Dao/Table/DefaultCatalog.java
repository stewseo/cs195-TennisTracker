package com.example.cs195tennis.Dao.Table;

import com.example.cs195tennis.model.Public;
import org.jooq.Schema;
import org.jooq.impl.CatalogImpl;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultCatalog extends CatalogImpl {

        private static final long serialVersionUID = 1035293962;


        public static final DefaultCatalog DEFAULT_CATALOG = new DefaultCatalog();


        public final Public PUBLIC = Public.PUBLIC;

        private DefaultCatalog() {
            super("");
        }

        @Override
        public final List<Schema> getSchemas() {
            return Arrays.<Schema>asList(
                    Public.PUBLIC);
        }
    }

