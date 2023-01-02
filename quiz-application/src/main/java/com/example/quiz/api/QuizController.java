package com.example.quiz.api;

import com.example.quiz.QuizCreator;
import com.example.quiz.game.Game;
import com.example.quiz.game.GameRepository;
import com.example.quiz.questions.QuestionType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class QuizController {

    private final QuizCreator quizCreator;
    private final GameRepository gameRepository;

    @PostMapping("/game")
    public ResponseEntity<CreateGameResponse> createGame(@Valid @RequestBody CreateGameRequest request) {
        var game = quizCreator.createGameFor(request.gameType(), request.numberOfQuestions(), request.numberOfAnswers());
        var newGameId = gameRepository.addGame(game);
        return ResponseEntity.ok(new CreateGameResponse(newGameId.toString()));
    }

    @PostMapping("/game/{gameId}/start")
    public ResponseEntity<GameQuestion> start(@PathVariable UUID gameId) {
        return gameRepository
                .getGameFor(gameId)
                .map(Game::start)
                .map(question -> new GameQuestion(question.print(), gameId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/game/{gameId}/answer")
    public ResponseEntity<?> nextQuestion(@PathVariable UUID gameId, @Valid @RequestBody GameQuestionAnswer answer) {
        var game = gameRepository
                .getGameFor(gameId);
        if (game.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var nextQuestion = game.get().answer(answer.answer());
        if (nextQuestion.isEmpty()) {
            return ResponseEntity.ok(new GameResult(game.get().result()));
        }
        return ResponseEntity.ok(new GameQuestion(nextQuestion.get().print(), gameId));
    }

    @GetMapping("/game/{gameId}/result")
    public ResponseEntity<GameResult> result(@PathVariable UUID gameId) {
        return gameRepository
                .getGameFor(gameId)
                .map(Game::result)
                .map(GameResult::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    record CreateGameRequest(
            @NotNull QuestionType gameType,
            @Max(50) Integer numberOfQuestions,
            @Max(6) Integer numberOfAnswers) {
    }

    record CreateGameResponse(String gameId) {

    }

    record GameQuestion(String question, UUID gameId) {

    }

    record GameQuestionAnswer(@NotEmpty String answer) {

    }

    record GameResult(String result) {

    }

}
