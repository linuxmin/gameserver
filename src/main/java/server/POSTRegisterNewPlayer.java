package server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/registernewplayer")
public class POSTRegisterNewPlayer {
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response consumeXML(Player player ) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final PlayerDAO playerDAO = new PlayerDAO(entityManager);
        final GameDAO gameDAO = new GameDAO(entityManager);
        try {
            playerDAO.insertPlayerData(player);
            gameDAO.iterateGamePlayerNo(player.getGame_id());
           return Response.status(200).type(MediaType.APPLICATION_XML).entity(player).build();
        }finally{
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}

