package com.sebastian_daschner.instrument_craft_shop.boundary;

import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.Enumeration;

@Path("instruments")
public class InstrumentsResource {

    @Inject
    InstrumentCraftShop instrumentCraftShop;

    @POST
    public void createInstrument(@Valid @NotNull Instrument instrument, @Context HttpServletRequest request) {
        logHeaders(request);
        instrumentCraftShop.craftInstrument(instrument);
    }

    private void logHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            System.out.println(header + ": " + request.getHeader(header));
        }
        System.out.println();
    }

}
