package design_patterns.structural.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class SlowUserTypeRepoAPi implements UserTypeRepository {

    private final Map<String, String> DATABASE = new HashMap<>();

    @Override
    public Optional<String> getUserType(String id) {

        //perform some heavy logic operations to get the required user type

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.of(DATABASE.get(id));
    }
}
