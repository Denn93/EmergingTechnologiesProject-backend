package nl.dennisvdwielen.entity;

import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 26-5-2014 at 20:15)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.entity
 */

/**
 * This class is a custom entity which is used for reflection purposes and overall use with the database
 */
@SuppressWarnings("unused")
@Table(tableName = "shippingname", alias = "shname")
public class Shippingname {

    @PrimaryKey(fieldName = "shippingid")
    private int shippingID;

    private String shippingName;

    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shipppingID) {
        this.shippingID = shipppingID;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }
}
