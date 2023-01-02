package com.example.quiz.game;

import com.example.quiz.questions.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class Game {

    private int numberOfLeftQuestions;
    private final List<Question> questions;
    private final List<Answer> answers;
    private Question currentQuestion;

    public static Game create(List<Question> questions) {
        return new Game(questions, new ArrayList<>());
    }

    public Question start() {
        numberOfLeftQuestions = questions.size() - 1;
        currentQuestion = questions.get(numberOfLeftQuestions);
        numberOfLeftQuestions--;
        return currentQuestion;
    }

    public Optional<Question> answer(String answer) {
        answers.add(
                new Answer(answer, currentQuestion));

        if (gameFinished()) {
            log.trace("Game finished");
            return Optional.empty();
        }
        currentQuestion = questions.get(numberOfLeftQuestions);
        numberOfLeftQuestions--;
        return Optional.of(currentQuestion);
    }

    public String result() {
        if (gameFinished()) {
            return printResult();
        } else {
            return String.format("""
                    GAME NOT FINISHED
                    Questions left %d
                    Answeres sent %d""", numberOfLeftQuestions, answers.size());
        }
    }

    private boolean gameFinished() {
        return numberOfLeftQuestions <= 0 && answers.size() == questions.size();
    }

    private String printResult() {
        return String.format(
                "Score [%d/%d] \n" +
                        "%s", getScore(), questions.size(), summary());

    }

    private long getScore() {
        return answers
                .stream()
                .filter(Answer::wasCorrect)
                .count();
    }

    private String summary() {
        return answers
                .stream()
                .map(Answer::summary)
                .collect(Collectors.joining("\n"));
    }
}
