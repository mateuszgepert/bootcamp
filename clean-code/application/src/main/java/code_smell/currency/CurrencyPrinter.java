package code_smell.currency;

import code_smell.currency.formatter.LocaleAwareAmountFormatter;
import code_smell.currency.gateway.ExchangeRatesClient;
import code_smell.currency.money.MoneyCalculator;

public class CurrencyPrinter {

    private final ExchangeRatesClient exchangeRatesClient;
    private final MoneyCalculator moneyCalculator;
    private final LocaleAwareAmountFormatter formatter;

    public CurrencyPrinter(ExchangeRatesClient exchangeRatesClient, MoneyCalculator moneyCalculator, LocaleAwareAmountFormatter formatter) {
        this.exchangeRatesClient = exchangeRatesClient;
        this.moneyCalculator = moneyCalculator;
        this.formatter = formatter;
    }

    public String printMoney(String currency, double PLN) {
        var rate = exchangeRatesClient.getRate(currency)
                .orElse("NOT_FOUND");

        var amount = moneyCalculator.calculate(PLN, rate);

        return formatter.format(amount, currency);
    }

}