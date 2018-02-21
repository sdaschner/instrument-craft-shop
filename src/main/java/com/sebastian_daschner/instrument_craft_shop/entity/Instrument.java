package com.sebastian_daschner.instrument_craft_shop.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Instrument {

    @NotNull
    private InstrumentType type;

    @Positive
    private int price;

    public InstrumentType getType() {
        return type;
    }

    public void setType(InstrumentType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
