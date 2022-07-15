package com.example.database.sakila_database.model.Table.Record;


import com.example.database.sakila_database.model.Table.Language;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LanguageRecord extends UpdatableRecordImpl<LanguageRecord> implements Record3<Long, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    public void setLanguageId(Long value) {
        set(0, value);
    }

    public Long getLanguageId() {
        return (Long) get(0);
    }

    public void setName(String value) {
        set(1, value);
    }

    public String getName() {
        return (String) get(1);
    }

    public void setLastUpdate(LocalDateTime value) {
        set(2, value);
    }


    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, LocalDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Language.LANGUAGE.LANGUAGE_ID;
    }

    @Override
    public Field<String> field2() {
        return Language.LANGUAGE.NAME;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return Language.LANGUAGE.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getLanguageId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public LocalDateTime component3() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getLanguageId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public LocalDateTime value3() {
        return getLastUpdate();
    }

    @Override
    public LanguageRecord value1(Long value) {
        setLanguageId(value);
        return this;
    }

    @Override
    public LanguageRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public LanguageRecord value3(LocalDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public LanguageRecord values(Long value1, String value2, LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LanguageRecord
     */
    public LanguageRecord() {
        super(Language.LANGUAGE);
    }

    /**
     * Create a detached, initialised LanguageRecord
     */
    public LanguageRecord(Long languageId, String name, LocalDateTime lastUpdate) {
        super(Language.LANGUAGE);

        setLanguageId(languageId);
        setName(name);
        setLastUpdate(lastUpdate);
    }

    /**
     * Create a detached, initialised LanguageRecord
     */
    public LanguageRecord(Long languageId, String name) {
        super(Language.LANGUAGE);

        setLanguageId(languageId);
        setName(name);
    }


//    public LanguageRecord(Language  Language value) {
//        super(Language.L);
//
//        if (value != null) {
//            setLanguageId(value.languageId());
//            setName(value.name());
//            setLastUpdate(value.lastUpdate());
//        }
//    }
}