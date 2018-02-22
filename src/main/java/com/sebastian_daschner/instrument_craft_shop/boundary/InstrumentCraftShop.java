package com.sebastian_daschner.instrument_craft_shop.boundary;

import com.sebastian_daschner.instrument_craft_shop.control.MakerBot;
import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class InstrumentCraftShop {

    @Inject
    MakerBot makerBot;

    public void craftInstrument(Instrument instrument) {
        makerBot.printInstrument(instrument.getType());

        System.out.println("printing instrument " + instrument.getType() + " with price $" + instrument.getPrice());
    }

}
