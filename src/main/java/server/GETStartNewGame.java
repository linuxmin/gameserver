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
        Integer game_id = null;
        Game game = new Game();
        try {
            if(id == 1) {
                game = gameDAO.createGame(game);
                //game_id = game.getGame_id();
                return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML_TYPE).entity(game).build();
            }
            else{
                game_id=gameDAO.findOpenGame();
                game.setGame_id(game_id);
                return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML_TYPE).entity(game).build();
            }
        }
        finally{
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
