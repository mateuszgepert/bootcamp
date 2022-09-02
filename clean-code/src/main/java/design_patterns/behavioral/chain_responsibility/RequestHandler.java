package design_patterns.behavioral.chain_responsibility;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class RequestHandler {

    private final RequestRepository requestRepository;

    void handle(Request request) {
        if (!"INTERNAL".equals(request.getInitiator())) {
            //skip authentication
            if (authenticated(request)) {
                return;
            }
        }
        if (!request.getParams().containsKey("email")) {
            logRequest(request);
        }
        if ("third-party".equals(request.getDestination())) {
            storeRequest(request);
        }
        processRequest(request);
    }

    private void processRequest(Request request) {
        //business logic processing request
    }

    private void storeRequest(Request request) {
        requestRepository.store(request);
    }

    private boolean authenticated(Request request) {
        return List.of("user", "external-service", "admin").contains(request.getInitiator());
    }

    private void logRequest(Request request) {
        System.out.println(request);
    }

}
