package se.currency.workshop;

import com.google.gson.Gson;
import se.currency.workshop.api.FetchCurrency;
import se.currency.workshop.model.CurrencyResponse;
import se.currency.workshop.service.CurrencyService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String json = FetchCurrency.fetch();
        Gson gson = new Gson();
        CurrencyResponse response = gson.fromJson(json, CurrencyResponse.class);
        CurrencyService service = new CurrencyService();
        service.setCurrencyResponse(response);
        // Menu
        System.out.println("Exchange rates from " + response.getSource());
        System.out.println("Choose an option:");
        System.out.println("1. USD to SEK");
        System.out.println("2. SEK to USD");
        System.out.println("3. EUR to SEK");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        System.out.println("insert the amount you want to convert");
        double amount = scanner.nextDouble();

        switch (choice) {
            case 1:
                double usdToSek = service.convertUsdToSek(amount);
                System.out.printf(amount + " USD = %.2f kr%n ", usdToSek);
                break;
            case 2:
                double sekToUsd = service.convertSekToUsd(amount);
                System.out.printf("%.2f SEK = %.2f USD%n", amount, sekToUsd);
                break;
            case 3:
               double euroToSek = service.convertEuroToSek(amount);
                System.out.printf(amount +  " EUR = %.2f SEK%n", euroToSek);
                break;
            default:
                System.out.println("%.2f Invalid option.");
        }
    }

}