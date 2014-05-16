package nl.dennisvdwielen.rest;

import nl.dennisvdwielen.factory.DaoFactory;
import nl.dennisvdwielen.interfaces.ADao;
import nl.dennisvdwielen.pojo.Container;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/container")
public class HelloResources {

   // private static final Logger log = Logger.getLogger(HelloResources.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public String getContainer(@QueryParam(value = "equipmentNumber") String equipmentNumber,
                               @QueryParam(value = "shipID") Integer shipID,
                               @QueryParam(value = "handlingID") Integer handlingID,
                               @QueryParam(value = "packagingID") Integer packagingID,
                               @QueryParam(value = "consignmentNumber") Integer consignmentNumber,
                               @QueryParam(value = "imo") Double imo,
                               @QueryParam(value = "uno") Double uno,
                               @QueryParam(value = "flashpoint") Double flashpoint,
                               @QueryParam(value = "stowagePosition") Integer stowagePosition,
                               @QueryParam(value = "quantity") Integer quantity,
                               @QueryParam(value = "weight") Integer weight,
                               @QueryParam(value = "portOfDischarge") String portOfDischarge,
                               @QueryParam(value = "terminal") String terminal) {

        // IDao dao = new DaoFactory().getDAO(Container.class);

        equipmentNumber = (equipmentNumber == null) ? "" : equipmentNumber;
        // dao.checkInput(equipmentNumber, equipmentNumber);
        shipID = (shipID == null) ? 0 : shipID;
        handlingID = (handlingID == null) ? 0 : handlingID;
        packagingID = (packagingID == null) ? 0 : packagingID;
        consignmentNumber = (consignmentNumber == null) ? 0 : consignmentNumber;
        imo = (uno == null) ? 0 : uno;
        uno = (uno == null) ? 0 : uno;
        flashpoint = (flashpoint == null) ? 0 : flashpoint;
        stowagePosition = (stowagePosition == null) ? 0 : stowagePosition;
        quantity = (quantity == null) ? 0 : quantity;
        weight = (weight == null) ? 0 : weight;
        portOfDischarge = (portOfDischarge == null) ? "" : portOfDischarge;
        terminal = (terminal == null) ? "" : terminal;

        return equipmentNumber + " - " + uno.toString() + " - " + imo.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getfilter")
    public ArrayList<Container> getContainerByFiler(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

        String result = "";

        HashMap<String, String> filter = new HashMap<String, String>();
        for (Map.Entry<String, List<String>> entry : queryParameters.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();

            filter.put(key, value.get(0));
            result += key + " - " + value.toString() + "\n";
        }

        ADao dao = new DaoFactory().getDAO(Container.class);

        return dao.get(-1, ADao.Option.Filter, filter);
    }
}
