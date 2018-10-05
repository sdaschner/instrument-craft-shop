package com.sebastian_daschner.instrument_craft_shop.boundary;

import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("health")
public class HealthResource {

    @GET
    @Timeout
    public String health() {
        return "OK";
    }

}
