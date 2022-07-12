package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.CustomerList;
import org.jooq.Field;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.TableRecordImpl;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerListRecord extends TableRecordImpl<CustomerListRecord> implements Record9<Long, String, String, String, String, String, String, String, Long> {

    private static final long serialVersionUID = 1L;

    public void setId(Long value) {
        set(0, value);
    }

    public Long getId() {
        return (Long) get(0);
    }

    public void setName(String value) {
        set(1, value);
    }

    public String getName() {
        return (String) get(1);
    }

    public void setAddress(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.customer_list.address</code>.
     */
    public String getAddress() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.customer_list.zip code</code>.
     */
    public void setZipCode(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.customer_list.zip code</code>.
     */
    public String getZipCode() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.customer_list.phone</code>.
     */
    public void setPhone(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.customer_list.phone</code>.
     */
    public String getPhone() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.customer_list.city</code>.
     */
    public void setCity(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.customer_list.city</code>.
     */
    public String getCity() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.customer_list.country</code>.
     */
    public void setCountry(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.customer_list.country</code>.
     */
    public String getCountry() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.customer_list.notes</code>.
     */
    public void setNotes(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.customer_list.notes</code>.
     */
    public String getNotes() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.customer_list.sid</code>.
     */
    public void setSid(Long value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.customer_list.sid</code>.
     */
    public Long getSid() {
        return (Long) get(8);
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, String, String, String, String, String, String, String, Long> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<Long, String, String, String, String, String, String, String, Long> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return CustomerList.CUSTOMER_LIST.ID;
    }

    @Override
    public Field<String> field2() {
        return CustomerList.CUSTOMER_LIST.NAME;
    }

    @Override
    public Field<String> field3() {
        return CustomerList.CUSTOMER_LIST.ADDRESS;
    }

    @Override
    public Field<String> field4() {
        return CustomerList.CUSTOMER_LIST.ZIP_CODE;
    }

    @Override
    public Field<String> field5() {
        return CustomerList.CUSTOMER_LIST.PHONE;
    }

    @Override
    public Field<String> field6() {
        return CustomerList.CUSTOMER_LIST.CITY;
    }

    @Override
    public Field<String> field7() {
        return CustomerList.CUSTOMER_LIST.COUNTRY;
    }

    @Override
    public Field<String> field8() {
        return CustomerList.CUSTOMER_LIST.NOTES;
    }

    @Override
    public Field<Long> field9() {
        return CustomerList.CUSTOMER_LIST.SID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getAddress();
    }

    @Override
    public String component4() {
        return getZipCode();
    }

    @Override
    public String component5() {
        return getPhone();
    }

    @Override
    public String component6() {
        return getCity();
    }

    @Override
    public String component7() {
        return getCountry();
    }

    @Override
    public String component8() {
        return getNotes();
    }

    @Override
    public Long component9() {
        return getSid();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getAddress();
    }

    @Override
    public String value4() {
        return getZipCode();
    }

    @Override
    public String value5() {
        return getPhone();
    }

    @Override
    public String value6() {
        return getCity();
    }

    @Override
    public String value7() {
        return getCountry();
    }

    @Override
    public String value8() {
        return getNotes();
    }

    @Override
    public Long value9() {
        return getSid();
    }

    @Override
    public CustomerListRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public CustomerListRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public CustomerListRecord value3(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public CustomerListRecord value4(String value) {
        setZipCode(value);
        return this;
    }

    @Override
    public CustomerListRecord value5(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public CustomerListRecord value6(String value) {
        setCity(value);
        return this;
    }

    @Override
    public CustomerListRecord value7(String value) {
        setCountry(value);
        return this;
    }

    @Override
    public CustomerListRecord value8(String value) {
        setNotes(value);
        return this;
    }

    @Override
    public CustomerListRecord value9(Long value) {
        setSid(value);
        return this;
    }

    @Override
    public CustomerListRecord values(Long value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, Long value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerListRecord
     */
    public CustomerListRecord() {
        super(CustomerList.CUSTOMER_LIST);
    }

    /**
     * Create a detached, initialised CustomerListRecord
     */
    public CustomerListRecord(Long id, String name, String address, String zipCode, String phone, String city, String country, String notes, Long sid) {
        super(CustomerList.CUSTOMER_LIST);

        setId(id);
        setName(name);
        setAddress(address);
        setZipCode(zipCode);
        setPhone(phone);
        setCity(city);
        setCountry(country);
        setNotes(notes);
        setSid(sid);
    }

}