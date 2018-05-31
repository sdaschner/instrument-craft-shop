package com.sebastian_daschner.instrument_craft_shop.boundary;

import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("instruments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InstrumentsResource {

    @Inject
    InstrumentCraftShop instrumentCraftShop;

    @GET
    public List<Instrument> getInstruments() {
        return instrumentCraftShop.getInstruments();
    }

    @POST
    public Response createInstrument(@Valid @NotNull Instrument instrument) {
        try {
            instrumentCraftShop.craftInstrument(instrument);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("X-Error", e.getMessage())
                    .build();
        }
    }

}
