package nl.dennisvdwielen.entity;

import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 26-5-2014 at 20:11)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.entity
 */

/**
 * This class is a custom entity which is used for reflection purposes and overall use with the database
 */
@SuppressWarnings("unused")
@Table(tableName = "packagekind", alias = "kind")
public class Packagekind {

    @PrimaryKey(fieldName = "kindid")
    private int kindID;

    private String kindName;

    public int getKindID() {
        return kindID;
    }

    public void setKindID(int kindID) {
        this.kindID = kindID;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }
}
