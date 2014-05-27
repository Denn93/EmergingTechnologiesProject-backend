package nl.dennisvdwielen.pojo;

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

import java.sql.Timestamp;

/**
 * Created by Dennis on 26-5-2014 at 20:46)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.pojo
 */
@SuppressWarnings("unused")
@Table(tableName = "ContainerLocation", alias = "location")
public class ContainerLocation {

    @PrimaryKey(fieldName = "locationID")
    private int locationID;

    @ForeignKey(tableName = "Container", fieldName = "equipmentNumber")
    private Container equipmentNumber;

    private Double longitude;
    private Double latitude;
    private Timestamp date;
}
