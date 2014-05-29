package nl.dennisvdwielen.rest;

import nl.dennisvdwielen.factory.DaoFactory;
import nl.dennisvdwielen.interfaces.ADao;
import nl.dennisvdwielen.pojo.ContainerKinds;

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

@Path("/container")
public class ContainerResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public ArrayList<ContainerKinds> getContainerByFiler(@Context UriInfo uriInfo) {
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

        ADao dao = new DaoFactory().getDAO(ContainerKinds.class);

        if (!where.isEmpty() && !order.isEmpty())
            return dao.get(-1, where, order);

        if (!where.isEmpty())
            return dao.get(-1, where);

        if (!order.isEmpty())
            return dao.get(-1, order);

        return dao.get();
    }
}
