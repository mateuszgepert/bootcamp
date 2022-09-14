package com.example.quiz.game;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class GameRepository {

    Map<UUID, Game> games = new HashMap<>();

    public UUID addGame(Game game) {
        var id = UUID.randomUUID();
        games.put(id, game);
        return id;
    }

    public Optional<Game> getGameFor(UUID gameId) {
        return Optional.of(games.get(gameId));
    }

    public void save(UUID id, Game game) {
        games.put(id, game);
    }
}
