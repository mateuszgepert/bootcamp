package design_patterns.creational.factory_method;

import design_patterns.creational.factory_method.id.IncrementalIdGenerator;
import design_patterns.creational.factory_method.id.UuidGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class CreateUserService {

    public User createUser(String name, String env) {
        if ("test".equals(env)) {
            return new User(new IncrementalIdGenerator().getNext(), name);
        } else if ("live".equals(env)) {
            return new User(new UuidGenerator().getNext(), name);
        }
        throw new IllegalArgumentException(String.format("Invalid env %s", env));
    }

}
