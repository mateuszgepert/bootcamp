package design_patterns.structural.proxy;

import design_patterns.structural.proxy.user.attirbutes.UserAttributesRepository;
import design_patterns.structural.proxy.user.types.SlowUserTypeRepoAPI;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Map;

@AllArgsConstructor
class UserDataQuery {

    private final UserAttributesRepository attributesRepository;
    private final SlowUserTypeRepoAPI userTypeRepoAPi;

    User getUserFor(String id) {
        return new User(
                id,
                userTypeRepoAPi.getUserType(id),
                attributesRepository.findAttributesFor(id)
        );
    }

    @Value
    static class User {
        String id;
        String type;
        Map<String, String> attributes;
    }
}
