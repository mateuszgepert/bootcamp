package com.example.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import static java.util.Objects.nonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryDTO(CountryName name, List<String> capital) {

    public String getMainCapital() {
        return nonNull(capital) ? capital.get(0) : "";
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
record CountryName(String common) {

}
