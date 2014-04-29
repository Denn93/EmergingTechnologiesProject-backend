package nl.dennisvdwielen.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.dennisvdwielen.dao.ClassRoom;
import nl.dennisvdwielen.dto.Student;
import org.apache.log4j.Logger;

import java.util.ArrayList;

@Path("/hello")
public class HelloResources {

    private static final Logger log = Logger.getLogger(HelloResources.class);

    @GET
    @Produces("Application/json")
    @Path("/get")
    public ArrayList<Student> sayHello() {

        ClassRoom room = new ClassRoom();


        return room.getStudents();
    }
}
