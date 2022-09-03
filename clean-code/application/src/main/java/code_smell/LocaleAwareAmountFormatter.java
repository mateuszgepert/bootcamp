package code_smell;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

class LocaleAwareAmountFormatter {
    public String format(BigDecimal amount, String currency) {
        var localeWithCurrency = getLocaleAndSymbolFor(currency);
        var formatting = NumberFormat.getInstance(localeWithCurrency.locale);
        return appendSymbol(formatting.format(amount), localeWithCurrency);
    }

    private String appendSymbol(String amountFormatted, LocalWithCurrency localeWithCurrency) {
        if (List.of("CAD", "JPY").contains(localeWithCurrency.currency)) {
            return String.format("%s %s", localeWithCurrency.symbol, amountFormatted);
        }
        return String.format("%s%s", localeWithCurrency.symbol, amountFormatted);
    }


    private LocalWithCurrency getLocaleAndSymbolFor(String currency) {
        return switch(currency) {
            case "CAD" -> new LocalWithCurrency(Locale.CANADA, "$", "CAD");
            case "USD" -> new LocalWithCurrency(Locale.GERMANY, "$", "USD");
            case "JPY" -> new LocalWithCurrency(Locale.GERMANY, "¥", "JPY");
            default -> new LocalWithCurrency(Locale.GERMANY, "€", "EUR");
        };
    }
    static class LocalWithCurrency {
        Locale locale;
        String symbol;
        String currency;

        public LocalWithCurrency(Locale locale, String symbol, String currency) {
            this.locale = locale;
            this.symbol = symbol;
            this.currency = currency;
        }

    }
}
