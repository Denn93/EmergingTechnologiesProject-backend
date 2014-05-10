package nl.dennisvdwielen.resources;

import jooq.generated.tables.daos.ContainerDao;
import jooq.generated.tables.pojos.Container;
import nl.dennisvdwielen.factory.ConfigFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Dennis on 10-5-2014 at 00:12)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.resources
 */

@Path("/container")
public class ContainerResource {

    @GET
    @Produces("Application/json")
    @Path("/get")
    public List<Container> fetch() {
        return new ContainerDao(ConfigFactory.getInstance().getConfig()).findAll();
    }

    @GET
    @Produces("Application/json")
    @Path("/get/{id}")
    public List<Container> fetchById(@PathParam("id") String id) {
        return new ContainerDao(ConfigFactory.getInstance().getConfig()).fetchByEquipmentnumber(id);
    }
}
