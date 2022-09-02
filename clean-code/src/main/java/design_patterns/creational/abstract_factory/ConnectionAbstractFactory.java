package design_patterns.creational.abstract_factory;

public interface ConnectionAbstractFactory {

    Connection createConnection();

    SecuredConnection createSecuredConnection();

}
