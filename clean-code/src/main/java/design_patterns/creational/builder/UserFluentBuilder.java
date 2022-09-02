package design_patterns.creational.builder;

import static java.util.Objects.isNull;

public class UserFluentBuilder implements UserLoginBuilder, UserPasswordBuilder,
        UserEmailBuilder, UserOptionalFieldsBuilder {

    private String login;
    private String password;
    private String email;

    private String name;
    private Integer age;

    public static UserLoginBuilder user() {
        return new UserFluentBuilder();
    }

    @Override
    public UserPasswordBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public UserEmailBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public UserOptionalFieldsBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserOptionalFieldsBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UserOptionalFieldsBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public UserWithMandatoryFields build() {
        return new UserWithMandatoryFields(login, password, email, name, age);
    }

    private void validateRequiredField(String fieldName, String value) {
        if(isNull(value) || value.isEmpty()) {
            throw new IllegalStateException("Invalid state, field [" +
                    fieldName + "] may not be null or empty.");
        }
    }

}

interface UserLoginBuilder {

    UserPasswordBuilder withLogin(String login);

}

interface UserPasswordBuilder {

    UserEmailBuilder withPassword(String password);

}

interface UserEmailBuilder {

    UserOptionalFieldsBuilder withEmail(String email);

}

interface UserOptionalFieldsBuilder {

    UserOptionalFieldsBuilder withName(String name);

    UserOptionalFieldsBuilder withAge(Integer age);

    UserWithMandatoryFields build();

}