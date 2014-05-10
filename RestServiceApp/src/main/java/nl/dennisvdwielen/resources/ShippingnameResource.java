package nl.dennisvdwielen.resources;

import jooq.generated.tables.daos.ShippingnameDao;
import jooq.generated.tables.pojos.Shippingname;
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
@Path("/shippingname")
public class ShippingnameResource {

    @GET
    @Produces("Application/json")
    @Path("/get")
    public List<Shippingname> fetch() {
        return new ShippingnameDao(ConfigFactory.getInstance().getConfig()).findAll();
    }

    @GET
    @Produces("Application/json")
    @Path("/get/{id}")
    public List<Shippingname> fetchById(@PathParam("id") Integer id) {
        return new ShippingnameDao(ConfigFactory.getInstance().getConfig()).fetchByShippingid(id);
    }
}
