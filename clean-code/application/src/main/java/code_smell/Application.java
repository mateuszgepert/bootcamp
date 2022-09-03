package code_smell;

import code_smell.currency.CurrencyPrinter;
import code_smell.currency.formatter.LocaleAwareAmountFormatter;
import code_smell.currency.gateway.ExchangeRatesClient;
import code_smell.currency.money.MoneyCalculator;

class Application {

    public static void main(String[] args) {
        CurrencyPrinter exchangeRatesProvider = new CurrencyPrinter(
                ExchangeRatesClient.create(),
                new MoneyCalculator(),
                new LocaleAwareAmountFormatter());
        System.out.println(exchangeRatesProvider.printMoney("EUR", 222));
    }
}
