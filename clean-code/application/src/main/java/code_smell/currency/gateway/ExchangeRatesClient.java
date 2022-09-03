package code_smell.currency.gateway;

import java.util.Optional;

public class ExchangeRatesClient {

    private final RatesClient ratesClient;
    private final RateExtractor rateExtractor;

    public ExchangeRatesClient(RatesClient ratesClient, RateExtractor rateExtractor) {
        this.ratesClient = ratesClient;
        this.rateExtractor = rateExtractor;
    }

    public Optional<String> getRate(String currency) {
        return ratesClient.getRatesResponse(currency)
                .flatMap(response -> rateExtractor.getRateForCurrency(currency, response));
    }

    public static ExchangeRatesClient create() {
        return new ExchangeRatesClient(new RatesClient(), new RateExtractor());
    }
}
