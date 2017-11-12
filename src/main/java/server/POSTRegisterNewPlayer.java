package server;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/registernewplayer")
public class POSTRegisterNewPlayer {
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response consumeXML(Player player ) {
        new WriteToDB(player);
        return Response.status(200).type(MediaType.APPLICATION_XML).entity(player).build();
    }
}

