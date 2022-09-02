package solid.i;

public interface PasswordProvider {

//    boolean validatePassword(String password);
//
//    String hashPassword(String password, String salt);

    void savePassword(String id, String password);

    void getPasswordHashFor(String id);
}
