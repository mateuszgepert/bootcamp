package design_patterns.behavioral.strategy;

import java.math.BigDecimal;
import java.util.List;

class ParcelPriceCalculator {

    BigDecimal calculatePrice(ParcelType type, WeightRange weightRange) {
        if (type == ParcelType.PRIORITY) {
            if (List.of(WeightRange.MEDIUM, WeightRange.SMALL).contains(weightRange)) {
                return BigDecimal.valueOf(100);
            } else {
                return BigDecimal.valueOf(200);
            }
        } else if (type == ParcelType.REGULAR) {
            if (WeightRange.MAX == weightRange) {
                return BigDecimal.valueOf(50);
            } else if (WeightRange.MEDIUM == weightRange) {
                return BigDecimal.valueOf(45);
            } else {
                return BigDecimal.valueOf(30);
            }
        } else {
            return BigDecimal.valueOf(30);
        }
    }
}
