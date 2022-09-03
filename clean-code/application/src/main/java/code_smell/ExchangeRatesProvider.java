package code_smell;

public class ExchangeRatesProvider {

    private final RatesClient ratesClient;
    private final RateExtractor rateExtractor;

    public ExchangeRatesProvider(RatesClient ratesClient, RateExtractor rateExtractor) {
        this.ratesClient = ratesClient;
        this.rateExtractor = rateExtractor;
    }

    public String printMoney(String currency) {
        return ratesClient.getRatesResponse(currency)
                .map(response -> rateExtractor.getRateForCurrency(currency, response))
                .orElse("NOT_FOUND");
    }

}