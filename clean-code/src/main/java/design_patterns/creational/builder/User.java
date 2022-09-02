package design_patterns.creational.builder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class User {

    private String login;
    private String password;
    private String email;

    private String name;
    private Integer age;
}