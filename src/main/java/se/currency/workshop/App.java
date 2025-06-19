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
        System.out.println("Example: USD to SEK or SEK to USD");
        System.out.print("Enter source currency (e.g. USD): ");
        String from = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter target currency (e.g. SEK): ");
        String to = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        double sek = service.convertUsdToSek(amount);
        double backToUsd = service.convertSekToUsd(sek);

        System.out.printf("%.2f SEK = %.2f USD%n", sek, backToUsd);
    }
}
