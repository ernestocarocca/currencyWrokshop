package se.currency.workshop.service;

import se.currency.workshop.model.CurrencyResponse;

public class CurrencyService {
    private CurrencyResponse currencyResponse;

    public void setCurrencyResponse(CurrencyResponse response) {
        this.currencyResponse = response;
    }

    public double convertUsdToSek(double amountUsd) throws IllegalAccessException {
        if (currencyResponse == null || currencyResponse.getQuotes() == null) {
            throw new IllegalAccessException("KursData saknas");
        }

        Double rate = currencyResponse.getQuotes().get("USDSEK");
        if (rate == null) {
            throw new IllegalArgumentException("Kurs för USDSEK saknas");
        }

        return amountUsd * rate;
    }

    public double convertSekToUsd(double amountSek) throws IllegalAccessException {
        if (currencyResponse == null || currencyResponse.getQuotes() == null) {
            throw new IllegalAccessException("KursData saknas");
        }

        Double rate = currencyResponse.getQuotes().get("USDSEK");
        if (rate == null) {
            throw new IllegalArgumentException("Kurs för USDSEK saknas");
        }

        return amountSek / rate;
    }
}
