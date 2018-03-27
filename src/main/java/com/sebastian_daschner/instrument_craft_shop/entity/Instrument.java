package com.sebastian_daschner.instrument_craft_shop.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Instrument {

    @NotNull
    private InstrumentType type;

    @Min(0)
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
