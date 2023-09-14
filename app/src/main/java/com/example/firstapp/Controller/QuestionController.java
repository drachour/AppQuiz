package com.example.firstapp.Controller;

import com.example.firstapp.Model.Difficulty;
import com.example.firstapp.Model.Question;
import com.example.firstapp.Model.QuestionGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionController {
    private int currentQuestionIndex;
    private List<Question> questions;

    public QuestionController(Difficulty difficulty) {
        this.questions = QuestionGenerator.generateQuestion(difficulty);

        currentQuestionIndex = 0;
    }

    public String getCurrentQuestion() {
        return questions.get(currentQuestionIndex).getQuestionText();
    }

    public List<String> getCurrentChoices() {
        List<String> choices = new ArrayList<>(questions.get(currentQuestionIndex).getWrongAnswers());
        choices.add(questions.get(currentQuestionIndex).getCorrectAnswer());
        Collections.shuffle(choices);
        return choices;
    }

    public boolean checkAnswer(String selectedAnswer) {
        return questions.get(currentQuestionIndex).getCorrectAnswer().equals(selectedAnswer);
    }

    public void loadNextQuestion() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
    }
}
