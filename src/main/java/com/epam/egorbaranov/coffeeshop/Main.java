package com.epam.egorbaranov.coffeeshop;

import com.epam.egorbaranov.coffeeshop.business.CoffeeShop;
import com.epam.egorbaranov.coffeeshop.items.Beverage;
import com.epam.egorbaranov.coffeeshop.items.Extra;
import com.epam.egorbaranov.coffeeshop.items.Snack;
import com.epam.egorbaranov.coffeeshop.receipt.FormatReceipt;
import com.epam.egorbaranov.coffeeshop.receipt.Receipt;
import com.epam.egorbaranov.coffeeshop.receipt.SimpleReceiptFormatter;
import com.epam.egorbaranov.coffeeshop.service.CoffeeShopService;

public class Main {
    public static void main(String[] args) {
        // Create the coffee shop
        CoffeeShop coffeeShop = new CoffeeShop();

        // Add beverages, snacks, and extras
        coffeeShop.addBeverage(Beverage.LARGE_COFFEE);
        coffeeShop.addBeverage(Beverage.MEDIUM_COFFEE);
        coffeeShop.addSnack(Snack.BACON_ROLL);
        coffeeShop.addExtra(Extra.EXTRA_MILK);
        coffeeShop.addExtra(Extra.SPECIAL_ROAST);

        // Create the service and formatter
        FormatReceipt formatter = new SimpleReceiptFormatter();
        CoffeeShopService service = new CoffeeShopService(formatter);

        // Assume the customer has previously purchased 4 beverages
        int previousBeverages = 4;

        // Generate the receipt
        Receipt receipt = service.generateReceipt(coffeeShop, previousBeverages);

        // Format and print the receipt
        String formattedReceipt = service.formatReceipt(receipt);
        System.out.println(formattedReceipt);
    }
}