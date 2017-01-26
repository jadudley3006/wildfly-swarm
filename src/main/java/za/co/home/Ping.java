package za.co.home;

import za.co.home.greeting.PingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/app")
@ApplicationScoped
public class Ping {

    @Inject
    PingService pingService;

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPing() {
        return pingService.getPing();
    }

}
