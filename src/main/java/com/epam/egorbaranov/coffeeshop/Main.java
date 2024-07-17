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
    private static final Scanner scanner = new Scanner(System.in);
    private static CoffeeShop coffeeShop;
    private static int previousBeverages;

    public static void main(String[] args) {
        FormatReceipt formatter = new SimpleReceiptFormatter();
        CoffeeShopService service = new CoffeeShopService(formatter);

        System.out.println("Welcome to the Coffee Shop!");

        inputPreviousBeverages();

        while (true) {
            coffeeShop = new CoffeeShop();
            handleUserInput();

            Receipt receipt = service.generateReceipt(coffeeShop, previousBeverages);
            String formattedReceipt = service.formatReceipt(receipt);
            System.out.println(formattedReceipt);

            inputPreviousBeverages();
        }
    }

    private static void inputPreviousBeverages() {
        System.out.print("Enter the number of previously purchased beverages: ");
        previousBeverages = scanner.nextInt();
        scanner.nextLine();  // Consume newline
    }

    private static void handleUserInput() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 9) {
                break;
            }

            processChoice(choice);
        }
    }

    private static void printMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add Large Coffee (3.55 CHF)");
        System.out.println("2. Add Medium Coffee (3.05 CHF)");
        System.out.println("3. Add Small Coffee (2.55 CHF)");
        System.out.println("4. Add Orange Juice (3.95 CHF)");
        System.out.println("5. Add Bacon Roll (4.53 CHF)");
        System.out.println("6. Add Extra Milk (0.32 CHF)");
        System.out.println("7. Add Foamed Milk (0.51 CHF)");
        System.out.println("8. Add Special Roast (0.95 CHF)");
        System.out.println("9. Generate Receipt");
        System.out.println("10. Exit");
    }

    private static void processChoice(int choice) {
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
            case 10:
                System.out.println("Exiting...");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}