package code_smell.currency.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyCalculator {

    public BigDecimal calculate(double amount, String rate) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(rate)).setScale(2, RoundingMode.DOWN);
    }
}
