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
public class HelloWorldRessource {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("id") final int id) {
        // final String output = "Hello " + id + "\n";
        player pl = new player(0, "Benjamin", "Hartmann", 29, "ElMuldo");

        final String output = pl.getFirst_name();
        if (id == pl.getPlayer_id())
            return Response.status(Response.Status.OK).entity(output).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
