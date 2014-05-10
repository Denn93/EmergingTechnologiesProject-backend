package nl.dennisvdwielen.resources;

import jooq.generated.tables.daos.ContainerlocationDao;
import jooq.generated.tables.pojos.Containerlocation;
import nl.dennisvdwielen.factory.ConfigFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Dennis on 10-5-2014 at 01:07)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.resources
 */
@Path("/location")
public class ContainerLocationResource {

    @GET
    @Produces("Application/json")
    @Path("/get")
    public List<Containerlocation> fetch() {
        return new ContainerlocationDao(ConfigFactory.getInstance().getConfig()).findAll();
    }

    @GET
    @Produces("Application/json")
    @Path("/get/{id}")
    public List<Containerlocation> fetchById(@PathParam("id") String id) {
        return new ContainerlocationDao(ConfigFactory.getInstance().getConfig()).fetchByEquipmentnumber(id);
    }
}
