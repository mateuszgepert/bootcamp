package design_patterns.creational.abstract_factory.ftp;


import design_patterns.creational.abstract_factory.Connection;
import design_patterns.creational.abstract_factory.ConnectionAbstractFactory;
import design_patterns.creational.abstract_factory.SecuredConnection;

public class FtpConnectionFactory implements ConnectionAbstractFactory {

    @Override
    public Connection createConnection() {
        return new FtpConnection();
    }

    @Override
    public SecuredConnection createSecuredConnection() {
        return new SftpConnection();
    }

}
