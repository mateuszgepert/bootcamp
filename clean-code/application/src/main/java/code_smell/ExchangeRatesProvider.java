package code_smell;

public class ExchangeRatesProvider {

    private final RatesClient ratesClient;
    private final RateExtractor rateExtractor;

    public ExchangeRatesProvider(RatesClient ratesClient, RateExtractor rateExtractor) {
        this.ratesClient = ratesClient;
        this.rateExtractor = rateExtractor;
    }

    public String printMoney(String currency, double PLN) {
        var rate = ratesClient.getRatesResponse(currency)
                .map(response -> rateExtractor.getRateForCurrency(currency, response))
                .orElse("NOT_FOUND");

        Double amount = null;
        if(!rate.equals("NOT_COUND")) {
            amount = Double.valueOf(rate) * PLN;
        }
        StringBuffer sb = new StringBuffer();
        if(currency.equals("CAD") || currency.equals("USD") || currency.equals("MXN") || currency.equals("JPY")) {
            if (currency.equals("JPY")) {
                sb.append("¥ ");
            } else {
                sb.append("$ ");
            }
            if (amount > 999) {
                char[] s = amount.toString().toCharArray();
                sb.append(s[0]);
                sb.append(",");
                for (int i = 1; i < s.length; i++) {
                    sb.append(s[i]);
                }
            }
            sb.append(amount);
        }
        else if(currency.equals("EUR") || currency.equals("SEK")) {
            if(currency.equals("EUR")) {
                sb.append("€");
            } else {
                //do nothing we want currency symbol in the end
            }
            if(amount < 1000) {
                int dot = 0;
                char[] s = amount.toString().toCharArray();
                for (int i = 0; i < s.length; i++) {
                    if(dot == 3) {
                        break;
                    } else if(dot >= 1){
                        dot++;
                    }
                    if(s[i] == '.') {
                        sb.append(',');
                        dot++;
                    } else {
                        sb.append(s[i]);
                    }
                }
            }
            if(amount > 999) {
                int dot = 0;
                char[] s = amount.toString().toCharArray();
                sb.append(s[0]);
                sb.append(".");
                for (int i = 1; i < s.length; i++) {
                    if(dot == 3) {
                        break;
                    } else if(dot >= 1){
                        dot++;
                    }
                    if(s[i] == '.') {
                        sb.append(',');
                        dot++;
                    } else {
                        sb.append(s[i]);
                    }
                }
            }
            if(currency.equals("SEK")) {
                char[] temp = sb.toString().replace("€", "").toCharArray();
                sb = new StringBuffer();
                for (int i = 0; i < temp.length; i++) {
                    sb.append(temp[i]);
                }
                sb.append(" kr");

            }
        } else {
            return "currency is not supported";
        }
        String resultString = sb.toString();

        if(resultString.contains("USD")) {
            resultString = resultString.replace(" ", "");
        }

        if(!(currency.equals("EUR") || currency.equals("SEK"))) {
            int end = resultString.indexOf(".")+3;
            resultString = resultString.substring(0, end);
        }

        return resultString;
    }

}