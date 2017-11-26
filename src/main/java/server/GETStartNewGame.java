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
        try {
                PerformJPAActions.startTransaction(entityManager);
                //Player player = new Player(1234,"lkdfj","dsfsdf",23,"sdfj");
                Player player = playerDAO.findPlayerByID(id);
                Game game = new Game(player);
                entityManager.detach(player);
                gameDAO.createGame(game);
                PerformJPAActions.commitTransaction(entityManager);
                return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML).entity(game).build();


            }
          //  PerformJPAActions.startTransaction(entityManager);
          //  Game game = gameDAO.findOpenGame(id);
         //   PerformJPAActions.commitTransaction(entityManager);
        finally{
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
