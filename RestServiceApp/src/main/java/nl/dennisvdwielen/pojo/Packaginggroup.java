package nl.dennisvdwielen.pojo;

import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 14-5-2014 at 21:43)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.pojo
 */
@SuppressWarnings("unused")
@Table(tableName = "Packaginggroup", alias = "pg")
public class Packaginggroup {

    @PrimaryKey(fieldName = "packagingID")
    private Integer packagingID;

    private String packagingName;

    public Integer getPackagingID() {
        return packagingID;
    }

    public void setPackagingID(Integer packagingID) {
        this.packagingID = packagingID;
    }

    public String getPackagingName() {
        return packagingName;
    }

    public void setPackagingName(String packagingName) {
        this.packagingName = packagingName;
    }
}
