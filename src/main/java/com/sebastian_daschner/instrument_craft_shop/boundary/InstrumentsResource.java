package com.sebastian_daschner.instrument_craft_shop.boundary;

import com.airhacks.porcupine.execution.boundary.Dedicated;
import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;

@Path("instruments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InstrumentsResource {

    @Inject
    InstrumentCraftShop instrumentCraftShop;

    @Inject
    @Dedicated("instruments-read")
    ExecutorService readExecutor;

    @Inject
    @Dedicated("instruments-write")
    ExecutorService writeExecutor;

    @GET
    public CompletionStage<List<Instrument>> getInstruments() {
        return CompletableFuture.supplyAsync(() -> instrumentCraftShop.getInstruments(), readExecutor);
    }

    @POST
    public CompletionStage<Response> createInstrument(@Valid @NotNull Instrument instrument) {
        return CompletableFuture.runAsync(() -> instrumentCraftShop.craftInstrument(instrument), writeExecutor)
                .thenApply(c -> Response.noContent().build())
                .exceptionally(e -> Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .header("X-Error", e.getMessage())
                        .build());
    }

}
