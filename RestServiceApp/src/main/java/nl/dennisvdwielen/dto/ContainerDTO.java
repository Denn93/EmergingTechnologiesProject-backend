package nl.dennisvdwielen.dto;

import nl.dennisvdwielen.pojo.Container;
import nl.dennisvdwielen.pojo.ContainerLocation;
import nl.dennisvdwielen.pojo.ContainerShippingnames;
import nl.dennisvdwielen.pojo.Packagekind;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Dennis on 31-5-2014 at 02:06)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dto
 */
public class ContainerDTO {

    Container container;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    Packagekind kinds;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    ContainerLocation location;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    ContainerShippingnames shippingnames;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Packagekind getKinds() {
        return kinds;
    }

    public void setKinds(Packagekind kinds) {
        this.kinds = kinds;
    }

    public ContainerLocation getLocation() {
        return location;
    }

    public void setLocation(ContainerLocation location) {
        this.location = location;
    }

    public ContainerShippingnames getShippingnames() {
        return shippingnames;
    }

    public void setShippingnames(ContainerShippingnames shippingnames) {
        this.shippingnames = shippingnames;
    }
}
