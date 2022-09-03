package design_patterns.structural.proxy.user.proxy.types;

import java.util.HashMap;
import java.util.Map;

class SlowUserTypeRepoAPI {

    private final Map<String, String> DATABASE = new HashMap<>();

    public String getUserType(String id) {

        //perform some heavy logic operations to get the required user type

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return DATABASE.getOrDefault(id, "type");
    }
}
