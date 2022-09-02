package solid.i;

public interface InterfaceSegregationPasswordProvider {

    boolean validatePassword(String password);

    String hashPassword(String password, String salt);

    void savePassword(String id, String password);

    void getPasswordHashFor(String id);
}

//vs

interface PasswordCreator {

    String hashPassword(String password, String salt);

}

interface PasswordValidator {

    boolean validatePassword(String password);
}

interface PasswordRepository {

    void savePassword(String id, String password);

    void getPasswordHashFor(String id);
}
