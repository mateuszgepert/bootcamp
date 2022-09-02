package design_patterns.behavioral.observer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class ServerEvent {

    private String payload;

}
