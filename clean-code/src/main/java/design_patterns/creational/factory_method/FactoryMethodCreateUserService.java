package design_patterns.creational.factory_method;

import design_patterns.creational.factory_method.id.factory.IdGeneratorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class FactoryMethodCreateUserService {

    private final IdGeneratorFactory idGeneratorFactory;

    public User createUser(String name) {
        var generator = idGeneratorFactory.create();
        return new User(generator.getNext(), name);
    }
}
