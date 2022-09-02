package design_patterns.behavioral.chain_responsibility;

import java.util.ArrayList;
import java.util.List;

class RequestRepository {

    List<Request> requests = new ArrayList<>();

    void store(Request request) {
        requests.add(request);
    }
}
