package com.example.cs195tennis.Dao.Record;


import com.example.cs195tennis.model.AtpPlayer;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AtpPlayerRecord extends UpdatableRecordImpl<AtpPlayerRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = 1297442421;

    /**
     * Setter for <code>public.article.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.article.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.article.title</code>.
     */
    public void setFirstName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.article.title</code>.
     */
    public String getFirstName() {
        return (String) get(1);
    }


    /**
     * Setter for <code>public.article.author_id</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.article.author_id</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return AtpPlayer.ATP_PLAYER.ID;
    }

    @Override
    public Field<String> field2() {
        return AtpPlayer.ATP_PLAYER.FIRST_NAME;
    }
    @Override
    public Field<String> field3() {return AtpPlayer.ATP_PLAYER.LAST_NAME;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getLastName();
    }



    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public AtpPlayerRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public AtpPlayerRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public AtpPlayerRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public AtpPlayerRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);

        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ArticleRecord
     */
    public AtpPlayerRecord() {
        super(AtpPlayer.ATP_PLAYER);
    }

    /**
     * Create a detached, initialised ArticleRecord
     */
    public AtpPlayerRecord(Integer id, String title, String description) {
        super(AtpPlayer.ATP_PLAYER);

        set(0, id);
        set(1, title);
        set(2, description);
    }

}
