package com.sebastian_daschner.instrument_craft_shop.control;

import com.sebastian_daschner.instrument_craft_shop.entity.Instrument;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class Instruments {

    private final List<Instrument> instruments = new ArrayList<>();

    @Lock(LockType.READ)
    public List<Instrument> get() {
        return Collections.unmodifiableList(instruments);
    }

    @Lock
    public void add(Instrument instrument) {
        instruments.add(instrument);
    }

}
