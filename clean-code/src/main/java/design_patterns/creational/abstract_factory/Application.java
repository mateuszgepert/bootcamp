package design_patterns.creational.abstract_factory;


import design_patterns.creational.abstract_factory.ftp.FtpConnectionFactory;

public class Application {

    public static void main(String[] args) {
        var connectionFactory = new FtpConnectionFactory();
        var service = new Service(connectionFactory);
        //------------------------------------------------------------------
        service.run();
    }

}
