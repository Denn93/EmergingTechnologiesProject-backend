package nl.dennisvdwielen.resources;

import nl.dennisvdwielen.abstracts.ADao;
import nl.dennisvdwielen.dto.LocationDTO;
import nl.dennisvdwielen.entity.ContainerLocation;
import nl.dennisvdwielen.factory.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
 * Created by Dennis on 9-6-2014 at 11:57)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.resources
 */
@Path("/location")
public class LocationResource {

    ADao dao = new DaoFactory().getDAO(ContainerLocation.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public ArrayList<LocationDTO> getLocations(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

        List<String> order = new ArrayList<String>();

        for (Map.Entry<String, List<String>> entry : queryParameters.entrySet())
            if (entry.getKey().equalsIgnoreCase("order"))
                for (String value : entry.getValue())
                    order.add(value);

        if (!order.isEmpty())
            return dao.get(-1, order);

        return dao.get();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public LocationDTO getLocationByEquipmentID(@PathParam("id") String equipmentNumber, @Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

        LinkedHashMap<String, List<String>> where = new LinkedHashMap<String, List<String>>();
        List<String> whereValues = new ArrayList<String>();
        whereValues.add(equipmentNumber);
        where.put("equipmentNumber", whereValues);

        List<String> order = new ArrayList<String>();
        for (Map.Entry<String, List<String>> entry : queryParameters.entrySet())
            if (entry.getKey().equalsIgnoreCase("order"))
                for (String value : entry.getValue())
                    order.add(value);

        if (!order.isEmpty())
            return (LocationDTO) dao.get(-1, where, order).get(0);

        return (!dao.get(-1, where).isEmpty()) ? (LocationDTO) (dao.get(-1, where).get(0)) : null;
    }
}
