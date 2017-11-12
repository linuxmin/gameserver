package server;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/registernewplayer")
public class POSTRegisterNewPlayer {
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response consumeXML(Player player ) {
        //write new Player to the DB
        new WriteToDB(player);

        //query DB if there is already a Game with 1 Player waiting for Player 2.
        QueryDBOpenGame queryDBOpenGame = new QueryDBOpenGame(player);
        if(queryDBOpenGame.query()){
            Game game  = queryDBOpenGame.insert(player);
            player.setGame_id(game.getGame_id());
            return Response.status(200).type(MediaType.APPLICATION_XML).entity(player).build();
        }else {

            //if not a new Game and a new Play is created with Player 1
            Game game = new Game(player);
            new WriteToDB(game);
            player.setGame_id(game.getGame_id());
            //return the Game and the Play the Player is in
            return Response.status(200).type(MediaType.APPLICATION_XML).entity(player).build();
        }
    }
}

