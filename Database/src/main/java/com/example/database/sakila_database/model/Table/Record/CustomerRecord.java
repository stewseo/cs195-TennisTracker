package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.Customer;
import org.jooq.*;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerRecord extends UpdatableRecordImpl<CustomerRecord> implements Record12<Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.customer.customer_id</code>.
     */
    public void setCustomerId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.customer.customer_id</code>.
     */
    public Long getCustomerId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.customer.store_id</code>.
     */
    public void setStoreId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.customer.store_id</code>.
     */
    public Long getStoreId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.customer.first_name</code>.
     */
    public void setFirstName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.customer.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.customer.last_name</code>.
     */
    public void setLastName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.customer.last_name</code>.
     */
    public String getLastName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.customer.email</code>.
     */
    public void setEmail(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.customer.email</code>.
     */
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.customer.address_id</code>.
     */
    public void setAddressId(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.customer.address_id</code>.
     */
    public Long getAddressId() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>public.customer.activebool</code>.
     */
    public void setActivebool(Boolean value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.customer.activebool</code>.
     */
    public Boolean getActivebool() {
        return (Boolean) get(6);
    }

    /**
     * Setter for <code>public.customer.create_date</code>.
     */
    public void setCreateDate(LocalDate value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.customer.create_date</code>.
     */
    public LocalDate getCreateDate() {
        return (LocalDate) get(7);
    }

    /**
     * Setter for <code>public.customer.last_update</code>.
     */
    public void setLastUpdate(LocalDateTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.customer.last_update</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(8);
    }

    /**
     * Setter for <code>public.customer.active</code>.
     */
    public void setActive(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.customer.active</code>.
     */
    public Integer getActive() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>public.customer.full_address</code>.
     */
    public void setFullAddress(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.customer.full_address</code>.
     */
    public String getFullAddress() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.customer.full_name</code>.
     */
    public void setFullName(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.customer.full_name</code>.
     */
    public String getFullName() {
        return (String) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Customer.CUSTOMER.CUSTOMER_ID;
    }

    @Override
    public Field<Long> field2() {
        return Customer.CUSTOMER.STORE_ID;
    }

    @Override
    public Field<String> field3() {
        return Customer.CUSTOMER.FIRST_NAME;
    }

    @Override
    public Field<String> field4() {
        return Customer.CUSTOMER.LAST_NAME;
    }

    @Override
    public Field<String> field5() {
        return Customer.CUSTOMER.EMAIL;
    }

    @Override
    public Field<Long> field6() {
        return Customer.CUSTOMER.ADDRESS_ID;
    }

    @Override
    public Field<Boolean> field7() {
        return Customer.CUSTOMER.ACTIVEBOOL;
    }

    @Override
    public Field<LocalDate> field8() {
        return Customer.CUSTOMER.CREATE_DATE;
    }

    @Override
    public Field<LocalDateTime> field9() {
        return Customer.CUSTOMER.LAST_UPDATE;
    }

    @Override
    public Field<Integer> field10() {
        return Customer.CUSTOMER.ACTIVE;
    }

    @Override
    public Field<String> field11() {
        return null;
    }

    @Override
    public Field<String> field12() {
        return null;
    }


    @Override
    public Long component1() {
        return getCustomerId();
    }

    @Override
    public Long component2() {
        return getStoreId();
    }

    @Override
    public String component3() {
        return getFirstName();
    }

    @Override
    public String component4() {
        return getLastName();
    }

    @Override
    public String component5() {
        return getEmail();
    }

    @Override
    public Long component6() {
        return getAddressId();
    }

    @Override
    public Boolean component7() {
        return getActivebool();
    }

    @Override
    public LocalDate component8() {
        return getCreateDate();
    }

    @Override
    public LocalDateTime component9() {
        return getLastUpdate();
    }

    @Override
    public Integer component10() {
        return getActive();
    }

    @Override
    public String component11() {
        return getFullAddress();
    }

    @Override
    public String component12() {
        return getFullName();
    }

    @Override
    public Long value1() {
        return getCustomerId();
    }

    @Override
    public Long value2() {
        return getStoreId();
    }

    @Override
    public String value3() {
        return getFirstName();
    }

    @Override
    public String value4() {
        return getLastName();
    }

    @Override
    public String value5() {
        return getEmail();
    }

    @Override
    public Long value6() {
        return getAddressId();
    }

    @Override
    public Boolean value7() {
        return getActivebool();
    }

    @Override
    public LocalDate value8() {
        return getCreateDate();
    }

    @Override
    public LocalDateTime value9() {
        return getLastUpdate();
    }

    @Override
    public Integer value10() {
        return getActive();
    }

    @Override
    public String value11() {
        return getFullAddress();
    }

    @Override
    public String value12() {
        return getFullName();
    }

    @Override
    public CustomerRecord value1(Long value) {
        setCustomerId(value);
        return this;
    }

    @Override
    public CustomerRecord value2(Long value) {
        setStoreId(value);
        return this;
    }

    @Override
    public CustomerRecord value3(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public CustomerRecord value4(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public CustomerRecord value5(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public CustomerRecord value6(Long value) {
        setAddressId(value);
        return this;
    }

    @Override
    public CustomerRecord value7(Boolean value) {
        setActivebool(value);
        return this;
    }

    @Override
    public CustomerRecord value8(LocalDate value) {
        setCreateDate(value);
        return this;
    }

    @Override
    public CustomerRecord value9(LocalDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public CustomerRecord value10(Integer value) {
        setActive(value);
        return this;
    }

    @Override
    public CustomerRecord value11(String value) {
        setFullAddress(value);
        return this;
    }

    @Override
    public CustomerRecord value12(String value) {
        setFullName(value);
        return this;
    }

    @Override
    public CustomerRecord values(Long value1, Long value2, String value3, String value4, String value5, Long value6, Boolean value7, LocalDate value8, LocalDateTime value9, Integer value10, String value11, String value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerRecord
     */
    public CustomerRecord() {
        super(Customer.CUSTOMER);
    }

    /**
     * Create a detached, initialised CustomerRecord
     */
    public CustomerRecord(Long customerId, Long storeId, String firstName, String lastName, String email, Long addressId, Boolean activebool, LocalDate createDate, LocalDateTime lastUpdate, Integer active, String fullAddress, String fullName) {
        super(Customer.CUSTOMER);

        setCustomerId(customerId);
        setStoreId(storeId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAddressId(addressId);
        setActivebool(activebool);
        setCreateDate(createDate);
        setLastUpdate(lastUpdate);
        setActive(active);
        setFullAddress(fullAddress);
        setFullName(fullName);
    }

    /**
     * Create a detached, initialised CustomerRecord
     */
    public CustomerRecord(Long customerId, Long storeId, String firstName, String lastName, String email, Long addressId, Boolean activebool, LocalDate createDate, Integer active, String fullAddress, String fullName) {
        super(Customer.CUSTOMER);

        setCustomerId(customerId);
        setStoreId(storeId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAddressId(addressId);
        setActivebool(activebool);
        setCreateDate(createDate);
        setActive(active);
        setFullAddress(fullAddress);
        setFullName(fullName);
    }
}