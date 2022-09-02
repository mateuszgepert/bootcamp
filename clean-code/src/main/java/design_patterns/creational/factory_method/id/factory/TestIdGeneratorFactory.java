package design_patterns.creational.factory_method.id.factory;

import design_patterns.creational.factory_method.id.IdGenerator;
import design_patterns.creational.factory_method.id.IncrementalIdGenerator;

public class TestIdGeneratorFactory implements IdGeneratorFactory {

    private IdGenerator generator = new IncrementalIdGenerator();

    @Override
    public IdGenerator create() {
        return generator;
    }

}
