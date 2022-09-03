package design_patterns.behavioral.strategy.enum_based;

import java.math.BigDecimal;
import java.util.List;

enum ParcelType {

    PRIORITY {
        @Override
        BigDecimal calculateFor(WeightRange weightRange) {
            if (List.of(WeightRange.MEDIUM, WeightRange.SMALL).contains(weightRange)) {
                return BigDecimal.valueOf(100);
            } else {
                return BigDecimal.valueOf(200);
            }
        }
    },
    REGULAR {
        @Override
        BigDecimal calculateFor(WeightRange weightRange) {
            if (WeightRange.MAX == weightRange) {
                return BigDecimal.valueOf(50);
            } else if (WeightRange.MEDIUM == weightRange) {
                return BigDecimal.valueOf(45);
            } else {
                return BigDecimal.valueOf(30);
            }
        }
    },
    LOW_PRIORITY{
        @Override
        BigDecimal calculateFor(WeightRange weightRange) {
            return BigDecimal.valueOf(30);
        }
    };

    ParcelType() {
    }

    abstract BigDecimal calculateFor(WeightRange weightRange);
}
