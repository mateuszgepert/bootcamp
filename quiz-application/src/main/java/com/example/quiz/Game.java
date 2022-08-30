package com.example.quiz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;
import static java.util.Objects.isNull;

@ShellComponent
public class Game {

    private static final List<String> OPTIONS = List.of("trivia", "countries");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    List<QuestionDTO> questions;
    QuestionDTO currentQuestion;
    int count = 5;
    Integer options;
    char quizType;

    @ShellMethod("Starts game")
    public String start(@ShellOption(defaultValue = "") String option, @ShellOption(defaultValue = "") String answerOptions) throws JsonProcessingException {
        if(count != 5) {
            count = 5;
        }
        //add support for single letter menu option #jira QUIZ-334
        if (option.isBlank() ||
                (isNull(OPTIONS.stream().filter(o -> o.equalsIgnoreCase(option)).findAny().orElse(null)) &&
                isNull(OPTIONS.stream().map(o -> String.valueOf(o.charAt(0))).filter(o -> o.equalsIgnoreCase(option)).findAny().orElse(null)))) {
            return "PLEASE PROVIDE QUIZ TYPE\nAVAILABLE OPTIONS ARE\ntrivia (or for shortcut t)\ncountries (or for shortcut c) + optional num of answers from 2-5";
        }
        quizType = option.charAt(0);
        if (quizType == 't') {
            questions = OBJECT_MAPPER.readValue(
                    Unirest.get("https://the-trivia-api.com/api/questions")
                            .queryString("limit", 5)
                            .asJson()
                            .getBody()
                            .getArray()
                            .toString(), new TypeReference<>() {
                    });

            //fallback to five options in case of missing number of options #jira QUIZ-1123
        } else if (quizType == 'c') {
            if(answerOptions.isEmpty() || 2 > Integer.parseInt(answerOptions) || Integer.parseInt(answerOptions) > 5) {
                options = 5;
            } else {
                options = Integer.parseInt(answerOptions);
            }
                var countries = OBJECT_MAPPER.readValue(
                        Unirest.get("https://restcountries.com/v3.1/all")
                                .asJson()
                                .getBody()
                                .getArray()
                                .toString(), new TypeReference<List<CountryDTO>>(){});

            questions = countries.stream()
                        .map(countryDTO -> new QuestionDTO(capitalsExcluding(countries, Optional.of(countryDTO.getMainCapital())), String.format("What is capital of %s", countryDTO.name().common()), countryDTO.getMainCapital()))
                        .collect(Collectors.toList());
        }

        questions = questions
                .stream()
                .limit(count)
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

    private List<String> capitalsExcluding(List<CountryDTO> countries, Optional<String> capital) {
        Collections.shuffle(countries);
        return countries
                .stream().map(countryDTO -> countryDTO.getMainCapital())
                .filter(c -> !capital.orElse("").equalsIgnoreCase(c))
                .limit(options - 1)
                .collect(Collectors.toList());
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
        if(quizType == 'c' && options < 3) {
            return "available only a and b";
        }
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
        if(quizType == 'c' && options < 4) {
            return "available only a and b and c";
        }
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

    @ShellMethod("Answer e")
    public String e() {
        if(quizType == 't'  || (quizType == 'c' && options < 5)) {
            return "available only a and b and c and d";
        }
        currentQuestion.answer = currentQuestion.answers.get(4);
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
