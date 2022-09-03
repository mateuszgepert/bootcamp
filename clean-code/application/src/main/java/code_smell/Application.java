package code_smell;

import java.io.IOException;

class Application {

    public static void main(String[] args) throws IOException {
        ExchangeRatesProvider exchangeRatesProvider = new ExchangeRatesProvider();
        System.out.println(exchangeRatesProvider.printMoney("XXX"));

    }
}
