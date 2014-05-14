package nl.dennisvdwielen.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.dennisvdwielen.dto.Student;
import nl.dennisvdwielen.factory.DaoFactory;
import nl.dennisvdwielen.inferface.IDao;

import java.util.ArrayList;

@Path("/hello")
public class HelloResources {

   // private static final Logger log = Logger.getLogger(HelloResources.class);

    @GET
    @Produces("Application/json")
    @Path("/get")
    public ArrayList<Student> sayHello() {
        IDao dao = new DaoFactory().getDAO(Student.class);

        return dao.get(-1);
    }
}
