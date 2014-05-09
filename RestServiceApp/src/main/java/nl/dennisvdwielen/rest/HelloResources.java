package nl.dennisvdwielen.rest;

import jooq.generated.tables.daos.ContainerDao;
import jooq.generated.tables.pojos.Container;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/hello")
public class HelloResources {

    //private static final Logger log = Logger.getLogger(HelloResources.class);

    @GET
    @Produces("Application/json")
    @Path("/get")
    public List<Container> sayHello() {

        Connection connect = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connect = DriverManager.getConnection("jdbc:mysql://localhost/emerging", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        Configuration c = DSL.using(connect, SQLDialect.MYSQL).configuration();

        ContainerDao dao = new ContainerDao();
        dao.setConfiguration(c);
        return dao.findAll();
    }
}
