package com.example.quiz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.Unirest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

@ShellComponent
public class Game {

    List<QuestionDTO> questions;
    QuestionDTO currentQuestion;
    int count = 5;

    @ShellMethod("Starts game")
    public String start() {
        questions = new Gson().fromJson(
                Unirest.get("https://the-trivia-api.com/api/questions")
                        .queryString("limit", 5)
                        .asJson()
                        .getBody()
                        .getArray()
                        .toString(), new TypeToken<List<QuestionDTO>>() {
                }.getType());

        questions = questions
                .stream()
                .map(question -> {
                    question.incorrectAnswers.add(question.correctAnswer);
                    return question;
                })
                .collect(Collectors.toList());

        for (int i = 0; i < questions.size(); i++) {
            var question = questions.get(i);
            question.answers = question.incorrectAnswers;
        }
        currentQuestion = questions.get(--count);
        final char[] a = {'a'};
        System.out.println(questions.stream().map(q -> q.question + " " + q.correctAnswer).collect(Collectors.joining("\n")));
        return String.format("Q: %s\n%s", currentQuestion.question, currentQuestion.answers.stream().map(answer -> {
            if (a[0] == 'a') {
                var t = "[" + a[0] + "]" + " " + answer;
                a[0]++;
                return t;
            }
            return "[" + a[0]++ + "]" + " " + answer;
        }).collect(Collectors.joining("\n")));

    }

    @ShellMethod("Answer a")
    public String A() {
        currentQuestion.answer = currentQuestion.answers.get(0);
        currentQuestion = questions.get(--count);
        //end game
        if (count <= 0) {
            return "FINISHED!!!" +
                    lineSeparator() +
                    result() +
                    lineSeparator() +
                    "to play again type [start]";
        } else {
            final char[] a = {'a'};
            return String.format("Q: %s\n%s", currentQuestion.question, currentQuestion.answers.stream().map(answer -> {
                if (a[0] == 'a') {
                    var t = "[" + a[0] + "]" + " " + answer;
                    a[0]++;
                    return t;
                }
                return "[" + a[0]++ + "]" + " " + answer;
            }).collect(Collectors.joining("\n")));
        }
    }

    private String result() {
        var score = questions
                .stream()
                .filter(question -> question.correctAnswer.equals(question.answer))
                .count();
        return String.format("Score [%d/20]\n" +
                "%s", score, questions.stream().map(question -> String.format("Question: %s\nAnswer: %s\n Your answer: %s", question.question, question.correctAnswer, question.answer)).collect(Collectors.joining("\n")));
    }

    @ShellMethod("Answer b")
    public String B() {
        currentQuestion.answer = currentQuestion.answers.get(1);
        currentQuestion = questions.get(--count);
        //end game
        if (count <= 0) {
            return "FINISHED!!!" +
                    lineSeparator() +
                    result() +
                    lineSeparator() +
                    "to play again type [start]";
        } else {
            final char[] a = {'a'};
            return String.format("Q: %s\n%s", currentQuestion.question, currentQuestion.answers.stream().map(answer -> {
                if (a[0] == 'a') {
                    var t = "[" + a[0] + "]" + " " + answer;
                    a[0]++;
                    return t;
                }
                return "[" + a[0]++ + "]" + " " + answer;
            }).collect(Collectors.joining("\n")));
        }
    }

    @ShellMethod("Answer c")
    public String C() {
        currentQuestion.answer = currentQuestion.answers.get(2);
        currentQuestion = questions.get(--count);
        final char[] a = {'a'};
        return String.format("Q: %s\n%s", currentQuestion.question, currentQuestion.answers.stream().map(answer -> {
            if (a[0] == 'a') {
                var t = "[" + a[0] + "]" + " " + answer;
                a[0]++;
                return t;
            }
            return "[" + a[0]++ + "]" + " " + answer;
        }).collect(Collectors.joining("\n")));
    }

    @ShellMethod("Answer d")
    public String D() {
        currentQuestion.answer = currentQuestion.answers.get(3);
        currentQuestion = questions.get(--count);
        if (count <= 0) {
            return "FINISHED!!!" +
                    lineSeparator() +
                    result() +
                    lineSeparator() +
                    "to play again type [start]";
        } else {
            final char[] a = {'a'};
            return String.format("Q: %s\n%s", currentQuestion.question, currentQuestion.answers.stream().map(answer -> {
                if (a[0] == 'a') {
                    var t = "[" + a[0] + "]" + " " + answer;
                    a[0]++;
                    return t;
                }
                return "[" + a[0]++ + "]" + " " + answer;
            }).collect(Collectors.joining("\n")));
        }
    }


}
