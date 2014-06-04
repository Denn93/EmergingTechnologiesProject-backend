package nl.dennisvdwielen.dto;

import nl.dennisvdwielen.pojo.Container;
import nl.dennisvdwielen.pojo.ContainerLocation;
import nl.dennisvdwielen.pojo.Packagekind;
import nl.dennisvdwielen.pojo.Shippingname;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Dennis on 31-5-2014 at 02:06)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dto
 */
@SuppressWarnings("unused")
public class ContainerDTO {

    Container equipmentNumber;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    Packagekind kindID;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    ContainerLocation locationID;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    Shippingname shippingID;

    public Container getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Container equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Packagekind getKindID() {
        return kindID;
    }

    public void setKindID(Packagekind kindID) {
        this.kindID = kindID;
    }

    public ContainerLocation getLocationID() {
        return locationID;
    }

    public void setLocationID(ContainerLocation locationID) {
        this.locationID = locationID;
    }

    public Shippingname getShippingID() {
        return shippingID;
    }

    public void setShippingID(Shippingname shippingID) {
        this.shippingID = shippingID;
    }
}
