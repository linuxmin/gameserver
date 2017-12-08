package server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/startgame")
public class GETStartNewGame {
    private static final Logger LOGGER = LogManager.getLogger(Game.class);
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("id") final Integer id) throws Exception{
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final GameDAO gameDAO = new GameDAO(entityManager);
        final PlayerDAO playerDAO = new PlayerDAO(entityManager);
        final MapDAO mapDAO = new MapDAO(entityManager);

        Integer game_id = null;
        Game game = new Game();

        try {
            if (id == 1) { // if id sent is 1 (Player1), then create a new game.
                game = gameDAO.createGame(game);
                Map map = new Map(game);
                map = mapDAO.createMap(map);
                Player player = new Player(map, game);
                player = playerDAO.createPlayer(player);
                map.setPlayer_id(player.getPlayer_id()); //associate the map with the player
                map = mapDAO.createMap(map);
                LOGGER.info(game);
                LOGGER.info(map);
                LOGGER.info(player);
                LOGGER.info(game.getGame_id());
                LOGGER.info(player.getGame_id());
                return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML_TYPE).entity(player).build();
            } else if (id == 2) { //if player is Player2 then add him to the last created open game.
                Game game2 = new Game(gameDAO.findOpenGame()); //find the last created open game
                Map map = new Map(game2);
                map = mapDAO.createMap(map);
                Player player = new Player(map, game2);
                player = playerDAO.createPlayer(player);
                map.setPlayer_id(player.getPlayer_id());
                map = mapDAO.createMap(map);
                return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML_TYPE).entity(player).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        }catch(Exception e) { //no or wrong id specified
            e.printStackTrace();
            Error error = new Error();
            error.setMessage("No ID specified or no open game found");
            return Response.status(Response.Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML_TYPE).entity(error).build();
        }
        finally{
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
