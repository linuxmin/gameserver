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
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("name") final String name){
        final String output = "Hello " + name + "\n";
        return Response.status(Response.Status.OK).entity(output).build();
    }
}
