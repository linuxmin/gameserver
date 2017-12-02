package server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/registernewmap")
public class POSTMapTiles {
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response consumeXML(TileList tileList) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final MapDAO mapDAO = new MapDAO(entityManager);
        final TileDAO tileDAO = new TileDAO(entityManager);
        Map map = new Map();
       // Tile tiletoDB = new Tile();
        //tiletoDB = tileList.getTiles().get(0);
        //map = mapDAO.findMapByID(tiletoDB.getMap_id());
        //tiletoDB.setMap_id(map.getMap_id());
        //mapDAO.createMap(map);
        //tileDAO.createTile(tiletoDB);
        Integer mapsize = tileList.getTiles().size();
        Integer grass = 0;
        Integer mountain = 0;
        Integer water = 0;
        Integer borderwater=0;
        Integer islandwater = 0;
        Integer island = 0;
        TileList respondedTileList = new TileList();
        List<Tile> tiles = new ArrayList<>();
        mapDAO.insertTiles(tileList);
        for(Tile tile : tileList.getTiles()){
            Tile tiletoDB = new Tile();
            tileDAO.createTile(tiletoDB);
            tiletoDB.setMap_id(tile.getMap_id());
            tiletoDB.setCastle(tile.getCastle());
            tiletoDB.setTreasure(tile.getTreasure());
            tiletoDB.setType(tile.getType());
            tiletoDB.setX(tile.getX());
            tiletoDB.setY(tile.getY());
            tileDAO.createTile(tiletoDB);
         //   tile.setMap_id(115);
        //    tileDAO.createTile(tile);
            switch(tile.getType()){
                case 1:
                    grass = grass+1;
                    break;
                case 2:
                    mountain = mountain+1;
                    break;
                case 3:
                    water = water+1;
                    if(tile.getY() == 4){
                        borderwater = borderwater+1;
                    }
                    break;
            }
        }

        if(mapsize != 3 || mountain < 3 || grass < 5 || water < 4 || borderwater > 3){      //auf 32 zurücksetzen
            Error error = new Error();
            error.setMessage("Violated Map Rules! mapsize: " + mapsize + " Mountains: " + mountain + " Grass: " + grass + " Water: " + water + " Borderwater: " +borderwater);
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
        }

        return Response.status(200).type(MediaType.APPLICATION_XML).entity(grass).build();

    }
}
