package design_patterns.creational.abstract_factory.http;


import design_patterns.creational.abstract_factory.Connection;
import design_patterns.creational.abstract_factory.ConnectionAbstractFactory;
import design_patterns.creational.abstract_factory.SecuredConnection;

public class HttpConnectionFactory implements ConnectionAbstractFactory {

    @Override
    public Connection createConnection() {
        return new HttpConnection();
    }

    @Override
    public SecuredConnection createSecuredConnection() {
        return new HttpsConnection();
    }

}
