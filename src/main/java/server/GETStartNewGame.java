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
    //private static final Logger LOGGER = LogManager.getLogger(GETStartNewGame.class);
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("id") final Integer id) throws Exception{
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final GameDAO gameDAO = new GameDAO(entityManager);
        final PlayerDAO playerDAO = new PlayerDAO(entityManager);
        final MapDAO mapDAO = new MapDAO(entityManager);
        Player player = new Player();
        Integer game_id = null;
        Game game = new Game();
        Map map = new Map();
        try {
            if (id == 1) {
                game = gameDAO.createGame(game);
                map.setGame_id(game.getGame_id());
                player.setGame_id(game.getGame_id());
                player = playerDAO.createPlayer(player);
                map.setPlayer_id(player.getPlayer_id());
                map = mapDAO.createMap(map);
                player.setMap_id(map.getMap_id());
                player = playerDAO.createPlayer(player);
                //game_id = game.getGame_id();
                return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML_TYPE).entity(player).build();
            } else if (id == 2) {
                game_id = gameDAO.findOpenGame();
                map.setGame_id(game_id);
                map = mapDAO.createMap(map);
                game = gameDAO.findGameByID(game_id);
                player.setGame_id(game.getGame_id());
                player.setMap_id(map.getMap_id());
                player = playerDAO.createPlayer(player);
                map.setPlayer_id(player.getPlayer_id());
                map = mapDAO.createMap(map);
                return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML_TYPE).entity(player).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        }catch(Exception e) {
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
