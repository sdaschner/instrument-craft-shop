package com.sebastian_daschner.instrument_craft_shop;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("instruments")
public class InstrumentsResource {

    @Inject
    InstrumentCraftShop instrumentCraftShop;

    @POST
    public void createInstrument(@Valid @NotNull Instrument instrument) {
        instrumentCraftShop.craft(instrument);
    }

}
