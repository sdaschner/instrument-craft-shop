package com.sebastian_daschner.instrument_craft_shop.boundary;

import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;
import com.sebastian_daschner.instrument_craft_shop.entity.InstrumentType;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("instruments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InstrumentsResource {

    @Inject
    InstrumentCraftShop instrumentCraftShop;

    @GET
    public List<Instrument> getInstruments() throws Exception {
        return instrumentCraftShop.getInstruments().get(500, TimeUnit.MILLISECONDS);
    }

    @GET
    @Path("create")
    public Response testCreateInstrument() {
        Instrument instrument = new Instrument(InstrumentType.GUITAR, 100);
        try {
            instrumentCraftShop.craftInstrument(instrument).get(2000, TimeUnit.MILLISECONDS);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error", e.getMessage())
                    .build();
        }
    }

    @POST
    public Response createInstrument(@Valid @NotNull Instrument instrument) {
        try {
            instrumentCraftShop.craftInstrument(instrument).get(2000, TimeUnit.MILLISECONDS);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error", e.getMessage())
                    .build();
        }
    }

}
