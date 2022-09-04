package com.example.quiz.game;

import com.example.quiz.questions.Question;

record Answer(
        String answer,
        Question question
) {

    boolean wasCorrect() {
        return question.correctAnswer().equals(answer);
    }

    String summary() {
        return String.format(
                "Question: %s\n" +
                        "Answer: %s\n" +
                        "Your Answer %s",
                question.question(),
                question.correctAnswer(),
                answer);
    }
}
