package design_patterns.structural.facade;

import java.util.List;

class UserRepository {

    List<User> getUsers() {
        return List.of();
    }

    static class User {
        //user related data
    }
}
