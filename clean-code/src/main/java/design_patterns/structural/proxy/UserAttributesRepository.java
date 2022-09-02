package design_patterns.structural.proxy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class UserAttributesRepository {

    private final Map<String, Map<String, String>> DATABASE = new HashMap<>();

    Map<String, String> findAttributesFor(String id) {
        return DATABASE.getOrDefault(id, Collections.emptyMap());
    }
}
