package design_patterns.structural.proxy;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
class UserDataQuery {

    private final UserAttributesRepository attributesRepository;
    private final UserTypeRepository userTypeRepoAPi;

    Optional<User> getUserFor(String id) {
        return userTypeRepoAPi
                .getUserType(id)
                .map(type -> new User(
                        id,
                        type,
                        attributesRepository.findAttributesFor(id)
                ));
    }

    @Value
    static class User {
        String id;
        String type;
        Map<String, String> attributes;
    }
}
