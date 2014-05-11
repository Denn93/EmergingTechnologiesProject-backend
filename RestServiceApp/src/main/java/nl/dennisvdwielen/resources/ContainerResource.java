package nl.dennisvdwielen.resources;

import jooq.generated.tables.daos.ContainerDao;
import jooq.generated.tables.pojos.Container;
import nl.dennisvdwielen.factory.ConfigFactory;

import javax.ws.rs.*;
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
    public Container fetchById(@PathParam("id") String id) {
        return new ContainerDao(ConfigFactory.getInstance().getConfig()).fetchByEquipmentnumber(id).get(0);
    }

    @PUT
    @Consumes("Application/json")
    @Path("/handling")
    public Container updateHandling(Container obj) {
        new ContainerDao(ConfigFactory.getInstance().getConfig()).update(obj);
        return new ContainerDao(ConfigFactory.getInstance().getConfig()).fetchByEquipmentnumber(obj.getEquipmentnumber()).get(0);

        //TODO change longtitude to longitude in database and generate files
    }
}
