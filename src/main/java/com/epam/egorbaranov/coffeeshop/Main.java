package com.epam.egorbaranov.coffeeshop;

import com.epam.egorbaranov.coffeeshop.business.CoffeeShop;
import com.epam.egorbaranov.coffeeshop.items.Beverage;
import com.epam.egorbaranov.coffeeshop.items.Extra;
import com.epam.egorbaranov.coffeeshop.items.Snack;
import com.epam.egorbaranov.coffeeshop.receipt.FormatReceipt;
import com.epam.egorbaranov.coffeeshop.receipt.Receipt;
import com.epam.egorbaranov.coffeeshop.receipt.SimpleReceiptFormatter;
import com.epam.egorbaranov.coffeeshop.service.CoffeeShopService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FormatReceipt formatter = new SimpleReceiptFormatter();
        CoffeeShopService service = new CoffeeShopService(formatter);

        System.out.println("Welcome to the Coffee Shop!");

        // Input previous beverages
        System.out.print("Enter the number of previously purchased beverages: ");
        int previousBeverages = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        while (true) {
            CoffeeShop coffeeShop = new CoffeeShop();

            while (true) {
                System.out.println("Select an option:");
                System.out.println("1. Add Large Coffee");
                System.out.println("2. Add Medium Coffee");
                System.out.println("3. Add Small Coffee");
                System.out.println("4. Add Orange Juice");
                System.out.println("5. Add Bacon Roll");
                System.out.println("6. Add Extra Milk");
                System.out.println("7. Add Foamed Milk");
                System.out.println("8. Add Special Roast");
                System.out.println("9. Generate Receipt");
                System.out.println("10. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        coffeeShop.addBeverage(Beverage.LARGE_COFFEE);
                        break;
                    case 2:
                        coffeeShop.addBeverage(Beverage.MEDIUM_COFFEE);
                        break;
                    case 3:
                        coffeeShop.addBeverage(Beverage.SMALL_COFFEE);
                        break;
                    case 4:
                        coffeeShop.addBeverage(Beverage.ORANGE_JUICE);
                        break;
                    case 5:
                        coffeeShop.addSnack(Snack.BACON_ROLL);
                        break;
                    case 6:
                        coffeeShop.addExtra(Extra.EXTRA_MILK);
                        break;
                    case 7:
                        coffeeShop.addExtra(Extra.FOAMED_MILK);
                        break;
                    case 8:
                        coffeeShop.addExtra(Extra.SPECIAL_ROAST);
                        break;
                    case 9:
                        Receipt receipt = service.generateReceipt(coffeeShop, previousBeverages);
                        String formattedReceipt = service.formatReceipt(receipt);
                        System.out.println(formattedReceipt);
                        break;
                    case 10:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                if (choice == 9) {
                    break;
                }
            }

            // Reset previous beverages count after generating receipt
            System.out.print("Enter the number of previously purchased beverages for the next customer: ");
            previousBeverages = scanner.nextInt();
            scanner.nextLine();  // Consume newline
        }
    }
}