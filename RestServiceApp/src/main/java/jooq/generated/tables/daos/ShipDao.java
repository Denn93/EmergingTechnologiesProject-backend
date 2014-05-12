/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables.daos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value = {"http://www.jooq.org", "3.3.2"},
        comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ShipDao extends org.jooq.impl.DAOImpl<jooq.generated.tables.records.ShipRecord, jooq.generated.tables.pojos.Ship, java.lang.Integer> {

    /**
     * Create a new ShipDao without any configuration
     */
    public ShipDao() {
        super(jooq.generated.tables.Ship.SHIP, jooq.generated.tables.pojos.Ship.class);
    }

    /**
     * Create a new ShipDao with an attached configuration
     */
    public ShipDao(org.jooq.Configuration configuration) {
        super(jooq.generated.tables.Ship.SHIP, jooq.generated.tables.pojos.Ship.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected java.lang.Integer getId(jooq.generated.tables.pojos.Ship object) {
        return object.getShipid();
    }

    /**
     * Fetch records that have <code>shipID IN (values)</code>
     */
    public java.util.List<jooq.generated.tables.pojos.Ship> fetchByShipid(java.lang.Integer... values) {
        return fetch(jooq.generated.tables.Ship.SHIP.SHIPID, values);
    }

    /**
     * Fetch a unique record that has <code>shipID = value</code>
     */
    public jooq.generated.tables.pojos.Ship fetchOneByShipid(java.lang.Integer value) {
        return fetchOne(jooq.generated.tables.Ship.SHIP.SHIPID, value);
    }

    /**
     * Fetch records that have <code>shipName IN (values)</code>
     */
    public java.util.List<jooq.generated.tables.pojos.Ship> fetchByShipname(java.lang.String... values) {
        return fetch(jooq.generated.tables.Ship.SHIP.SHIPNAME, values);
    }
}
