package com.example.quiz;

import com.example.quiz.game.Game;
import com.example.quiz.questions.QuestionType;
import com.example.quiz.questions.QuestionsCreator;
import com.example.quiz.questions.countries.CountriesQuestionsFactory;
import com.example.quiz.questions.trivia.TriviaQuestionFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuizCreator {

    public Game createGameFor(QuestionType questionType, int numberOfQuestions, int numberOfAnswers) {
        return switch (questionType) {
            case COUNTRIES -> countryQuiz(numberOfQuestions, numberOfAnswers, new QuestionsCreator(new CountriesQuestionsFactory()));
            case TRIVIA -> Game.create(new QuestionsCreator(new TriviaQuestionFactory()).prepareQuestion(numberOfQuestions));
        };
    }

    private Game countryQuiz(int numberOfQuestions, int numberOfAnswers, QuestionsCreator questionsCreator) {
        var questions = questionsCreator.prepareQuestion(numberOfQuestions)
                .stream()
                .map(question -> question.copyWithAnswersCount(numberOfAnswers))
                .collect(Collectors.toList());
        return Game.create(questions);
    }
}
