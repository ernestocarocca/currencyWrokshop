package se.currency.workshop.ui;

import se.currency.workshop.service.CurrencyService;

import java.util.Scanner;

public class MenuHandler {
    public void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. USD to SEK");
        System.out.println("2. SEK to USD");
        System.out.println("3. EUR to SEK");
    }
    public void chooseCurrency(CurrencyService service) {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("Insert the amount you want to convert:");
        double amount = scanner.nextDouble();

        switch (choice) {
            case 1:
                double usdToSek = service.convert("USD", "SEK", amount);
                System.out.printf("%.2f USD = %.2f SEK%n", amount, usdToSek);
                break;
            case 2:
                double sekToUsd = service.convert("SEK", "USD", amount);
                System.out.printf("%.2f SEK = %.2f USD%n", amount, sekToUsd);
                break;
            case 3:
                double eurToSek = service.convert("EUR", "SEK", amount);
                System.out.printf("%.2f EUR = %.2f SEK%n", amount, eurToSek);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}