package nl.dennisvdwielen.pojo;

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.Table;

/**
 * Created by Dennis on 26-5-2014 at 20:26)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.pojo
 */
@SuppressWarnings("unused")
@Table(tableName = "containershippingnames", alias = "conshipping")
public class ContainerShippingnames {

    @ForeignKey(tableName = "Container", fieldName = "equipmentnumber")
    private Container equipmentnumber;

    @ForeignKey(tableName = "Shippingname", fieldName = "shippingID")
    private Shippingname shippingID;
}
