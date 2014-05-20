package nl.dennisvdwielen.pojo;

/**
 * Created by Dennis on 14-5-2014 at 21:42)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.pojo
 */
@SuppressWarnings("unused")
public class Handling {

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
