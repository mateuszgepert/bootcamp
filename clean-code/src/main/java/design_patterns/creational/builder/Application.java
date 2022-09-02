package design_patterns.creational.builder;

class Application {

    public static void main(String[] args) {

        //normal User
        var tom = new User();
        tom.setAge(12);
        tom.setEmail("email@example.com");
        tom.setName("tom");
        tom.setPassword("password");
        tom.setLogin("login");

        var karl = new UserWithMandatoryFields("karl", "pass", "em", null, null);

        var john = UserBuilder
                .user()
                .withLogin("john")
                .withPassword("password")
                .withEmail("mail@mail.com")
                .withName("john")
                .build();

        var clark = UserFluentBuilder
                .user()
                .withLogin("clark")
                .withPassword("password")
                .withEmail("email@dot.com")
                .build();

    }
}
