package nl.dennisvdwielen.enums;

/**
 * Created by Dennis on 20-5-2014 at 11:14)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.enums
 */

/**
 * This enum class is used for organizing the order  options within the backend. The create clear constant and value pairs
 */
public enum Orders {
    ASCENDING("ASC"), DESCENDING("DESC");

    private String shortValue;

    private Orders(String shortValue) {
        this.shortValue = shortValue;
    }

    public String getValue() {
        return shortValue;
    }

}
