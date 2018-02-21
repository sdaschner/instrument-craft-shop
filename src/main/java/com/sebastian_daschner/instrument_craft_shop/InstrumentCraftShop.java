package com.sebastian_daschner.instrument_craft_shop;

import javax.inject.Inject;

public class InstrumentCraftShop {

    @Inject
    MakerBot makerBot;

    public void craft(Instrument instrument) {
        makerBot.startPrintJob(instrument.getType());
        System.out.println("printing a " + instrument.getType() + " for $" + instrument.getPrice());
    }

}
