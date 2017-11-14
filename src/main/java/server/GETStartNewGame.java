package server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/startgame")
public class GETStartNewGame {
    private static final Logger LOGGER = LogManager.getLogger(GETStartNewGame.class);
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("id") final Integer id) throws Exception{
        QueryDBOpenGame queryDBOpenGame = new QueryDBOpenGame(id);
        if(queryDBOpenGame.query()){
            return Response.status(Response.Status.ACCEPTED).build();
        }
        Integer game_id = queryDBOpenGame.query(id);
        Map map = new Map();
        map.setPlayer_id(id);
        map.setGame_id(game_id);
       // new WriteToDB(map);
        DBConnection dbConnection = new DBConnection();
        dbConnection.write(map);
        if(dbConnection.query())
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML).entity(map).build();
        else
            return Response.status(Response.Status.ACCEPTED).build();

    }
}
