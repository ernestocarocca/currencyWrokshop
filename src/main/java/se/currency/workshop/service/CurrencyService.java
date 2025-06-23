package se.currency.workshop.service;

import se.currency.workshop.CurrencyPairs;
import se.currency.workshop.model.CurrencyResponse;

import java.util.Locale;

public class CurrencyService {
    private CurrencyResponse currencyResponse;

    public void setCurrencyResponse(CurrencyResponse response) {
        this.currencyResponse = response;
    }

    private double getRate(String pair) throws IllegalAccessException {
        if (currencyResponse == null || currencyResponse.getQuotes() == null) {
            throw new IllegalAccessException("kursdata saknas");
        }
        Double rate = currencyResponse.getQuotes().get(pair);
        if (rate == null) {
            throw new IllegalAccessException("kurs för " + pair + "saknas");
        }
        return currencyResponse.getQuotes().get(pair);

    }
    /**
     * Övergripande konverteringsmetod, från valfri valuta till valfri annan (SEK, USD, EUR).
     */
    public  double convert( String from , String to, double amount) throws IllegalAccessException{
   from = from.toUpperCase();
   to = to.toUpperCase();
   if (from.equals(to)){
       return amount;
   }
        if (from.equals("USD")) {
            return amount * getRate("USD" + to);
        }
        if (to.equals("USD")) {
            return amount / getRate("USD" + from);
        }
        double amountInUsd = amount / getRate("USD" + from);
        return amountInUsd * getRate("USD" + to);
    }
    public double convert(double amount, String toCurrency) throws IllegalAccessException {
        return convert("USD", toCurrency, amount);
    }
    public double convert(double amount) throws IllegalAccessException {
        return convert("SEK", "USD", amount);
    }
}
