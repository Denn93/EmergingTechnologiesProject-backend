package nl.dennisvdwielen.rest;

import jooq.generated.tables.daos.ContainerDao;
import jooq.generated.tables.pojos.Container;
import nl.dennisvdwielen.factory.ConfigFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/hello")
public class HelloResources {

    @GET
    @Produces("Application/json")
    @Path("/get")
    public List<Container> sayHello() {
        return new ContainerDao(ConfigFactory.getInstance().getConfig()).findAll();
    }
}
