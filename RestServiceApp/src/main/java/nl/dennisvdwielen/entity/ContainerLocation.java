package nl.dennisvdwielen.entity;

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

import java.sql.Timestamp;

/**
 * Created by Dennis on 26-5-2014 at 20:46)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.entity
 */

/**
 * This class is a custom entity which is used for reflection purposes and overall use with the database
 */
@SuppressWarnings("unused")
@Table(tableName = "containerlocation", alias = "location")
public class ContainerLocation {

    @PrimaryKey(fieldName = "locationid")
    private int locationID;

    @ForeignKey(tableName = "container", fieldName = "equipmentnumber")
    private Container equipmentNumber;

    private Double longitude;
    private Double latitude;
    private Timestamp date;

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public Container getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Container equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
