package server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.constraints.Null;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/readygame")
public class GETReadyGame {
    //private static final Logger LOGGER = LogManager.getLogger(GETStartNewGame.class);
    @GET
    @Path("/{player_id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("player_id") final Integer player_id) throws Exception{
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final GameDAO gameDAO = new GameDAO(entityManager);
        final PlayerDAO playerDAO = new PlayerDAO(entityManager);
        final MapDAO mapDAO = new MapDAO(entityManager);
        Player player = new Player();
        Game game = new Game();
        Integer player_no;
        try {
            player = playerDAO.findPlayerByID(player_id);
            game = gameDAO.findGameByID(player.getGame_id());
            System.out.println(game.getPlayers_no());
            player_no = game.getPlayers_no();
            if(player_no == 2) {   //if 2 players are in the game, then the game is ready
                return Response.status(Response.Status.OK).build();
            }
            else
            return Response.status(Response.Status.NO_CONTENT).build();

        }catch(NullPointerException e){  // if a wrong player_id was sent by the client
            Error error = new Error();
            error.setMessage("Player not registered in a Game");
            e.printStackTrace();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
        }
        finally{
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
