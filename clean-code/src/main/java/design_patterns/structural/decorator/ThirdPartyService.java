package design_patterns.structural.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class ThirdPartyService {

    private final Metrics metrics;

    //issue add metrics
    public String getData() {
        //detailed logic
        return metrics.meter(() -> longRunGetData());

    }

    private String longRunGetData() {
        return "data";
    }
}
