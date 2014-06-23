package nl.dennisvdwielen.dto;

import nl.dennisvdwielen.entity.Handling;

/**
 * Created by Dennis on 6/18/2014.
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dto
 */

/**
 * This DTO class is used to transfer entities between client and server. This is done because entity objects are only locally used
 */
@SuppressWarnings("unused")
public class HandlingDTO {

    private Handling handlingID;

    public Handling getHandlingID() {
        return handlingID;
    }

    public void setHandlingID(Handling handlingID) {
        this.handlingID = handlingID;
    }
}
