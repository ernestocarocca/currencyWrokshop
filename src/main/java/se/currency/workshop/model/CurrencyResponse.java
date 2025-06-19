package se.currency.workshop.model;

import java.util.Map;

public class CurrencyResponse {
    private boolean success;
    private String source;
    private Map<String, Double> quotes;

    public boolean isSuccess() { return success; }
    public String getSource() { return source; }
    public Map<String, Double> getQuotes() { return quotes; }
}
