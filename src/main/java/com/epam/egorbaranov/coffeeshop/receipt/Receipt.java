package com.epam.egorbaranov.coffeeshop.receipt;

import com.epam.egorbaranov.coffeeshop.items.Beverage;
import com.epam.egorbaranov.coffeeshop.items.Extra;
import com.epam.egorbaranov.coffeeshop.items.Snack;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Receipt {
    private final List<Beverage> beverages;
    private final List<Snack> snacks;
    private final List<Extra> extras;
    private final BigDecimal total;
    private final List<String> discounts;

    public Receipt(List<Beverage> beverages, List<Snack> snacks, List<Extra> extras, BigDecimal total, List<String> discounts) {
        this.beverages = Objects.requireNonNull(beverages, "Beverages cannot be null");
        this.snacks = Objects.requireNonNull(snacks, "Snacks cannot be null");
        this.extras = Objects.requireNonNull(extras, "Extras cannot be null");
        this.total = Objects.requireNonNull(total, "Total cannot be null");
        this.discounts = Objects.requireNonNull(discounts, "Discounts cannot be null");
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<String> getDiscounts() {
        return discounts;
    }
}
