package code_smell;

public class ExchangeRatesProvider {

    private final RatesClient ratesClient;
    private final RateExtractor rateExtractor;
    private final MoneyCalculator moneyCalculator;
    private final LocaleAwareAmountFormatter formatter;

    public ExchangeRatesProvider(RatesClient ratesClient, RateExtractor rateExtractor, MoneyCalculator moneyCalculator, LocaleAwareAmountFormatter formatter) {
        this.ratesClient = ratesClient;
        this.rateExtractor = rateExtractor;
        this.moneyCalculator = moneyCalculator;
        this.formatter = formatter;
    }

    public String printMoney(String currency, double PLN) {
        var rate = ratesClient.getRatesResponse(currency)
                .map(response -> rateExtractor.getRateForCurrency(currency, response))
                .orElse("NOT_FOUND");

        var amount = moneyCalculator.calculate(PLN, rate);
        return formatter.format(amount, currency);
    }

}