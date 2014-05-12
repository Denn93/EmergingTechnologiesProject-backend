/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value = {"http://www.jooq.org", "3.3.2"},
        comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ContainerlocationRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.generated.tables.records.ContainerlocationRecord> implements org.jooq.Record5<java.lang.Integer, java.lang.String, java.math.BigDecimal, java.math.BigDecimal, java.sql.Timestamp> {

    private static final long serialVersionUID = 558126064;

    /**
     * Create a detached ContainerlocationRecord
     */
    public ContainerlocationRecord() {
        super(jooq.generated.tables.Containerlocation.CONTAINERLOCATION);
    }

    /**
     * Create a detached, initialised ContainerlocationRecord
     */
    public ContainerlocationRecord(java.lang.Integer locationid, java.lang.String equipmentnumber, java.math.BigDecimal longitude, java.math.BigDecimal latitude, java.sql.Timestamp date) {
        super(jooq.generated.tables.Containerlocation.CONTAINERLOCATION);

        setValue(0, locationid);
        setValue(1, equipmentnumber);
        setValue(2, longitude);
        setValue(3, latitude);
        setValue(4, date);
    }

    /**
     * Getter for <code>emerging.containerlocation.locationID</code>.
     */
    public java.lang.Integer getLocationid() {
        return (java.lang.Integer) getValue(0);
    }

    /**
     * Setter for <code>emerging.containerlocation.locationID</code>.
     */
    public void setLocationid(java.lang.Integer value) {
        setValue(0, value);
    }

    /**
     * Getter for <code>emerging.containerlocation.equipmentNumber</code>.
     */
    public java.lang.String getEquipmentnumber() {
        return (java.lang.String) getValue(1);
    }

    /**
     * Setter for <code>emerging.containerlocation.equipmentNumber</code>.
     */
    public void setEquipmentnumber(java.lang.String value) {
        setValue(1, value);
    }

    /**
     * Getter for <code>emerging.containerlocation.longitude</code>.
     */
    public java.math.BigDecimal getLongitude() {
        return (java.math.BigDecimal) getValue(2);
    }

    /**
     * Setter for <code>emerging.containerlocation.longitude</code>.
     */
    public void setLongitude(java.math.BigDecimal value) {
        setValue(2, value);
    }

    /**
     * Getter for <code>emerging.containerlocation.latitude</code>.
     */
    public java.math.BigDecimal getLatitude() {
        return (java.math.BigDecimal) getValue(3);
    }

    /**
     * Setter for <code>emerging.containerlocation.latitude</code>.
     */
    public void setLatitude(java.math.BigDecimal value) {
        setValue(3, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>emerging.containerlocation.date</code>.
     */
    public java.sql.Timestamp getDate() {
        return (java.sql.Timestamp) getValue(4);
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>emerging.containerlocation.date</code>.
     */
    public void setDate(java.sql.Timestamp value) {
        setValue(4, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Record1<java.lang.Integer> key() {
        return (org.jooq.Record1) super.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Row5<java.lang.Integer, java.lang.String, java.math.BigDecimal, java.math.BigDecimal, java.sql.Timestamp> fieldsRow() {
        return (org.jooq.Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Row5<java.lang.Integer, java.lang.String, java.math.BigDecimal, java.math.BigDecimal, java.sql.Timestamp> valuesRow() {
        return (org.jooq.Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Field<java.lang.Integer> field1() {
        return jooq.generated.tables.Containerlocation.CONTAINERLOCATION.LOCATIONID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Field<java.lang.String> field2() {
        return jooq.generated.tables.Containerlocation.CONTAINERLOCATION.EQUIPMENTNUMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Field<java.math.BigDecimal> field3() {
        return jooq.generated.tables.Containerlocation.CONTAINERLOCATION.LONGITUDE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Field<java.math.BigDecimal> field4() {
        return jooq.generated.tables.Containerlocation.CONTAINERLOCATION.LATITUDE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Field<java.sql.Timestamp> field5() {
        return jooq.generated.tables.Containerlocation.CONTAINERLOCATION.DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.Integer value1() {
        return getLocationid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.String value2() {
        return getEquipmentnumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.math.BigDecimal value3() {
        return getLongitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.math.BigDecimal value4() {
        return getLatitude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.sql.Timestamp value5() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerlocationRecord value1(java.lang.Integer value) {
        setLocationid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerlocationRecord value2(java.lang.String value) {
        setEquipmentnumber(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerlocationRecord value3(java.math.BigDecimal value) {
        setLongitude(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerlocationRecord value4(java.math.BigDecimal value) {
        setLatitude(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerlocationRecord value5(java.sql.Timestamp value) {
        setDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerlocationRecord values(java.lang.Integer value1, java.lang.String value2, java.math.BigDecimal value3, java.math.BigDecimal value4, java.sql.Timestamp value5) {
        return this;
    }
}
