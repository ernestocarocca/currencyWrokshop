package se.currency.workshop.service;

import com.google.gson.Gson;
import se.currency.workshop.CurrencyPairs;
import se.currency.workshop.api.FetchCurrency;
import se.currency.workshop.model.CurrencyResponse;

import java.util.Locale;

public class CurrencyService {
    private CurrencyResponse currencyResponse;
    public CurrencyService() throws Exception {
        // fetch from API when service starts
        String json = FetchCurrency.fetch();
        this.currencyResponse = new Gson().fromJson(json, CurrencyResponse.class);

        if (!currencyResponse.isSuccess()) {
            throw new RuntimeException("Failed to load currency data from API");
        }
    }




    private double getRate(String pair) {
        Double rate = currencyResponse.getQuotes().get(pair);
        if (rate == null) {
            throw new IllegalArgumentException("Missing rate for pair: " + pair);
        }
        return rate;
    }

    /**
     * General conversion method, from any currency to any other (SEK, USD, EUR).
     */

    public double convert(String from, String to, double amount) {
        from = from.toUpperCase();
        to = to.toUpperCase();

        if (from.equals(to)) {
            return amount;
        }

        if (from.equals("USD")) {
            return amount * getRate("USD" + to);
        }

        if (to.equals("USD")) {
            return amount / getRate("USD" + from);
        }

        double usdAmount = amount / getRate("USD" + from);
        return usdAmount * getRate("USD" + to);
    }
    public double convert(double amount, String toCurrency) throws IllegalAccessException {
        return convert("USD", toCurrency, amount);
    }
    public double convert(double amount) throws IllegalAccessException {
        return convert("SEK", "USD", amount);
    }
}
