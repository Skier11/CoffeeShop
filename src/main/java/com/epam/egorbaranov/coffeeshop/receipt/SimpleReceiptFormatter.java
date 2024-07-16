package com.epam.egorbaranov.coffeeshop.receipt;

import com.epam.egorbaranov.coffeeshop.items.Item;

import java.util.List;
import java.util.Objects;

public class SimpleReceiptFormatter implements FormatReceipt {
    @Override
    public String format(Receipt receipt) {
        Objects.requireNonNull(receipt, "Receipt cannot be null");

        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("Coffee Shop Receipt\n");
        receiptBuilder.append("====================\n");

        appendItems(receipt.getBeverages(), receiptBuilder);
        appendItems(receipt.getSnacks(), receiptBuilder);
        appendItems(receipt.getExtras(), receiptBuilder);

        receiptBuilder.append("--------------------\n");

        // Append discounts
        for (String discount : receipt.getDiscounts()) {
            receiptBuilder.append(discount).append("\n");
        }

        receiptBuilder.append("--------------------\n");
        receiptBuilder.append(String.format("Total: %.2f CHF\n", receipt.getTotal()));

        return receiptBuilder.toString();
    }

    private <T extends Item> void appendItems(List<T> items, StringBuilder receiptBuilder) {
        for (T item : items) {
            receiptBuilder.append(item.getName()).append(" - ").append(item.getPrice()).append(" CHF\n");
        }
    }
}