package server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;


@Path("/action")
public class POSTActionMove {
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response consumeXML(ActionMove actionMove ) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gameserver");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final ActionMoveDAO actionMoveDAO = new ActionMoveDAO(entityManager);
        final TileDAO tileDAO = new TileDAO(entityManager);
        final MapDAO mapDAO = new MapDAO(entityManager);
        final GameDAO gameDAO = new GameDAO(entityManager);
        try {
            Tile tile = new Tile();
            Game game = new Game();
            game = gameDAO.findGameByID(actionMove.getGame_id());
            ActionMove actionMovetoDB = new ActionMove();
            actionMove.setGame_id(game.getGame_id());
            ActionMove otherplayermove = new ActionMove();
            //ActionMove lastmove = new ActionMove();

            if(game.getloser_id() != 0){
                actionMove.setWon(1);
                game.setWinner_id(actionMove.getPlayer_id());
                gameDAO.createGame(game);
            }
            if(!actionMove.checkSeconds()){
                Error error = new Error();
                error.setMessage("Too slow, you lost");
                game.setloser_id(actionMove.getPlayer_id());
                gameDAO.createGame(game);
                return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
            }
            if(actionMoveDAO.countActions(actionMove) != 0){
                otherplayermove = actionMoveDAO.findLastAction();
                if(otherplayermove.getPlayer_id().equals(actionMove.getPlayer_id()) ){  //other player's turn
                     return Response.status(Response.Status.NO_CONTENT).build();
                }else if(otherplayermove.getWon() == 1) {
                    Error error = new Error();
                    error.setMessage("You lost!");
                    return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
                }
            }
            tile = tileDAO.findTileByID(actionMove.getTarget_tile());
            if(tile.getType() == 3){
                Error error = new Error();
                error.setMessage("Water, you lost!");
                return Response.status(Response.Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML).entity(error).build();
            }
            actionMove.setActions_left(actionMove.getActions_left()-1);
            if(actionMove.getActions_left() == 0) {
                if (tile.getTreasure() == 1) {
                    actionMove.setInventory(1);
                }
                if (tile.getCastle() == 1 && actionMove.getInventory() == 1) {
                    actionMove.setWon(1);
                }
            }
                Long count = actionMoveDAO.countActions(actionMove);
                System.out.println(count);
                  if(count > 198 && actionMove.getWon() != 1){
                      Error error = new Error();
                      error.setMessage("Too many actions, both lost");
                      actionMovetoDB.setProperties(actionMove);
                      actionMoveDAO.createActionMove(actionMovetoDB);
                      return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
                  }

            actionMovetoDB.setProperties(actionMove);
            actionMovetoDB = actionMoveDAO.createActionMove(actionMovetoDB);
            return Response.status(Response.Status.OK).entity(actionMovetoDB).build();


        }catch(java.lang.NullPointerException e){
            e.printStackTrace();
            Error error = new Error();
            error.setMessage("Missing attributes for Player");
            return Response.status(Response.Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML).entity(error).build();
        }catch(java.lang.Exception e){
            e.printStackTrace();
            Error error = new Error();
            error.setMessage(e.toString());
            return Response.status(Response.Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML).entity(error).build();
        }

        finally{
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}

