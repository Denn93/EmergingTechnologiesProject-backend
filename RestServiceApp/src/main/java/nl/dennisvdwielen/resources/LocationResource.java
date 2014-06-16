package nl.dennisvdwielen.resources;

import nl.dennisvdwielen.abstracts.ADao;
import nl.dennisvdwielen.dto.LocationDTO;
import nl.dennisvdwielen.entity.Container;
import nl.dennisvdwielen.entity.ContainerLocation;
import nl.dennisvdwielen.factory.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.sql.Timestamp;
import java.util.*;

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

        ArrayList<Object> result = dao.get(-1, where);

        if (result.isEmpty())
            return null;

        return (LocationDTO) result.get(0);

        //return (!dao.get(-1, where).isEmpty()) ? (LocationDTO) (dao.get(-1, where).get(0)) : null;
    }

    @GET
    @Path("/update/{id}")
    public Response updateLocation(@PathParam("id") String equipmentNumber) {
        LocationDTO dto = new LocationDTO();
        ContainerLocation loc = new ContainerLocation();
        loc.setDate(new Timestamp(new Date().getTime()));
        loc.setLatitude(45.3);
        loc.setLongitude(51.9);

        Container con = new Container();
        con.setEquipmentNumber(equipmentNumber);
        loc.setEquipmentNumber(con);
        dto.setLocationID(loc);

        if (dao.update(dto))
            return Response.status(Response.Status.OK).build();

        return Response.status(Response.Status.CONFLICT).build();
    }
}
