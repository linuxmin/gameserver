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
        //WriteToDBPlayer writeToDBPlayer = new WriteToDBPlayer(player);
        //writeToDBPlayer.write();
        //query DB if there is already a Game with 1 Player waiting for Player 2.
        QueryDBOpenGame queryDBOpenGame = new QueryDBOpenGame(player.getPlayer_id());
        if(queryDBOpenGame.query()){
            // Insert the player_id as player2_id into table game
            Game game  = queryDBOpenGame.insert(player);
            //set Game_id of player to game_id from DB and return data as XML to the client
            player.setGame_id(game.getGame_id());
            return Response.status(200).type(MediaType.APPLICATION_XML).entity(player).build();
        }else {

            //if no open Game exists a new one will be created and the game_id will be set as the players game_id
            Game game = new Game(player);
            new WriteToDB(game);
            player.setGame_id(game.getGame_id());
            //return the data
            return Response.status(200).type(MediaType.APPLICATION_XML).entity(player).build();
        }
    }
}

