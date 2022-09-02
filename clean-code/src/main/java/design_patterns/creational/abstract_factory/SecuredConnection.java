package design_patterns.creational.abstract_factory;

public interface SecuredConnection extends Connection {

    String getEncryptionAlgorithm();

}
