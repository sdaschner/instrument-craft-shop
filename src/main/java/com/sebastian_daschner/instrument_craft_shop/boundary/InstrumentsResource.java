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
import java.util.concurrent.CompletionStage;

@Path("instruments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InstrumentsResource {

    @Inject
    InstrumentCraftShop instrumentCraftShop;

    @GET
    public CompletionStage<List<Instrument>> getInstruments() {
        return instrumentCraftShop.getInstruments();
    }

    @GET
    @Path("create")
    public CompletionStage<Response> testCreateInstrument() {
        Instrument instrument = new Instrument(InstrumentType.GUITAR, 100);
        return instrumentCraftShop.craftInstrument(instrument)
                .thenApply(c -> Response.noContent().build())
                .exceptionally(this::exceptionResponse);
    }

    @POST
    public CompletionStage<Response> createInstrument(@Valid @NotNull Instrument instrument) {
        return instrumentCraftShop.craftInstrument(instrument)
                .thenApply(c -> Response.noContent().build())
                .exceptionally(this::exceptionResponse);
    }

    private Response exceptionResponse(Throwable e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("X-Error", e.getMessage())
                .build();
    }

}
