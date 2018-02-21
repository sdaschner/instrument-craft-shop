package com.sebastian_daschner.instrument_craft_shop;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.ServiceUnavailableException;
import java.util.concurrent.atomic.AtomicBoolean;

@Path("health")
@ApplicationScoped
public class HealthResource {

    private AtomicBoolean shutdown = new AtomicBoolean();

    @GET
    public String healthCheck() {
        if (shutdown.get())
            throw new ServiceUnavailableException();
        return "ok";
    }

    @DELETE
    public void shutdown() {
        shutdown.set(false);
    }

}
