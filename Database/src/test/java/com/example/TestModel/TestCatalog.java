package com.example.TestModel;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;
import org.jooq.meta.mysql.mysql.DefaultCatalog;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TestCatalog extends SchemaImpl {

        private static final long serialVersionUID = 1L;

        public static final TestCatalog TEST_CATALOG = new TestCatalog();


        public final Author AUTHOR = Author.AUTHOR;

        public final Book BOOK = Book.BOOK;

        private TestCatalog() {
            super("R2DBC_EXAMPLE", null);
        }

        @Override
        public Catalog getCatalog() {
            return DefaultCatalog.DEFAULT_CATALOG;
        }

        @Override
        public final List<Table<?>> getTables() {
            return Arrays.asList(
                    Author.AUTHOR,
                    Book.BOOK
            );
        }
    }



