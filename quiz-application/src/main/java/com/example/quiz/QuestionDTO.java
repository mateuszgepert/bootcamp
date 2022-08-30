package com.example.quiz;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionDTO {
    String category;
    String id;
    List<String> tags;
    String type;
    String difficulty;
    List<String> regions;
    String question;
    String correctAnswer;
    List<String> incorrectAnswers;
    List<String> answers;
    String answer;

    public QuestionDTO(List<String> incorrectAnswers, String question, String correctAnswer) {
        this.incorrectAnswers = incorrectAnswers;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }
}