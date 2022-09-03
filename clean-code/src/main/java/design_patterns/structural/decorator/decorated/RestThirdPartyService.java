package design_patterns.structural.decorator.decorated;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class RestThirdPartyService implements ThirdPartyClient {

    public String getData() {
        return longRunGetData();

    }

    private String longRunGetData() {
        return "data";
    }
}
