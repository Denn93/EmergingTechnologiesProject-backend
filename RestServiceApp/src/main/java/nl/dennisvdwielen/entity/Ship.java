package nl.dennisvdwielen.entity;

import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 14-5-2014 at 21:41)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.entity
 */

/**
 * This class is a custom entity which is used for reflection purposes and overall use with the database
 */
@SuppressWarnings("unused")
@Table(tableName = "ship", alias = "s")
public class Ship {

    @PrimaryKey(fieldName = "shipid")
    private Integer shipID;

    private String shipName;

    public Integer getShipID() {
        return shipID;
    }

    public void setShipID(Integer shipID) {
        this.shipID = shipID;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}
