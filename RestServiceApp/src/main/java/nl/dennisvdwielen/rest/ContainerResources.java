package nl.dennisvdwielen.rest;

import nl.dennisvdwielen.factory.DaoFactory;
import nl.dennisvdwielen.interfaces.ADao;
import nl.dennisvdwielen.pojo.Container;

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
    @Path("/getfilter")
    public ArrayList<Container> getContainerByFiler(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

        String result = "";

        LinkedHashMap<String, List<String>> filter = new LinkedHashMap<String, List<String>>();
        for (Map.Entry<String, List<String>> entry : queryParameters.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();

            filter.put(key, value);
            //result += key + " - " + value.toString() + "\n";
        }
        //return result;

        ADao dao = new DaoFactory().getDAO(Container.class);

        if (filter.isEmpty())
            return dao.get(-1, ADao.Option.None, filter);

        return dao.get(-1, ADao.Option.Filter, filter);
    }
}
