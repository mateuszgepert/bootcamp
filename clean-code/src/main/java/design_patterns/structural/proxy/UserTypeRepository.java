package design_patterns.structural.proxy;

import java.util.Optional;

public interface UserTypeRepository {
    Optional<String> getUserType(String id);
}
