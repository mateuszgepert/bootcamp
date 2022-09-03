package design_patterns.structural.proxy.user.proxy.types;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class CachedUserTypeRepository implements UserTypeRepository {

    private final SlowUserTypeRepoAPI api;
    private final Map<String, String> cache = new ConcurrentHashMap<>();


    @Override
    public String getUserType(String id) {
        return cache.computeIfAbsent(id, (key) -> api.getUserType(id));
    }
}
