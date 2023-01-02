package com.example.quiz.api;

import com.example.quiz.QuizCreator;
import com.example.quiz.game.Game;
import com.example.quiz.questions.Question;
import com.example.quiz.questions.QuestionType;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.stream.Collectors;

@ShellComponent
public class QuizShellApi {

    private final QuizCreator quizCreator = new QuizCreator();
    private Game game;

    @ShellMethod("Welcome")
    public String start() {
        game = null;
        return "Welcome in quiz available quiz are \n" +
                Arrays.stream(QuestionType.values())
                        .map(QuestionType::name)
                        .collect(Collectors.joining("\n"))
                + "\n\ntype game <QuizType> eg `game COUNTRIES`";
    }

    @ShellMethod("Create game")
    public String game(
            @ShellOption(defaultValue = "TRIVIA") String option,
            @ShellOption(defaultValue = "5") int numberOfQuestions,
            @ShellOption(defaultValue = "2") int answersLimit) {
        game = quizCreator.createGameFor(QuestionType.valueOf(option), numberOfQuestions, answersLimit);
        return game.start()
                .print();

    }


    @ShellMethod("Answer")
    public String answer(String answer) {
        return game.answer(answer)
                .map(Question::print)
                .orElse(game.result());
    }
}