package server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


@Path("/action")
public class  POSTActionMove {
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

            if(game.getloser_id() != 0){   //if opponent lost in the last move, actual player wins
                actionMove.setWon(1);
                game.setWinner_id(actionMove.getPlayer_id());
                gameDAO.createGame(game);
            }
            if(!actionMove.checkSeconds()){ //if action lasted longer than 3 seconds
                Error error = new Error();
                error.setMessage("Too slow, you lost");
                game.setloser_id(actionMove.getPlayer_id());
                gameDAO.createGame(game);
                return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
            }
            if(actionMoveDAO.countActions(actionMove) != 0){ //if this is not the first move in the game
                otherplayermove = actionMoveDAO.findLastAction(); // find the last action
                if(otherplayermove.getPlayer_id().equals(actionMove.getPlayer_id()) ){  //other player's turn
                     return Response.status(Response.Status.NO_CONTENT).build();
                }else if(otherplayermove.getWon() == 1) { //if opponent won in last round
                    Error error = new Error();
                    error.setMessage("You lost!");
                    /*
                    write the 10th, the 20th and the winner of the game to an xml and export it
                     */
                    Integer winner_id = gameDAO.findWinner(game);
                    ActionMove actionMove10 = actionMoveDAO.findActions(actionMovetoDB,10);
                    ActionMove actionMove20 = actionMoveDAO.findActions(actionMovetoDB,20);
                    XMLFinishedGame xmlFinishedGame = new XMLFinishedGame();
                    xmlFinishedGame.setActionMove10(actionMove10);
                    xmlFinishedGame.setActionMove20(actionMove20);
                    xmlFinishedGame.setWinner_id(winner_id);
                    JAXBContext contextObj = JAXBContext.newInstance(XMLFinishedGame.class);
                    Marshaller marshallerObj = contextObj.createMarshaller();
                    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshallerObj.marshal(xmlFinishedGame, new FileOutputStream("XMLFinished.xml"));
                    return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
                }
            }
            tile = tileDAO.findTileByID(actionMove.getTarget_tile());  //lookup where the player wants to move
            if(tile.getType() == 3){ // if player wants to move on water an error will be sent to client
                Error error = new Error();
                error.setMessage("Water, you lost!");
                return Response.status(Response.Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML).entity(error).build();
            }
            actionMove.setActions_left(actionMove.getActions_left()-1); //count down actions left to get to target tile
            if(actionMove.getActions_left() == 0) {
                if (tile.getTreasure() == 1) {//if treasure is on target tile and no actions left, inventory is 1
                    actionMove.setInventory(1);
                }
                if (tile.getCastle() == 1 && actionMove.getInventory() == 1) { // if castle is reached with treasure
                    actionMove.setWon(1);
                }
            }
                Long count = actionMoveDAO.countActions(actionMove);
                  if(count > 198 && actionMove.getWon() != 1){ // if player has not won with action and this would be the 200th action, the game is lost
                      Error error = new Error();
                      error.setMessage("Too many actions, both lost");
                      actionMovetoDB.setProperties(actionMove);
                      actionMoveDAO.createActionMove(actionMovetoDB);
                      return Response.status(Response.Status.EXPECTATION_FAILED).entity(error).build();
                  }

            actionMovetoDB.setProperties(actionMove); //the action is written to another object
            actionMovetoDB = actionMoveDAO.createActionMove(actionMovetoDB); //the action is saved to db



            return Response.status(Response.Status.OK).entity(actionMovetoDB).build();


        }catch(java.lang.NullPointerException e){ //if something goes wrong, e.g. some id is wrong
            e.printStackTrace();
            Error error = new Error();
            error.setMessage("Action failed");
            return Response.status(Response.Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_XML).entity(error).build();
        }catch(java.lang.Exception e){ //other errors
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

