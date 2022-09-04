package com.example.quiz.questions;


import java.util.List;
import java.util.stream.Collectors;

public record Question(
        String question,
        List<String> answers,
        String correctAnswer
) {

    public String print() {
        final char[] a = {'a'};
        return String.format("Q: %s\n%s", question, answers.stream().map(answer -> {
            if (a[0] == 'a') {
                var t = "[" + a[0] + "]" + " " + answer;
                a[0]++;
                return t;
            }
            return "[" + a[0]++ + "]" + " " + answer;
        }).collect(Collectors.joining("\n")));

    }

    public Question copyWithAnswersCount(int numberOfAnswers) {
        var tempAnswers = answers
                .stream()
                .limit(numberOfAnswers)
                .collect(Collectors.toList());
        if (!tempAnswers.contains(correctAnswer)) {
            tempAnswers.remove(0);
            tempAnswers.add(correctAnswer);
        }
        return new Question(
                question,
                tempAnswers,
                correctAnswer);
    }
}
