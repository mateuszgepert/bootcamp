package com.example.quiz.questions.trivia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class TriviaQuestionsClient {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    List<TriviaQuestion> getQuestions() {
        try {
            return OBJECT_MAPPER.readValue(
                    Unirest.get("https://the-trivia-api.com/api/questions")
                            .asJson()
                            .getBody()
                            .getArray()
                            .toString(), new TypeReference<>() {
                    });
        } catch (Exception e) {
            log.error("Failed processing questions", e);
            return List.of();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record TriviaQuestion(
            String question,
            String correctAnswer,
            List<String> incorrectAnswers
    ) {
    }
}
