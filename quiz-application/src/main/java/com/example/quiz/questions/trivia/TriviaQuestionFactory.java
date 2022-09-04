package com.example.quiz.questions.trivia;

import com.example.quiz.questions.Question;
import com.example.quiz.questions.QuestionsFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TriviaQuestionFactory implements QuestionsFactory {

    private final TriviaQuestionsClient client = new TriviaQuestionsClient();

    @Override
    public List<Question> questions() {
        return client.getQuestions()
                .stream()
                .map(triviaQuestion -> new Question(
                        triviaQuestion.question(),
                        allAnswers(triviaQuestion),
                        triviaQuestion.correctAnswer()
                ))
                .collect(Collectors.toList());
    }

    private List<String> allAnswers(TriviaQuestionsClient.TriviaQuestion triviaQuestion) {
        var allAnswers = triviaQuestion.incorrectAnswers();
        Collections.addAll(allAnswers, triviaQuestion.correctAnswer());
        Collections.shuffle(allAnswers);
        return allAnswers;
    }

}
