package design_patterns.creational.builder;


class UserWithMandatoryFields {

    private final String login;
    private final String password;
    private final String email;

    private final String name;
    private final Integer age;

    public UserWithMandatoryFields(String login, String password, String email, String name, Integer age) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public UserWithMandatoryFields(String login, String password, String email) {
        this(login, password, email, null, null);
    }

    public UserWithMandatoryFields(String login, String password, String email,
                String name) {
        this(login, password, email, name, null);
    }

    public UserWithMandatoryFields(String login, String password, String email,
                Integer age) {
        this(login, password, email, null, age);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
