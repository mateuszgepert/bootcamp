package design_patterns.creational.builder;

import static java.util.Objects.isNull;

public class UserBuilder {

    private String login;
    private String password;
    private String email;

    private String name;
    private Integer age;

    public static UserBuilder user() {
        return new UserBuilder();
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public UserWithMandatoryFields build() {
//        validate();
        return new UserWithMandatoryFields(login, password, email, name, age);
    }

    private void validate() {
        validateRequiredField("login", login);
        validateRequiredField("password", password);
        validateRequiredField("email", email);
    }

    private void validateRequiredField(String fieldName, String value) {
        if(isNull(value) || value.isEmpty()) {
            throw new IllegalStateException("Invalid state, field [" +
                    fieldName + "] may not be null or empty.");
        }
    }

}