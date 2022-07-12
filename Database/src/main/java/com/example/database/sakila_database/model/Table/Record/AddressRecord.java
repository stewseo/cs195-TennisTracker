package com.example.database.sakila_database.model.Table.Record;

import com.example.database.sakila_database.model.Table.Address;
import org.jooq.*;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AddressRecord extends UpdatableRecordImpl<AddressRecord> implements Record8<Long, String, String, String, Long, String, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.address.address_id</code>.
     */
    public void setAddressId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.address.address_id</code>.
     */
    public Long getAddressId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.address.address</code>.
     */
    public void setAddress(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.address.address</code>.
     */
    public String getAddress() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.address.address2</code>.
     */
    public void setAddress2(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.address.address2</code>.
     */
    public String getAddress2() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.address.district</code>.
     */
    public void setDistrict(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.address.district</code>.
     */
    public String getDistrict() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.address.city_id</code>.
     */
    public void setCityId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.address.city_id</code>.
     */
    public Long getCityId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>public.address.postal_code</code>.
     */
    public void setPostalCode(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.address.postal_code</code>.
     */
    public String getPostalCode() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.address.phone</code>.
     */
    public void setPhone(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.address.phone</code>.
     */
    public String getPhone() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.address.last_update</code>.
     */
    public void setLastUpdate(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.address.last_update</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, String, String, String, Long, String, String, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Long, String, String, String, Long, String, String, LocalDateTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Address.ADDRESS.ADDRESS_ID;
    }

    @Override
    public Field<String> field2() {
        return Address.ADDRESS.ADDRESS_;
    }

    @Override
    public Field<String> field3() {
        return Address.ADDRESS.ADDRESS2;
    }

    @Override
    public Field<String> field4() {
        return Address.ADDRESS.DISTRICT;
    }

    @Override
    public Field<Long> field5() {
        return Address.ADDRESS.CITY_ID;
    }

    @Override
    public Field<String> field6() {
        return Address.ADDRESS.POSTAL_CODE;
    }

    @Override
    public Field<String> field7() {
        return Address.ADDRESS.PHONE;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return Address.ADDRESS.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getAddressId();
    }

    @Override
    public String component2() {
        return getAddress();
    }

    @Override
    public String component3() {
        return getAddress2();
    }

    @Override
    public String component4() {
        return getDistrict();
    }

    @Override
    public Long component5() {
        return getCityId();
    }

    @Override
    public String component6() {
        return getPostalCode();
    }

    @Override
    public String component7() {
        return getPhone();
    }

    @Override
    public LocalDateTime component8() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getAddressId();
    }

    @Override
    public String value2() {
        return getAddress();
    }

    @Override
    public String value3() {
        return getAddress2();
    }

    @Override
    public String value4() {
        return getDistrict();
    }

    @Override
    public Long value5() {
        return getCityId();
    }

    @Override
    public String value6() {
        return getPostalCode();
    }

    @Override
    public String value7() {
        return getPhone();
    }

    @Override
    public LocalDateTime value8() {
        return getLastUpdate();
    }

    @Override
    public AddressRecord value1(Long value) {
        setAddressId(value);
        return this;
    }

    @Override
    public AddressRecord value2(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public AddressRecord value3(String value) {
        setAddress2(value);
        return this;
    }

    @Override
    public AddressRecord value4(String value) {
        setDistrict(value);
        return this;
    }

    @Override
    public AddressRecord value5(Long value) {
        setCityId(value);
        return this;
    }

    @Override
    public AddressRecord value6(String value) {
        setPostalCode(value);
        return this;
    }

    @Override
    public AddressRecord value7(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public AddressRecord value8(LocalDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public AddressRecord values(Long value1, String value2, String value3, String value4, Long value5, String value6, String value7, LocalDateTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AddressRecord
     */
    public AddressRecord() {
        super(Address.ADDRESS);
    }

    /**
     * Create a detached, initialised AddressRecord
     */
    public AddressRecord(Long addressId, String address, String address2, String district, Long cityId, String postalCode, String phone, LocalDateTime lastUpdate) {
        super(Address.ADDRESS);

        setAddressId(addressId);
        setAddress(address);
        setAddress2(address2);
        setDistrict(district);
        setCityId(cityId);
        setPostalCode(postalCode);
        setPhone(phone);
        setLastUpdate(lastUpdate);
    }

    /**
     * Create a detached, initialised AddressRecord
     */
    public AddressRecord(Long addressId, String address, String address2, String district, Long cityId, String postalCode, String phone) {
        super(Address.ADDRESS);

        setAddressId(addressId);
        setAddress(address);
        setAddress2(address2);
        setDistrict(district);
        setCityId(cityId);
        setPostalCode(postalCode);
        setPhone(phone);
    }
}
