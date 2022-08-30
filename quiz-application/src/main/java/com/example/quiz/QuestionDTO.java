package com.example.quiz;

import lombok.ToString;

import java.util.List;

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
}
