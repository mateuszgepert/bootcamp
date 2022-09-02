package design_patterns.creational.factory_method;

import design_patterns.creational.factory_method.id.factory.TestIdGeneratorFactory;

public class Application {

    public static void main(String[] args) {
        var service = new CreateUserService();

        var user1 = service.createUser("some name", "test");
        var user2 = service.createUser("some name", "test");
        System.out.println(user1);
        System.out.println(user2);

        var factoryBasedService = new FactoryMethodCreateUserService(new TestIdGeneratorFactory());
        var user3 = factoryBasedService.createUser("name");
        var user4 = factoryBasedService.createUser("name");
        System.out.println(user3);
        System.out.println(user4);

    }

}
