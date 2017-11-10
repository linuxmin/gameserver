package server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by laptop on 07.11.17.
 */
public class PlayerRessource {
    private static final List<player> players = new ArrayList<>();
    static {
        player pl = new player(1,"Benjamin","Hartmann",29,"mistertool");
        players.add(pl);
    }
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPlayerById(@PathParam("id") final long id){
        final Optional<player> optionalPlayer = findPlayerById(id);
        if (optionalPlayer.isPresent()){
            final player pl = optionalPlayer.get();
            return Response.status(Response.Status.OK).entity(pl).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    private Optional<player> findPlayerById(final long id){
        return players.stream().filter(player -> player.getPlayer_id()==id).findFirst();
    }
}
