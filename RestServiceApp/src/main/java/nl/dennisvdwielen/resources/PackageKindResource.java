package nl.dennisvdwielen.resources;

import jooq.generated.tables.daos.PackagekindDao;
import jooq.generated.tables.pojos.Packagekind;
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
@Path("/packagekind")
public class PackageKindResource {

    @GET
    @Produces("Application/json")
    @Path("/get")
    public List<Packagekind> fetch() {
        return new PackagekindDao(ConfigFactory.getInstance().getConfig()).findAll();
    }

    @GET
    @Produces("Application/json")
    @Path("/get/{id}")
    public List<Packagekind> fetchById(@PathParam("id") Integer id) {
        return new PackagekindDao(ConfigFactory.getInstance().getConfig()).fetchByKindid(id);
    }
}
