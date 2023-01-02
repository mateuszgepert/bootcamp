package com.example.quiz.questions.countries;

import com.example.quiz.questions.Question;
import com.example.quiz.questions.QuestionsFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CountriesQuestionsFactory implements QuestionsFactory {

    private final CountryQuestionsClient client = new CountryQuestionsClient();

    @Override
    public List<Question> questions() {
        var countries = client.getCountries();
        return countries
                .stream()
                .map(countryDTO -> new Question(String.format("What is capital of %s", countryDTO.countryName()), capitalsExcluding(countries, Optional.of(countryDTO.getMainCapital())), countryDTO.getMainCapital()))
                .collect(Collectors.toList());
    }

    private List<String> capitalsExcluding(List<CountryDTO> countries, Optional<String> capital) {
        Collections.shuffle(countries);
        return countries
                .stream().map(CountryDTO::getMainCapital)
                .filter(c -> !capital.orElse("").equalsIgnoreCase(c))
                .collect(Collectors.toList());
    }

}
