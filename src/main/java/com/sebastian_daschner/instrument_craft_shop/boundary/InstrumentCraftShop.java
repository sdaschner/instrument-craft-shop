package com.sebastian_daschner.instrument_craft_shop.boundary;

import com.sebastian_daschner.instrument_craft_shop.control.MakerBot;
import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;
import com.sebastian_daschner.instrument_craft_shop.entity.InstrumentType;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class InstrumentCraftShop {

    @Inject
    MakerBot makerBot;

    public void craftInstrument(Instrument instrument) {
        makerBot.printInstrument(instrument.getType());

        sleep(200);
    }

    public List<Instrument> getInstruments() {
        sleep(200);
        return buildInstruments();
    }

    private static List<Instrument> buildInstruments() {
        return Arrays.asList(
                new Instrument(InstrumentType.GUITAR, 100),
                new Instrument(InstrumentType.VIOLIN, 200),
                new Instrument(InstrumentType.PIANO, 500)
        );
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
