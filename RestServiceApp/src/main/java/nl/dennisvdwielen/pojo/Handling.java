package nl.dennisvdwielen.pojo;

import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 14-5-2014 at 21:42)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.pojo
 */
@SuppressWarnings("unused")
@Table(tableName = "Handling", alias = "h")
public class Handling {

    @PrimaryKey(fieldName = "handlingID")
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
