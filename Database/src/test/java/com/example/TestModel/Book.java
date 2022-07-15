package com.example.TestModel;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


    @SuppressWarnings({ "all", "unchecked", "rawtypes" })
    public class Book extends TableImpl<BookRecord> {

        private static final long serialVersionUID = 1L;


        public static final Book BOOK = new Book();


        @Override
        public Class<BookRecord> getRecordType() {
            return BookRecord.class;
        }


        public final TableField<BookRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");


        public final TableField<BookRecord, Integer> AUTHOR_ID = createField(DSL.name("AUTHOR_ID"), SQLDataType.INTEGER.nullable(false), this, "");


        public final TableField<BookRecord, String> TITLE = createField(DSL.name("TITLE"), SQLDataType.VARCHAR(100).nullable(false), this, "");

        private Book(Name alias, Table<BookRecord> aliased) {
            this(alias, aliased, null);
        }

        private Book(Name alias, Table<BookRecord> aliased, Field<?>[] parameters) {
            super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
        }

        public Book(String alias) {
            this(DSL.name(alias), BOOK);
        }


        public Book(Name alias) {
            this(alias, BOOK);
        }

        /**
         * Create a <code>R2DBC_EXAMPLE.BOOK</code> table reference
         */
        public Book() {
            this(DSL.name("BOOK"), null);
        }

        public <O extends Record> Book(Table<O> child, ForeignKey<O, BookRecord> key) {
            super(child, key, BOOK);
        }

        @Override
        public Schema getSchema() {
            return aliased() ? null : TestCatalog.TEST_CATALOG;
        }

        @Override
        public Identity<BookRecord, Integer> getIdentity() {
            return (Identity<BookRecord, Integer>) super.getIdentity();
        }

        @Override
        public UniqueKey<BookRecord> getPrimaryKey() {
            return Keys.PK_BOOK;
        }

        @Override
        public List<ForeignKey<BookRecord, ?>> getReferences() {
            return Arrays.asList(Keys.FK_BOOK_AUTHOR);
        }

        private transient Author _author;


        public Author author() {
            if (_author == null)
                _author = new Author(this, Keys.FK_BOOK_AUTHOR);

            return _author;
        }

        @Override
        public Book as(String alias) {
            return new Book(DSL.name(alias), this);
        }

        @Override
        public Book as(Name alias) {
            return new Book(alias, this);
        }


        @Override
        public Book rename(String name) {
            return new Book(DSL.name(name), null);
        }


        @Override
        public Book rename(Name name) {
            return new Book(name, null);
        }



        // -------------------------------------------------------------------------
        // Row3 type methods
        // -------------------------------------------------------------------------

        @Override
        public Row3<Integer, Integer, String> fieldsRow() {
            return (Row3) super.fieldsRow();
        }


    }


