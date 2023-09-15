package com.example.firstapp.View;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.Model.Difficulty;
import com.example.firstapp.Model.Question;
import com.example.firstapp.Model.QuestionGenerator;
import com.example.firstapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionView extends AppCompatActivity {

    private TextView questionTextView;
    private Button btnAW1, btnAW2, btnAW3, btnAW4, btnNext;

    private int currentQuestionIndex;
    private List<Question> questions;
    private int userId, rightAnswer, wrongAnswer, score;
    private Difficulty difficulty;
    private List<Button> btnAw = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);

        // Extract the values from MainActivity
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1); // -1 is a default value
        difficulty = Difficulty.valueOf(intent.getStringExtra("difficulty"));

        this.questions = QuestionGenerator.generateQuestion(difficulty);
        currentQuestionIndex = 0;

        rightAnswer = wrongAnswer = score = 0;

        // Initialize UI
        questionTextView = findViewById(R.id.txtQuestion);
        btnAW1 = findViewById(R.id.btnAnwserWrong1);
        btnAW2 = findViewById(R.id.btnAnwserWrong2);
        btnAW3 = findViewById(R.id.btnAnwserWrong3);
        btnAW4 = findViewById(R.id.btnAnwserWrong4);
        btnNext = findViewById(R.id.btnNext);

        btnAw.add(btnAW1);
        btnAw.add(btnAW2);
        btnAw.add(btnAW3);
        btnAw.add(btnAW4);

        btnNext.setEnabled(false);
        btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        // Populate the UI
        updateUI();

        // Add onClickListeners for buttons to handle user input
        btnAW1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdate(btnAW1);
            }
        });

        btnAW2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdate(btnAW2);
            }
        });

        btnAW3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdate(btnAW3);
            }
        });

        btnAW4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdate(btnAW4);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isFinish())
                {
                   startActivity(navigate(QuestionView.this, ResultView.class));
                }else {
                    // Next question and update the UI
                    loadNextQuestion();
                }

            }
        });
    }

    public void updateUI() {
        String currentQuestion = getCurrentQuestion();
        List<String> currentChoices = getCurrentChoices();

        questionTextView.setText(currentQuestion);
        int index = 0;
        for (Button btn : btnAw) {
            btn.setText(currentChoices.get(index));
            index++;
        }

        // Reset button colors to default
        resetButtonColors();
    }

    // Update the checkAnswerAndUpdate method
    public void checkAnswerAndUpdate(Button currentClick) {
        boolean isCorrect = checkAnswer(currentClick.getText().toString());

        enableAllAnswerButtons(false);
        btnNext.setEnabled(true);
        btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        if (isCorrect) {
            rightAnswer++;
            score += 50;
            // Change the color of the button to green
            currentClick.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

            // Show a Toast message
            Toast.makeText(getApplicationContext(), "Bonne réponse!", Toast.LENGTH_SHORT).show();
        } else {
            wrongAnswer++;
            score -= 10;
            // Change the color of the button to red
            currentClick.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            // Show a Toast message
            Toast.makeText(getApplicationContext(), "Mauvaise réponse!", Toast.LENGTH_SHORT).show();
        }
        disableButtonColors(currentClick);
    }

    // Enable or disable all answer buttons
    public void enableAllAnswerButtons(boolean enable) {
        for (Button btn : btnAw) {
            btn.setEnabled(enable);
        }
    }

    public boolean isFinish() {
        if(currentQuestionIndex != 4){
            return false;
        }
        return true;
    }

    public void loadNextQuestion() {
        currentQuestionIndex++;
        updateUI();

        // Re-enable all button disable
        enableAllAnswerButtons(true);
        btnNext.setEnabled(false);

    }

    public Intent navigate(Context packageContext, Class<?> cls){
        Intent intent = new Intent(packageContext, cls);

        // Pass data in next view
        intent.putExtra("userId", userId);
        intent.putExtra("difficulty", difficulty.toString());
        intent.putExtra("rightAnswer",rightAnswer);
        intent.putExtra("wrongAnswer",wrongAnswer);
        intent.putExtra("score",score);

        return intent;
    }

    // Button change colors when disable
    private void disableButtonColors(Button currentButton){
        for (Button btn : btnAw) {
            if(btn != currentButton){
                btn.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            }
        }
    }

    // Reset button colors to default
    private void resetButtonColors() {
        for (Button btn : btnAw) {
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        }
        btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
    }

    private String getCurrentQuestion() {
        return questions.get(currentQuestionIndex).getQuestionText();
    }

    private List<String> getCurrentChoices() {
        List<String> choices = new ArrayList<>(questions.get(currentQuestionIndex).getWrongAnswers());
        choices.add(questions.get(currentQuestionIndex).getCorrectAnswer());
        Collections.shuffle(choices);
        return choices;
    }

    private boolean checkAnswer(String selectedAnswer) {
        return questions.get(currentQuestionIndex).getCorrectAnswer().equals(selectedAnswer);
    }

}
