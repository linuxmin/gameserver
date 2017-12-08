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

import java.util.ArrayList;
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
        TileList tileList2 = new TileList();
        try {
            map = mapDAO.findOtherMapByGame_id(map_id);  //find the map from the other player of the game
            List<Tile> tileUtilList = map.getTiles();
            tileList.setTiles(tileUtilList);
            List<Tile> tileList1 = new ArrayList<>();
            /*
            Copy the tiles from the map of the other player to a new tilelist and hide the treasure and the castle
             */
            for(Tile tile : tileList.getTiles()){
                Tile tile1 = new Tile(tile);
                tile1.setTreasure(0);
                tile1.setCastle(0);
                tileList1.add(tile);
            }

            tileList2.setTiles(tileList1);
        }catch(NullPointerException e){  // map not ready or not found
            e.printStackTrace();
            return Response.status(Response.Status.NO_CONTENT).build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.NO_CONTENT).build();

        }
        return Response.status(200).type(MediaType.APPLICATION_XML).entity(tileList2).build();
    }
}
