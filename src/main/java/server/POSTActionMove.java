package server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//@Path("/action")
public class POSTActionMove {
 /*   @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response consumeXML(ActionMove actionMove ) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final ActionMoveDAO actionMoveDAO = new ActionMoveDAO(entityManager);
        try {
            playerDAO.insertPlayerData(player);
            gameDAO.iterateGamePlayerNo(player.getGame_id());
            return Response.status(200).type(MediaType.APPLICATION_XML).entity(player).build();
        }catch(java.lang.NullPointerException e){
            e.printStackTrace();
            Error error = new Error();
            error.setMessage("Missing attributes for Player");
            return Response.status(Response.Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML).entity(error).build();
        }catch(java.lang.Exception e){
            e.printStackTrace();
            Error error = new Error();
            error.setMessage(e.toString());
            return Response.status(Response.Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML).entity(error).build();
        }

        finally{
            entityManager.close();
            entityManagerFactory.close();
        }
    }*/
}
