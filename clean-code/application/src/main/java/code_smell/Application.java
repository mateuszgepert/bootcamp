package code_smell;

import java.io.IOException;

class Application {

    public static void main(String[] args) throws IOException {
        ExchangeRatesProvider exchangeRatesProvider = new ExchangeRatesProvider(new RatesClient(), new RateExtractor());
        System.out.println(exchangeRatesProvider.printMoney("XXX"));

    }
}
