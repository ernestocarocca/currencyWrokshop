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

    public void chooseCurrency(CurrencyService service, int choice, Scanner scanner) {
        double amount = getAmountFromUser(scanner);

        switch (choice) {
            case 1 -> {
                double result = service.convert("USD", "SEK", amount);
                System.out.printf("%.2f USD = %.2f SEK%n", amount, result);
            }
            case 2 -> {
                double result = service.convert("SEK", "USD", amount);
                System.out.printf("%.2f SEK = %.2f USD%n", amount, result);
            }
            case 3 -> {
                double result = service.convert("EUR", "SEK", amount);
                System.out.printf("%.2f EUR = %.2f SEK%n", amount, result);
            }
            default -> System.out.println("Något gick fel.");
        }
    }

    private double getAmountFromUser(Scanner scanner) {
        while (true) {
            System.out.print("Insert the amount you want to convert: ");
            String input = scanner.nextLine().trim().replace(",", ".");
            try {
                double amount = Double.parseDouble(input);
                if (amount < 0) {
                    System.out.println("Fel: Beloppet måste vara positivt.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Fel: Ogiltigt belopp. Försök igen.");
            }
        }
    }
}
