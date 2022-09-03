package design_patterns.structural.facade.services;

import java.util.List;

public class UserRepository {

    public List<User> getUsers() {
        return List.of();
    }

    static class User {
        //user related data
    }
}
