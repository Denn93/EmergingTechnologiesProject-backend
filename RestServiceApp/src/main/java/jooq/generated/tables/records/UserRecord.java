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
public class UserRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.generated.tables.records.UserRecord> implements org.jooq.Record3<java.lang.Integer, java.lang.String, java.lang.String> {

    private static final long serialVersionUID = 1554086630;

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(jooq.generated.tables.User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(java.lang.Integer id, java.lang.String name, java.lang.String country) {
        super(jooq.generated.tables.User.USER);

        setValue(0, id);
        setValue(1, name);
        setValue(2, country);
    }

    /**
     * Getter for <code>backend.user.id</code>.
     */
    public java.lang.Integer getId() {
        return (java.lang.Integer) getValue(0);
    }

    /**
     * Setter for <code>backend.user.id</code>.
     */
    public void setId(java.lang.Integer value) {
        setValue(0, value);
    }

    /**
     * Getter for <code>backend.user.name</code>.
     */
    public java.lang.String getName() {
        return (java.lang.String) getValue(1);
    }

    /**
     * Setter for <code>backend.user.name</code>.
     */
    public void setName(java.lang.String value) {
        setValue(1, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>backend.user.country</code>.
     */
    public java.lang.String getCountry() {
        return (java.lang.String) getValue(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>backend.user.country</code>.
     */
    public void setCountry(java.lang.String value) {
        setValue(2, value);
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
    public org.jooq.Row3<java.lang.Integer, java.lang.String, java.lang.String> fieldsRow() {
        return (org.jooq.Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Row3<java.lang.Integer, java.lang.String, java.lang.String> valuesRow() {
        return (org.jooq.Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Field<java.lang.Integer> field1() {
        return jooq.generated.tables.User.USER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Field<java.lang.String> field2() {
        return jooq.generated.tables.User.USER.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Field<java.lang.String> field3() {
        return jooq.generated.tables.User.USER.COUNTRY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.lang.String value3() {
        return getCountry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value1(java.lang.Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value2(java.lang.String value) {
        setName(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value3(java.lang.String value) {
        setCountry(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.String value3) {
        return this;
    }
}
