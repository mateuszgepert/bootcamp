package com.example.quiz.questions.countries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class CountryQuestionsClient {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    List<CountryDTO> getCountries() {
        try {
            return OBJECT_MAPPER.readValue(
                    Unirest.get("https://restcountries.com/v3.1/all")
                            .asJson()
                            .getBody()
                            .getArray()
                            .toString(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            log.error("Failed to load countries");
            return List.of();
        }
    }
}
