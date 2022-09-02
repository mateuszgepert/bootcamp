package design_patterns.creational.abstract_factory.http;


import design_patterns.creational.abstract_factory.SecuredConnection;

public class HttpsConnection implements SecuredConnection {

    @Override
    public int getPort() {
        return 443;
    }

    @Override
    public String getEncryptionAlgorithm() {
        return "AES";
    }

}
