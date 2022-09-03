package code_smell.exchange_rates;

import java.io.IOException;

public class ExchangeRatesProvider {


    public String printMoney(String cur, double PLN) throws IOException {
        return null;
    }

    public static void main(String[] args) throws IOException {
        ExchangeRatesProvider exchangeRatesProvider = new ExchangeRatesProvider();
        System.out.println(exchangeRatesProvider.printMoney("EUR", 23453452));
    }
}
