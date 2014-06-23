package nl.dennisvdwielen.resources;

import nl.dennisvdwielen.abstracts.ADao;
import nl.dennisvdwielen.dto.ContainerDTO;
import nl.dennisvdwielen.entity.Container;
import nl.dennisvdwielen.factory.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Dennis on 14-5-2014 at 15:22
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.factory
 */

/**
 * This resource is the endpoint for location data
 * Set location endpoint to '/container'
 */
@Path("/container")
public class ContainerResource {

    /**
     * GET Method. Used for retrieving container data based on filter options.
     * This methods uses the options that are given and filters the correct data to the different dao get methods
     *
     * @param uriInfo These are the filter inputs from a client
     * @return A ArrayLst of ContainerDTO classes in json format
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public ArrayList<ContainerDTO> getContainerByFiler(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

        LinkedHashMap<String, List<String>> where = new LinkedHashMap<String, List<String>>();
        List<String> order = new ArrayList<String>();

        for (Map.Entry<String, List<String>> entry : queryParameters.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("order"))
                for (String value : entry.getValue())
                    order.add(value);
            else
                where.put(entry.getKey(), entry.getValue());
        }

        ADao dao = new DaoFactory().getDAO(Container.class);

        if (!where.isEmpty() && !order.isEmpty())
            return dao.get(-1, where, order);

        if (!where.isEmpty())
            return dao.get(-1, where);

        if (!order.isEmpty())
            return dao.get(-1, order);

        return dao.get();
    }
}
