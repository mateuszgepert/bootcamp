package design_patterns.creational.abstract_factory.ftp;


import design_patterns.creational.abstract_factory.SecuredConnection;

public class SftpConnection implements SecuredConnection {

    @Override
    public int getPort() {
        return 22;
    }

    @Override
    public String getEncryptionAlgorithm() {
        return "AES";
    }

}
