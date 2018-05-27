package com.sebastian_daschner.instrument_craft_shop.boundary;

import com.sebastian_daschner.instrument_craft_shop.control.Instruments;
import com.sebastian_daschner.instrument_craft_shop.control.MakerBot;
import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class InstrumentCraftShop {

    @Inject
    MakerBot makerBot;

    @Inject
    Instruments instruments;

    public void craftInstrument(Instrument instrument) {
        makerBot.printInstrument(instrument.getType());

        System.out.println("printing instrument " + instrument.getType() + " with price $" + instrument.getPrice());
        instruments.add(instrument);
        sleep(100);
    }

    public List<Instrument> getInstruments() {
        sleep(100);
        return instruments.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
