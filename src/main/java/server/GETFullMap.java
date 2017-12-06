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

import java.util.List;

@Path("/map")

public class GETFullMap {
    @GET
    @Path("/{map_id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("map_id") final Integer map_id) throws Exception{
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final MapDAO mapDAO = new MapDAO(entityManager);
        final TileDAO tileDAO = new TileDAO(entityManager);
        TileList tileList = new TileList();
        Map map = new Map();
        try {
            map = mapDAO.findOtherMapByGame_id(map_id);
            List<Tile> tileUtilList = map.getTiles();
            tileList.setTiles(tileUtilList);
            if(tileUtilList.size() < 32){  //if mapsize is not correct -> error
                Error error = new Error();
                error.setMessage("Mapgeneration failed");
                return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();

            }
        }catch(NullPointerException e){  // map not ready or not found
            e.printStackTrace();
            return Response.status(Response.Status.NO_CONTENT).build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.NO_CONTENT).build();

        }
        return Response.status(200).type(MediaType.APPLICATION_XML).entity(tileList).build();
    }
}
