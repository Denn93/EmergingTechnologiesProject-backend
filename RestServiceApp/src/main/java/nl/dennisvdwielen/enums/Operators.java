package nl.dennisvdwielen.enums;

/**
 * Created by Dennis on 20-5-2014 at 00:11)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.enums
 */
public enum Operators {
    GreaterThan("gt", ">"), LessThan("lt", "<"), Equals("equals", "=");

    private String shortValue;
    private String value;

    private Operators(String shortValue, String value) {
        this.shortValue = shortValue;
        this.value = value;
    }

    public String getShort() {
        return shortValue;
    }

    public String getOperator() {
        return value;
    }
}
