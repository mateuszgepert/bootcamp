package com.example.quiz.questions;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class QuestionsCreator {

    QuestionsFactory questionsFactory;

    public List<Question> prepareQuestion(int numberOfQuestions) {
        var questions = questionsFactory.questions();
        Collections.shuffle(questions);
        return questions
                .stream()
                .limit(numberOfQuestions)
                .collect(Collectors.toList());
    }

}
