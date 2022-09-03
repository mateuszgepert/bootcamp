package design_patterns.structural.decorator.decorated;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class MeteredThirdPartyClient implements ThirdPartyClient {

    private final ThirdPartyClient delegate;
    private final Metrics metrics;

    @Override
    public String getData() {
        return metrics.meter(delegate::getData);
    }
}
