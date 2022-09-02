package design_patterns.creational.abstract_factory.ftp;


import design_patterns.creational.abstract_factory.Connection;

public class FtpConnection implements Connection {

    @Override
    public int getPort() {
        return 50;
    }

}
