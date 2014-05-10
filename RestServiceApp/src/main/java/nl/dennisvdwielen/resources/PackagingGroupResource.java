package nl.dennisvdwielen.resources;

import jooq.generated.tables.daos.PackaginggroupDao;
import jooq.generated.tables.pojos.Packaginggroup;
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
@Path("/packaginggroup")
public class PackagingGroupResource {

    @GET
    @Produces("Application/json")
    @Path("/get")
    public List<Packaginggroup> fetch() {
        return new PackaginggroupDao(ConfigFactory.getInstance().getConfig()).findAll();
    }

    @GET
    @Produces("Application/json")
    @Path("/get/{id}")
    public List<Packaginggroup> fetchById(@PathParam("id") Integer id) {
        return new PackaginggroupDao(ConfigFactory.getInstance().getConfig()).fetchByPackagingid(id);
    }
}
