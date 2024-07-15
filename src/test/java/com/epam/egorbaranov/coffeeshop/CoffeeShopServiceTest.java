package com.epam.egorbaranov.coffeeshop;

import com.epam.egorbaranov.coffeeshop.items.Beverage;
import com.epam.egorbaranov.coffeeshop.business.CoffeeShop;
import com.epam.egorbaranov.coffeeshop.items.Extra;
import com.epam.egorbaranov.coffeeshop.items.Snack;
import com.epam.egorbaranov.coffeeshop.receipt.FormatReceipt;
import com.epam.egorbaranov.coffeeshop.receipt.Receipt;
import com.epam.egorbaranov.coffeeshop.receipt.SimpleReceiptFormatter;
import com.epam.egorbaranov.coffeeshop.service.CoffeeShopService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoffeeShopServiceTest {

    @Test
    public void testReceiptWithSingleOrder() {
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.addBeverage(Beverage.LARGE_COFFEE);
        coffeeShop.addExtra(Extra.EXTRA_MILK);

        CoffeeShopService service = new CoffeeShopService(new SimpleReceiptFormatter());
        Receipt receipt = service.generateReceipt(coffeeShop, 0);

        assertEquals(new BigDecimal("3.87"), receipt.getTotal());
        assertEquals(1, receipt.getBeverages().size());
        assertEquals(1, receipt.getExtras().size());
    }

    @Test
    public void testReceiptWithMultipleOrders() {
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.addBeverage(Beverage.LARGE_COFFEE);
        coffeeShop.addExtra(Extra.EXTRA_MILK);
        coffeeShop.addSnack(Snack.BACON_ROLL);
        coffeeShop.addBeverage(Beverage.ORANGE_JUICE);

        CoffeeShopService service = new CoffeeShopService(new SimpleReceiptFormatter());
        Receipt receipt = service.generateReceipt(coffeeShop, 0);

        assertEquals(new BigDecimal("12.03"), receipt.getTotal());
        assertEquals(2, receipt.getBeverages().size());
        assertEquals(1, receipt.getSnacks().size());
        assertEquals(1, receipt.getExtras().size());
    }

    @Test
    public void testReceiptWithFreeExtra() {
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.addBeverage(Beverage.LARGE_COFFEE);
        coffeeShop.addExtra(Extra.EXTRA_MILK);
        coffeeShop.addSnack(Snack.BACON_ROLL);

        CoffeeShopService service = new CoffeeShopService(new SimpleReceiptFormatter());
        Receipt receipt = service.generateReceipt(coffeeShop, 0);

        assertEquals(new BigDecimal("8.08"), receipt.getTotal());
    }

    @Test
    public void testReceiptWithFreeBeverage() {
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.addBeverage(Beverage.LARGE_COFFEE);
        coffeeShop.addExtra(Extra.EXTRA_MILK);
        coffeeShop.addBeverage(Beverage.MEDIUM_COFFEE);
        coffeeShop.addBeverage(Beverage.SMALL_COFFEE);
        coffeeShop.addExtra(Extra.SPECIAL_ROAST);
        coffeeShop.addBeverage(Beverage.LARGE_COFFEE);

        CoffeeShopService service = new CoffeeShopService(new SimpleReceiptFormatter());
        Receipt receipt = service.generateReceipt(coffeeShop, 4); // Assume 4 previous beverages

        assertEquals(new BigDecimal("10.42"), receipt.getTotal());
    }

    @Test
    public void testGenerateReceiptWithNullCoffeeShop() {
        CoffeeShopService service = new CoffeeShopService(new SimpleReceiptFormatter());
        assertThrows(NullPointerException.class, () -> service.generateReceipt(null, 0));
    }

    @Test
    public void testGenerateReceiptWithNegativePreviousBeverages() {
        CoffeeShop coffeeShop = new CoffeeShop();
        CoffeeShopService service = new CoffeeShopService(new SimpleReceiptFormatter());
        assertThrows(IllegalArgumentException.class, () -> service.generateReceipt(coffeeShop, -1));
    }

    @Test
    public void testFormatterWithNullReceipt() {
        FormatReceipt formatter = new SimpleReceiptFormatter();
        assertThrows(NullPointerException.class, () -> formatter.format(null));
    }
}