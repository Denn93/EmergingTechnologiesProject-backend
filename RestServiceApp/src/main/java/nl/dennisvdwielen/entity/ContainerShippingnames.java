package nl.dennisvdwielen.entity;

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 26-5-2014 at 20:26)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.entity
 */
@SuppressWarnings("unused")
@Table(tableName = "containershippingnames", alias = "conshipping")
public class ContainerShippingnames {

    @ForeignKey(tableName = "container", fieldName = "equipmentnumber")
    private Container equipmentNumber;

    @ForeignKey(tableName = "shippingname", fieldName = "shippingid")
    private Shippingname shippingID;

    public Container getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Container equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Shippingname getShippingID() {
        return shippingID;
    }

    public void setShippingID(Shippingname shippingID) {
        this.shippingID = shippingID;
    }
}
