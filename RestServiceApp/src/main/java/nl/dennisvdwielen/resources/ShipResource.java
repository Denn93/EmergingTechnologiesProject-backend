package nl.dennisvdwielen.resources;

import jooq.generated.tables.daos.ShipDao;
import jooq.generated.tables.pojos.Ship;
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
@Path("/ship")
public class ShipResource {

    @GET
    @Produces("Application/json")
    @Path("/get")
    public List<Ship> fetch() {
        return new ShipDao(ConfigFactory.getInstance().getConfig()).findAll();
    }

    @GET
    @Produces("Application/json")
    @Path("/get/{id}")
    public List<Ship> fetchById(@PathParam("id") Integer id) {
        return new ShipDao(ConfigFactory.getInstance().getConfig()).fetchByShipid(id);
    }
}
