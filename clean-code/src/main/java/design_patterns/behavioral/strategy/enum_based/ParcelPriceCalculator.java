package design_patterns.behavioral.strategy.enum_based;

import java.math.BigDecimal;

class ParcelPriceCalculator {

    BigDecimal calculatePrice(ParcelType type, WeightRange weightRange) {
        return type.calculateFor(weightRange);
    }
}
