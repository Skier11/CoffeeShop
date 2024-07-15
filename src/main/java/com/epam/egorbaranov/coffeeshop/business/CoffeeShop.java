package com.epam.egorbaranov.coffeeshop.business;

import com.epam.egorbaranov.coffeeshop.items.Beverage;
import com.epam.egorbaranov.coffeeshop.items.Extra;
import com.epam.egorbaranov.coffeeshop.items.Snack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CoffeeShop {
    private final List<Beverage> beverages = new ArrayList<>();
    private final List<Snack> snacks = new ArrayList<>();
    private final List<Extra> extras = new ArrayList<>();

    public void addBeverage(Beverage beverage) {
        Objects.requireNonNull(beverage, "Beverage cannot be null");
        beverages.add(beverage);
    }

    public void addSnack(Snack snack) {
        Objects.requireNonNull(snack, "Snack cannot be null");
        snacks.add(snack);
    }

    public void addExtra(Extra extra) {
        Objects.requireNonNull(extra, "Extra cannot be null");
        extras.add(extra);
    }

    public List<Beverage> getBeverages() {
        return Collections.unmodifiableList(beverages);
    }

    public List<Snack> getSnacks() {
        return Collections.unmodifiableList(snacks);
    }

    public List<Extra> getExtras() {
        return Collections.unmodifiableList(extras);
    }
}