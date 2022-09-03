package design_patterns.structural.proxy.user.attirbutes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserAttributesRepository {

    private final Map<String, Map<String, String>> DATABASE = new HashMap<>();

    public Map<String, String> findAttributesFor(String id) {
        return DATABASE.getOrDefault(id, Collections.emptyMap());
    }
}
