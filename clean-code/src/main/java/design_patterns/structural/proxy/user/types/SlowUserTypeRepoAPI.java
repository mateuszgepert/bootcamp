package design_patterns.structural.proxy.user.types;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SlowUserTypeRepoAPI {

    private final Map<String, String> DATABASE = new HashMap<>();

    public String getUserType(String id) {

        //perform some heavy logic operations to get the required user type

        try {
            Thread.sleep(new Random().nextInt());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return DATABASE.getOrDefault(id, "empty");
    }
}
