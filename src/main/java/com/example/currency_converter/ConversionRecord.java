package com.example.currency_converter;

public class ConversionRecord {
    private String fromCurrency;
    private String toCurrency;
    private  double amount;
    private  double result;

    public ConversionRecord(String fromCurrency,String toCurrency, double amount, double result) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.result = result;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }
    public String getToCurrency() {
        return toCurrency;
    }
    public double getAmount() {
        return amount;
    }
    public double getResult() {
        return result;
    }

}
