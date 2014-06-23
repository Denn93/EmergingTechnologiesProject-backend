package nl.dennisvdwielen.entity;

import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 14-5-2014 at 21:42)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.entity
 */

/**
 * This class is a custom entity which is used for reflection purposes and overall use with the database
 */
@SuppressWarnings("unused")
@Table(tableName = "handling", alias = "h")
public class Handling {

    @PrimaryKey(fieldName = "handlingid")
    private Integer handlingID;

    private String handlingName;

    public String getHandlingName() {
        return handlingName;
    }

    public void setHandlingName(String handlingName) {
        this.handlingName = handlingName;
    }

    public Integer getHandlingID() {
        return handlingID;
    }

    public void setHandlingID(Integer handlingID) {
        this.handlingID = handlingID;
    }
}
