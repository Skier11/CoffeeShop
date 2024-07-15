package com.epam.egorbaranov.coffeeshop.items;

import java.math.BigDecimal;

public enum Beverage implements Item {
    SMALL_COFFEE("Small Coffee", new BigDecimal("2.55")),
    MEDIUM_COFFEE("Medium Coffee", new BigDecimal("3.05")),
    LARGE_COFFEE("Large Coffee", new BigDecimal("3.55")),
    ORANGE_JUICE("Orange Juice", new BigDecimal("3.95"));

    private final String name;
    private final BigDecimal price;

    Beverage(String name, BigDecimal price) {
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