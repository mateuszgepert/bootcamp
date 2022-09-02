package design_patterns.creational.abstract_factory.http;


import design_patterns.creational.abstract_factory.Connection;

public class HttpConnection implements Connection {

    @Override
    public int getPort() {
        return 80;
    }

}
