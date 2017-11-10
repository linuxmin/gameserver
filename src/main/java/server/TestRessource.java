package server;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.runner.Result;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

/**
 * Created by laptop on 07.11.17.
 */
@Path("/test")
public class TestRessource {

    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        return sessionFactory;
    }
    @GET
    @Path("/{first_name}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("first_name") final String first_name) throws Exception{
        // final String output = "Hello " + id + "\n";
        // Player pl = new Player(0, "Benjamin", "Hartmann", 29, "ElMuldo");
        configureSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Player pl = session.createNamedQuery("get_player_by_first_name", Player.class)
                .setParameter("name",first_name)
                .getSingleResult();


            return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML).entity(pl).build();

    }
}
