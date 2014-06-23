package nl.dennisvdwielen.dto;

import nl.dennisvdwielen.entity.ContainerLocation;

/**
 * Created by Dennis on 9-6-2014 at 12:04)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dto
 */

/**
 * This DTO class is used to transfer entities between client and server. This is done because entity objects are only locally used
 */
@SuppressWarnings("unused")
public class LocationDTO {

    ContainerLocation locationID;

    public ContainerLocation getLocationID() {
        return locationID;
    }

    public void setLocationID(ContainerLocation locationID) {
        this.locationID = locationID;
    }
}
