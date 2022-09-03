package design_patterns.structural.decorator;

import java.util.function.Supplier;

class Metrics {

    <T> T meter(Supplier<T> meteredAction) {
        //meter
        return meteredAction.get();
    }
}
