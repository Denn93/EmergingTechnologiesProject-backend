/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value = {"http://www.jooq.org", "3.3.2"},
        comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Container extends org.jooq.impl.TableImpl<jooq.generated.tables.records.ContainerRecord> {

    /**
     * The singleton instance of <code>emerging.container</code>
     */
    public static final jooq.generated.tables.Container CONTAINER = new jooq.generated.tables.Container();
    private static final long serialVersionUID = 1523928774;
    /**
     * The column <code>emerging.container.equipmentNumber</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.String> EQUIPMENTNUMBER = createField("equipmentNumber", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");
    /**
     * The column <code>emerging.container.shipID</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.Integer> SHIPID = createField("shipID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>emerging.container.handlingID</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.Integer> HANDLINGID = createField("handlingID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>emerging.container.packagingID</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.Integer> PACKAGINGID = createField("packagingID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>emerging.container.consignmentNumber</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.Long> CONSIGNMENTNUMBER = createField("consignmentNumber", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");
    /**
     * The column <code>emerging.container.UNO</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.Integer> UNO = createField("UNO", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>emerging.container.INO</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.math.BigDecimal> INO = createField("INO", org.jooq.impl.SQLDataType.DECIMAL.precision(2, 1).nullable(false), this, "");
    /**
     * The column <code>emerging.container.flashpoint</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.math.BigDecimal> FLASHPOINT = createField("flashpoint", org.jooq.impl.SQLDataType.DECIMAL.precision(4, 1).nullable(false), this, "");
    /**
     * The column <code>emerging.container.stowagePosition</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.Integer> STOWAGEPOSITION = createField("stowagePosition", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>emerging.container.quantityInContainer</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.Integer> QUANTITYINCONTAINER = createField("quantityInContainer", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>emerging.container.weight</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.Integer> WEIGHT = createField("weight", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>emerging.container.portOfDischarge</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.String> PORTOFDISCHARGE = createField("portOfDischarge", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");
    /**
     * The column <code>emerging.container.terminal</code>.
     */
    public final org.jooq.TableField<jooq.generated.tables.records.ContainerRecord, java.lang.String> TERMINAL = createField("terminal", org.jooq.impl.SQLDataType.VARCHAR.length(45).nullable(false), this, "");

    /**
     * Create a <code>emerging.container</code> table reference
     */
    public Container() {
        this("container", null);
    }

    /**
     * Create an aliased <code>emerging.container</code> table reference
     */
    public Container(java.lang.String alias) {
        this(alias, jooq.generated.tables.Container.CONTAINER);
    }

    private Container(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.ContainerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Container(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.ContainerRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, jooq.generated.Emerging.EMERGING, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public java.lang.Class<jooq.generated.tables.records.ContainerRecord> getRecordType() {
        return jooq.generated.tables.records.ContainerRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.UniqueKey<jooq.generated.tables.records.ContainerRecord> getPrimaryKey() {
        return jooq.generated.Keys.KEY_CONTAINER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.List<org.jooq.UniqueKey<jooq.generated.tables.records.ContainerRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<jooq.generated.tables.records.ContainerRecord>>asList(jooq.generated.Keys.KEY_CONTAINER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.List<org.jooq.ForeignKey<jooq.generated.tables.records.ContainerRecord, ?>> getReferences() {
        return java.util.Arrays.<org.jooq.ForeignKey<jooq.generated.tables.records.ContainerRecord, ?>>asList(jooq.generated.Keys.FK_CONTAINER_SCHIPID, jooq.generated.Keys.FK_CONTAINER_HANDLINGID, jooq.generated.Keys.FK_CONTAINER_PACKAGINGID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public jooq.generated.tables.Container as(java.lang.String alias) {
        return new jooq.generated.tables.Container(alias, this);
    }

    /**
     * Rename this table
     */
    public jooq.generated.tables.Container rename(java.lang.String name) {
        return new jooq.generated.tables.Container(name, null);
    }
}
