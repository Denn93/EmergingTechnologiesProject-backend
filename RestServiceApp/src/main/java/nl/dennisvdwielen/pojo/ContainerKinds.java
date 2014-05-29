package nl.dennisvdwielen.pojo;

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 26-5-2014 at 20:20)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.pojo
 */
@SuppressWarnings("unused")
@Table(tableName = "ContainerKinds", alias = "contkinds")
public class ContainerKinds {

    @ForeignKey(tableName = "Container", fieldName = "equipmentNumber")
    private Container equipmentNumber;

    @ForeignKey(tableName = "Packagekind", fieldName = "kindID")
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
