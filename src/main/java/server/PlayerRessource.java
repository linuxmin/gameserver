package server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by laptop on 07.11.17.
 */
@Path("/greeting")
public class PlayerRessource {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("id") final int id) throws Exception{
        // final String output = "Hello " + id + "\n";
       // Player pl = new Player(0, "Benjamin", "Hartmann", 29, "ElMuldo");


        final Player pl = testxmlplayer.readPlayer("/home/linuxmin/gameserver/src/main/resources/requestregisternewplayer.xml");
        final Integer outputinteger = pl.getPlayer_id();
        final String output ="<Player><player_id>" + outputinteger.toString() + "</player_id>" + "<first_name>" + pl.getFirst_name() + "</first_name></Player>";
        if (id == pl.getPlayer_id())
            return Response.status(Response.Status.OK).entity(output).build();
        else
          return Response.status(Response.Status.NOT_FOUND).build();
    }
}
