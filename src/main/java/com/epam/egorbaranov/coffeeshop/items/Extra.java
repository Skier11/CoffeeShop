package com.epam.egorbaranov.coffeeshop.items;

import java.math.BigDecimal;

public enum Extra implements Item {
    EXTRA_MILK("Extra Milk", new BigDecimal("0.32")),
    FOAMED_MILK("Foamed Milk", new BigDecimal("0.51")),
    SPECIAL_ROAST("Special Roast", new BigDecimal("0.95"));

    private final String name;
    private final BigDecimal price;

    Extra(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}