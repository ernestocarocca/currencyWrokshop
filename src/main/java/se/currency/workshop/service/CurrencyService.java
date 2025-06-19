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

    public double convertEuroToSek(double amountInEuro) throws IllegalAccessException {
        if (currencyResponse == null || currencyResponse.getQuotes() == null) {
            throw new IllegalStateException("Currency data is missing.");
        }
        Double usdSek = currencyResponse.getQuotes().get("USDSEK");
        Double usdEur = currencyResponse.getQuotes().get("USDEUR");
        if (usdSek == null || usdEur == null) {
            throw new IllegalArgumentException("Missing exchange rate for USDSEK or USDEUR.");
        }

        double eurSek = usdSek / usdEur;
        return amountInEuro * eurSek;
}
}
