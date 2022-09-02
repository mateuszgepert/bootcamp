package design_patterns.behavioral.observer;

import lombok.extern.java.Log;

import java.util.function.Consumer;

@Log
class EmailSender implements Consumer<ServerEvent> {
    @Override
    public void accept(ServerEvent event) {
        log.info("email with event " + event.getPayload() + " sent");
    }
}
