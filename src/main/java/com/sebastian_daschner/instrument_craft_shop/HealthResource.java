package com.sebastian_daschner.instrument_craft_shop;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("health")
public class HealthResource {

    @GET
    public String healthCheck() {
        return "OK";
    }

}
