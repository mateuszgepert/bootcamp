package design_patterns.behavioral.chain_responsibility;

import lombok.Value;

import java.util.Map;

@Value
class Request {

    String initiator;
    String destination;
    Map<String, String> params;

}
