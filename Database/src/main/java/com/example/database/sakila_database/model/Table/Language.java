package com.example.database.sakila_database.model.Table;

import com.example.database.sakila_database.schema.Keys;
import com.example.database.sakila_database.schema.Public;
import com.example.database.sakila_database.model.Table.Record.LanguageRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.lang.Record;
import java.time.LocalDateTime;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Language extends TableImpl<LanguageRecord> {


        private static final long serialVersionUID = 1L;

        public static final Language LANGUAGE = new Language();

    @Override
        public Class<LanguageRecord> getRecordType() {
            return LanguageRecord.class;
        }


        public final TableField<LanguageRecord, Long> LANGUAGE_ID = createField(DSL.name("language_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");


        public final TableField<LanguageRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CHAR(20).nullable(false), this, "");


        public final TableField<LanguageRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

        private Language(Name alias, Table<LanguageRecord> aliased) {
            this(alias, aliased, null);
        }

        private Language(Name alias, Table<LanguageRecord> aliased, Field<?>[] parameters) {
            super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
        }

        public Language(String alias) {
            this(DSL.name(alias), LANGUAGE);
        }


        public Language(Name alias) {
            this(alias, LANGUAGE);
        }


        public Language() {
            this(DSL.name("language"), null);
        }

        public <O extends Record & org.jooq.Record> Language(Table<O> child, ForeignKey<O, LanguageRecord> key) {
            super(child, key, LANGUAGE);
        }

        @Override
        public Schema getSchema() {
            return aliased() ? null : Public.PUBLIC;
        }

        @Override
        public Identity<LanguageRecord, Long> getIdentity() {
            return (Identity<LanguageRecord, Long>) super.getIdentity();
        }

        @Override
        public UniqueKey<LanguageRecord> getPrimaryKey() {
            return Keys.LANGUAGE_PKEY;
        }

        @Override
        public Language as(String alias) {
            return new Language(DSL.name(alias), this);
        }

        @Override
        public Language as(Name alias) {
            return new Language(alias, this);
        }

        @Override
        public Language rename(String name) {
            return new Language(DSL.name(name), null);
        }


        @Override
        public Language rename(Name name) {
            return new Language(name, null);
        }



        // -------------------------------------------------------------------------
        // Row3 type methods
        // -------------------------------------------------------------------------

        @Override
        public Row3<Long, String, LocalDateTime> fieldsRow() {
            return (Row3) super.fieldsRow();
        }


}
