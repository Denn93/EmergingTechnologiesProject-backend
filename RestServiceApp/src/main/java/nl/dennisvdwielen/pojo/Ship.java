package nl.dennisvdwielen.pojo;

import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 14-5-2014 at 21:41)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.pojo
 */
@SuppressWarnings("unused")
@Table(tableName = "Ship", alias = "s")
public class Ship {

    @PrimaryKey(fieldName = "shipID")
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
