package com.example.model.table.Record;

import com.example.model.table.Store;
import org.jooq.*;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StoreRecord extends UpdatableRecordImpl<StoreRecord> implements Record5<Long, Long, Long, LocalDateTime, String> {

    private static final long serialVersionUID = 1L;

    public void setStoreId(Long value) {
        set(0, value);
    }

    public Long getStoreId() {
        return (Long) get(0);
    }


    public void setManagerStaffId(Long value) {
        set(1, value);
    }


    public Long getManagerStaffId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.store.address_id</code>.
     */
    public void setAddressId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.store.address_id</code>.
     */
    public Long getAddressId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.store.last_update</code>.
     */
    public void setLastUpdate(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.store.last_update</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>public.store.full_address</code>.
     */
    public void setFullAddress(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.store.full_address</code>.
     */
    public String getFullAddress() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, Long, LocalDateTime, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, Long, Long, LocalDateTime, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Store.STORE.STORE_ID;
    }

    @Override
    public Field<Long> field2() {
        return Store.STORE.MANAGER_STAFF_ID;
    }

    @Override
    public Field<Long> field3() {
        return Store.STORE.ADDRESS_ID;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Store.STORE.LAST_UPDATE;
    }

    @Override
    public Field<String> field5() {
        return null;
    }


    @Override
    public Long component1() {
        return getStoreId();
    }

    @Override
    public Long component2() {
        return getManagerStaffId();
    }

    @Override
    public Long component3() {
        return getAddressId();
    }

    @Override
    public LocalDateTime component4() {
        return getLastUpdate();
    }

    @Override
    public String component5() {
        return getFullAddress();
    }

    @Override
    public Long value1() {
        return getStoreId();
    }

    @Override
    public Long value2() {
        return getManagerStaffId();
    }

    @Override
    public Long value3() {
        return getAddressId();
    }

    @Override
    public LocalDateTime value4() {
        return getLastUpdate();
    }

    @Override
    public String value5() {
        return getFullAddress();
    }

    @Override
    public StoreRecord value1(Long value) {
        setStoreId(value);
        return this;
    }

    @Override
    public StoreRecord value2(Long value) {
        setManagerStaffId(value);
        return this;
    }

    @Override
    public StoreRecord value3(Long value) {
        setAddressId(value);
        return this;
    }

    @Override
    public StoreRecord value4(LocalDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public StoreRecord value5(String value) {
        setFullAddress(value);
        return this;
    }

    @Override
    public StoreRecord values(Long value1, Long value2, Long value3, LocalDateTime value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StoreRecord
     */
    public StoreRecord() {
        super(Store.STORE);
    }

    /**
     * Create a detached, initialised StoreRecord
     */
    public StoreRecord(Long storeId, Long managerStaffId, Long addressId, LocalDateTime lastUpdate, String fullAddress) {
        super(Store.STORE);

        setStoreId(storeId);
        setManagerStaffId(managerStaffId);
        setAddressId(addressId);
        setLastUpdate(lastUpdate);
        setFullAddress(fullAddress);
    }

    /**
     * Create a detached, initialised StoreRecord
     */
    public StoreRecord(Long storeId, Long managerStaffId, Long addressId, String fullAddress) {
        super(Store.STORE);

        setStoreId(storeId);
        setManagerStaffId(managerStaffId);
        setAddressId(addressId);
        setFullAddress(fullAddress);
    }

    /**
     * Create a detached, initialised StoreRecord
     */

}
