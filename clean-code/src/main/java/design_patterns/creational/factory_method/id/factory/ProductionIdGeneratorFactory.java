package design_patterns.creational.factory_method.id.factory;

import design_patterns.creational.factory_method.id.IdGenerator;
import design_patterns.creational.factory_method.id.UuidGenerator;

public class ProductionIdGeneratorFactory implements IdGeneratorFactory {

    private final UuidGenerator uuidGenerator = new UuidGenerator();

    @Override
    public IdGenerator create() {
        return uuidGenerator;
    }

}
