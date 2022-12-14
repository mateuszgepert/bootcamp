package design_patterns.creational.factory_method.id;

import java.util.UUID;

public class UuidGenerator implements IdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
