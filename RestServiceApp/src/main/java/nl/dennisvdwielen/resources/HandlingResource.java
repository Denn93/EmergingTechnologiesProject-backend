package nl.dennisvdwielen.resources;

import nl.dennisvdwielen.abstracts.ADao;
import nl.dennisvdwielen.entity.Handling;
import nl.dennisvdwielen.factory.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by Dennis on 9-6-2014 at 11:57)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.resources
 */

/**
 * This resource is the endpoint for location data
 * Set location endpoint to '/handling'
 */
@Path("/handling")
public class HandlingResource {

    ADao dao = new DaoFactory().getDAO(Handling.class);

    /**
     * GET Method. This method retrieves all handlings within the database. No optional parameters are permitted
     *
     * @return ArrayList of HandlingdTO classes
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public ArrayList getHandlings() {
        return dao.get();
    }
}
