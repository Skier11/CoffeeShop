package com.epam.egorbaranov.coffeeshop.service;

import com.epam.egorbaranov.coffeeshop.items.Beverage;
import com.epam.egorbaranov.coffeeshop.business.CoffeeShop;
import com.epam.egorbaranov.coffeeshop.items.Extra;
import com.epam.egorbaranov.coffeeshop.items.Snack;
import com.epam.egorbaranov.coffeeshop.receipt.FormatReceipt;
import com.epam.egorbaranov.coffeeshop.receipt.Receipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoffeeShopService {
    private static final int BEVERAGE_DISCOUNT_THRESHOLD = 5;
    private final FormatReceipt formatter;

    public CoffeeShopService(FormatReceipt formatter) {
        this.formatter = Objects.requireNonNull(formatter, "Formatter cannot be null");
    }

    public Receipt generateReceipt(CoffeeShop coffeeShop, int previousBeverages) {
        Objects.requireNonNull(coffeeShop, "CoffeeShop cannot be null");
        if (previousBeverages < 0) {
            throw new IllegalArgumentException("Previous beverages cannot be negative");
        }

        List<Beverage> beverages = coffeeShop.getBeverages();
        List<Snack> snacks = coffeeShop.getSnacks();
        List<Extra> extras = coffeeShop.getExtras();
        List<String> discounts = new ArrayList<>();

        BigDecimal total = calculateTotal(beverages, snacks, extras);
        total = applyBeverageDiscount(beverages, total, previousBeverages, discounts);
        total = applyExtraDiscount(snacks, extras, total, discounts);

        return new Receipt(beverages, snacks, extras, total, discounts);
    }

    private BigDecimal calculateTotal(List<Beverage> beverages, List<Snack> snacks, List<Extra> extras) {
        BigDecimal total = BigDecimal.ZERO;

        for (Beverage beverage : beverages) {
            total = total.add(beverage.getPrice());
        }

        for (Snack snack : snacks) {
            total = total.add(snack.getPrice());
        }

        for (Extra extra : extras) {
            total = total.add(extra.getPrice());
        }

        return total;
    }

    private BigDecimal applyBeverageDiscount(List<Beverage> beverages, BigDecimal total, int previousBeverages, List<String> discounts) {
        int totalBeverages = previousBeverages + beverages.size();
        if (totalBeverages >= BEVERAGE_DISCOUNT_THRESHOLD) {
            int discountBeverages = totalBeverages / BEVERAGE_DISCOUNT_THRESHOLD;
            for (int i = 0; i < discountBeverages && i < beverages.size(); i++) {
                BigDecimal discount = beverages.get(i).getPrice();
                total = total.subtract(discount);
                discounts.add("Free beverage discount: -" + discount + " CHF");
            }
        }
        return total;
    }

    private BigDecimal applyExtraDiscount(List<Snack> snacks, List<Extra> extras, BigDecimal total, List<String> discounts) {
        if (!snacks.isEmpty() && !extras.isEmpty()) {
            Extra highestPricedExtra = extras.stream().max((e1, e2) -> e1.getPrice().compareTo(e2.getPrice())).orElse(null);
            if (highestPricedExtra != null) {
                BigDecimal discount = highestPricedExtra.getPrice();
                total = total.subtract(discount);
                discounts.add("Free extra discount: -" + discount + " CHF");
            }
        }
        return total;
    }

    public String formatReceipt(Receipt receipt) {
        return formatter.format(receipt);
    }
}