package nl.dennisvdwielen.entity;

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 26-5-2014 at 20:20)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.entity
 */

/**
 * This class is a custom entity which is used for reflection purposes and overall use with the database
 */
@SuppressWarnings("unused")
@Table(tableName = "containerkinds", alias = "contkinds")
public class ContainerKinds {

    @ForeignKey(tableName = "container", fieldName = "equipmentnumber")
    private Container equipmentNumber;

    @ForeignKey(tableName = "packagekind", fieldName = "kindid")
    private Packagekind kindID;

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
}
