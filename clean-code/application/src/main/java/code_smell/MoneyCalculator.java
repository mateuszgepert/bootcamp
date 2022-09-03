package code_smell;

import java.math.BigDecimal;
import java.math.RoundingMode;

class MoneyCalculator {

    BigDecimal calculate(double amount, String rate) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(rate)).setScale(2, RoundingMode.DOWN);
    }
}
