package se.currency.workshop.utils;

public class CurrencyUtil {
    public  static  String formatCurrency(double amount, String currencyCode){
        return  String.format("!%.2f", amount, currencyCode);
    }
}
