package design_patterns.structural.decorator.decorated;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Logging {

    public void logData(String data) {
        //log data
        if(data.isEmpty()) {
            log.warn("EMPTY DATA");
        } else {
            log.info("RECIVED DATA {}", data);
        }
    }
}
