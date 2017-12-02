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
            if(player_no == 2) {
                Map map = new Map();
                map = mapDAO.findMapByID(player.getMap_id());
                //map.setGame_id(player.getGame_id());
                //map.setPlayer_id(player.getPlayer_id());
                /*
                if (mapDAO.findOpenMapByPlayerID(player.getPlayer_id()) == 0) {
                    mapDAO.createMap(map);*/
                    return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML_TYPE).entity(map).build();
                /*}else{
                    map.setMap_id(mapDAO.findOpenMapByPlayerID(player.getPlayer_id()));
                    return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_XML_TYPE).entity(map).build();
                }*/
            }
            else
            return Response.status(Response.Status.NO_CONTENT).type(MediaType.APPLICATION_XML_TYPE).entity(player).build();

        }
        finally{
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}