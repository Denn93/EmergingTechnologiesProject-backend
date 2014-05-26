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
@Table(tableName = "containerkinds", alias = "contkinds")
public class ContainerKinds {

    @ForeignKey(tableName = "container", fieldName = "equipmentnumber")
    private Container equipmentnumber;

    @ForeignKey(tableName = "packagekind", fieldName = "kindID")
    private Packagekind kindID;

}
