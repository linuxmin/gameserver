package server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
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
        final GameDAO gameDAO = new GameDAO(entityManager);
        final PlayerDAO playerDAO = new PlayerDAO(entityManager);

        //variables for checking business rules
        Integer mapsize = tileList.getTiles().size();
        Integer grass = 0;
        Integer mountain = 0;
        Integer water = 0;
        Integer borderwater=0;
        Integer islandwater = 0;
        Integer treasure = 0;
        Integer castlewater = 0;
        Boolean hastreasure = false;

        try {
            Map map = new Map();
            Game game = new Game();
            Player player = new Player();
            /*
            finding actual player and game based on tilelist send by client
            and checking if game is not already lost because other player violated
            map rules.
             */
            player = playerDAO.findPlayerByID(tileList.getPlayer_id());
            game = gameDAO.findGameByID(player.getGame_id());
            if(game.getloser_id() != 0){
                game.setWinner_id(player.getPlayer_id());
                game=gameDAO.createGame(game);
                Error error = new Error();
                error.setMessage("Other Player violated Map rules");
                return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
            }
            /*
            checking the business rules of the map
             */
        for(Tile tile : tileList.getTiles()){
            Tile tiletoDB = new Tile(tile);
            tileDAO.createTile(tiletoDB);
            map = mapDAO.findMapByID(tile.getMap_id());
            map.getTiles().add(tiletoDB);
            mapDAO.createMap(map);
            switch(tile.getType()){
                case 1: //grass
                    grass = grass+1;
                    treasure = treasure +1;
                    if(treasure == 2){              //place treasure on 2nd grass field found
                        tile.setTreasure(1);
                        hastreasure = true;
                    }
                    break;
                case 2: //mountain
                    mountain = mountain+1;
                    if(tile.getCastle() == 1) //if castle stands on mountain
                        castlewater=1;
                    break;
                case 3:
                    water = water+1;
                    if(tile.getY() == 4){ //counting water on the borderside of map
                        borderwater = borderwater+1;
                    }
                    if(tile.getY() == 2 || tile.getY() == 3){ //to avoid islands water only allowed on y1 or y4
                        islandwater = islandwater + 1;
                    }
                    if(tile.getCastle() == 1){ //castle stands on water
                        castlewater = 1;
                    }
                    break;
            }
        }

        // @TODO Map Rules Checking enable after testing
      /*  if(mapsize != 32 || mountain < 3 || grass < 5 || water < 4 || borderwater > 3 || islandwater > 0 ||  castlewater != 0 || !hastreasure){      //auf 32 zurücksetzen
            Error error = new Error();
            error.setMessage("Violated Map Rules! mapsize: " + mapsize + " Mountains: " + mountain + " Grass: " + grass + " Water: " + water + " Borderwater: " +borderwater + "Possible island: " + islandwater + "castle not on grass" + castlewater);
            game = gameDAO.findGameByID(map.getGame_id());
            game.setEnd_code(1); //violated rules
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
        }
        /*
        checking if mapgeneration lasted less then 3 seconds
         */
        Long starttime = tileList.getTime_start_generation();
        Long endtime = tileList.getTime_end_generation();
        map.setTime_start_generation(starttime);
        map.setTime_end_generation(endtime);
        boolean timeok = map.checkSeconds();
            //boolean timeok = true;

            if(!timeok){ //if lasted too long
                Error error = new Error();
                error.setMessage("Mapgeneration lasted too long!");
                game = gameDAO.findGameByID(map.getGame_id());
                game.setEnd_code(1);  //end code 1 = violated rules
                game.setloser_id(map.getPlayer_id());
                String time_end = new Timestamp(System.currentTimeMillis()).toString();
                game.setTime_end(time_end);
                gameDAO.createGame(game);
                return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
            }
        }catch(NullPointerException e){ //if id of map is wrong
            Error error = new Error();
            error.setMessage("Map_ID wrong or not specified");
            e.printStackTrace();
            entityManager.close();
            entityManagerFactory.close();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
        }catch(Exception e){ //other unknown errors
            Error error = new Error();
            error.setMessage(e.toString());
            entityManager.close();
            entityManagerFactory.close();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
        }finally{
            entityManager.close();
            entityManagerFactory.close();
        }
        return Response.status(200).type(MediaType.APPLICATION_XML).entity(tileList).build();
    }
}
