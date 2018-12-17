package com.sebastian_daschner.instrument_craft_shop.boundary;

import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;
import com.sebastian_daschner.instrument_craft_shop.entity.InstrumentType;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("instruments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InstrumentsResource {

    @Inject
    InstrumentCraftShop instrumentCraftShop;

    @Resource
    ManagedExecutorService mes;

    @GET
    public CompletionStage<Response> getInstruments() {
        return CompletableFuture.supplyAsync(() -> instrumentCraftShop.getInstruments(), mes)
                .thenApply(f -> {
                    try {
                        return Response.ok(f.get()).build();
                    } catch (Exception e) {
                        return exceptionResponse(e);
                    }
                }).exceptionally(this::exceptionResponse);
    }

    @GET
    @Path("create")
    public CompletionStage<Response> testCreateInstrument() {
        Instrument instrument = new Instrument(InstrumentType.GUITAR, 100);
        return CompletableFuture.runAsync(() -> instrumentCraftShop.craftInstrument(instrument), mes)
                .thenApply(c -> Response.noContent().build())
                .exceptionally(this::exceptionResponse);
    }

    @POST
    public CompletionStage<Response> createInstrument(@Valid @NotNull Instrument instrument) {
        return CompletableFuture.runAsync(() -> instrumentCraftShop.craftInstrument(instrument), mes)
                .thenApply(c -> Response.noContent().build())
                .exceptionally(this::exceptionResponse);
    }

    private Response exceptionResponse(Throwable e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("X-Error", e.getMessage())
                .build();
    }

}
