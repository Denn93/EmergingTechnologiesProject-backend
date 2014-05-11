package nl.dennisvdwielen.resources;

import jooq.generated.tables.daos.ContainerlocationDao;
import jooq.generated.tables.pojos.Containerlocation;
import nl.dennisvdwielen.factory.ConfigFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public List<Containerlocation> fetch() {
        return new ContainerlocationDao(ConfigFactory.getInstance().getConfig()).findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public List<Containerlocation> fetchById(@PathParam("id") String id) {
        return new ContainerlocationDao(ConfigFactory.getInstance().getConfig()).fetchByEquipmentnumber(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response insert(Containerlocation container) {

//        String time = new Timestamp(System.currentTimeMillis()).toString();
//
//        container.setDate(new Timestamp(System.currentTimeMillis()));
        new ContainerlocationDao(ConfigFactory.getInstance().getConfig()).insert(container);

        return Response.status(201).entity(container).build();
    }
}
