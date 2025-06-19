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
        // Meny
        System.out.println("Växelkurser från " + response.getSource());
        System.out.println("Exempel: USD till SEK eller SEK till USD");
        System.out.print("Ange från-valuta (t.ex. USD): ");
        String from = scanner.nextLine().trim().toUpperCase();

        System.out.print("Ange till-valuta (t.ex. SEK): ");
        String to = scanner.nextLine().trim().toUpperCase();

        System.out.print("Ange belopp: ");
        double amount = scanner.nextDouble();
        double sek = service.convertUsdToSek(amount);
        double backToUsd = service.convertSekToUsd(sek);
        System.out.printf("%.2f SEK = %.2f USD%n", sek, backToUsd);
    }
}
