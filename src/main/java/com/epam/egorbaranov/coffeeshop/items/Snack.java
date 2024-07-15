package com.epam.egorbaranov.coffeeshop.items;

import java.math.BigDecimal;

public enum Snack implements Item {
    BACON_ROLL("Bacon Roll", new BigDecimal("4.53"));

    private final String name;
    private final BigDecimal price;

    Snack(String name, BigDecimal price) {
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