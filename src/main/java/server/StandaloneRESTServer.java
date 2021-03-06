package server;

/**
 * Created by laptop on 07.11.17.
 */
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class StandaloneRESTServer {
    private static final String BASE_URI = "http://localhost:7777/rest/";
    private static final String BASE_PACKAGE = "server";
    private static final Logger LOGGER = LogManager.getLogger(StandaloneRESTServer.class);

    public static void main(final String[] args) throws URISyntaxException,IOException{
        final ResourceConfig rc = new ResourceConfig().packages(BASE_PACKAGE);
        final HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create(BASE_URI),rc);
        System.out.println("REST Server is running at " + BASE_URI);
        System.in.read();

      LOGGER.info(server);

        server.stop(0);
    }
}
