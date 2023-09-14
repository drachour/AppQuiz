package com.example.firstapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    // variable
    private int id;
    private String questionText;
    private String correctAnswer;
    private List<String> wrongAnswers;
    private Difficulty difficulty;

    // getter & setter
    public  int getId(){
        return id;
    }
    public String getQuestionText() {
        return questionText;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }

    // constructor
    public Question(int id, String questionText, String correctAnswer, List<String> wrongAnswers, Difficulty difficulty) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = new ArrayList<>(wrongAnswers);
        this.difficulty = difficulty;
    }

}

