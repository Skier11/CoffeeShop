package com.epam.egorbaranov.coffeeshop;

import com.epam.egorbaranov.coffeeshop.items.Beverage;
import com.epam.egorbaranov.coffeeshop.items.Extra;
import com.epam.egorbaranov.coffeeshop.items.Snack;
import com.epam.egorbaranov.coffeeshop.receipt.FormatReceipt;
import com.epam.egorbaranov.coffeeshop.receipt.Receipt;
import com.epam.egorbaranov.coffeeshop.receipt.SimpleReceiptFormatter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiptFormatterTest {

    @Test
    public void testSimpleReceiptFormatter() {
        Receipt receipt = new Receipt(
                List.of(Beverage.LARGE_COFFEE),
                List.of(Snack.BACON_ROLL),
                List.of(Extra.EXTRA_MILK),
                new BigDecimal("8.40")
        );

        FormatReceipt formatter = new SimpleReceiptFormatter();
        String formattedReceipt = formatter.format(receipt);

        String expectedReceipt = "Coffee Shop Receipt\n" +
                "====================\n" +
                "Large Coffee - 3.55 CHF\n" +
                "Bacon Roll - 4.53 CHF\n" +
                "Extra Milk - 0.32 CHF\n" +
                "--------------------\n" +
                "Total: 8.40 CHF\n";

        assertEquals(expectedReceipt, formattedReceipt);
    }
}
