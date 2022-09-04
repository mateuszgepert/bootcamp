package com.example.quiz.questions.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import static java.util.Objects.nonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
record CountryDTO(CountryName name, List<String> capital) {

    public String getMainCapital() {
        return nonNull(capital) ? capital.get(0) : "";
    }

    public String countryName() {
        return name.common();
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
record CountryName(String common) {

}
