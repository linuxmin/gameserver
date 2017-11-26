package server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/daotest")
public class POSTDaoTest {
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response consumeXML(Player player ) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final PlayerDAO playerDAO = new PlayerDAO(entityManager);

        try {
            PerformJPAActions.startTransaction(entityManager);
            playerDAO.createPlayer(player);
            PerformJPAActions.commitTransaction(entityManager);
        }finally{
            entityManagerFactory.close();
        }

        return Response.status(200).type(MediaType.APPLICATION_XML).entity(player).build();
    }
}

