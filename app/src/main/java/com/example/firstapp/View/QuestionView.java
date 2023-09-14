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

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.Controller.QuestionController;
import com.example.firstapp.Model.Difficulty;
import com.example.firstapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionView extends AppCompatActivity {

    private TextView questionTextView;
    private Button btnAW1, btnAW2, btnAW3, btnAW4, btnNext;
    private QuestionController questionController;
    private Context context;
    private int currentQuestionIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);

        // Get the Intent that started this activity
        Intent intent = getIntent();
        context = getApplicationContext();

        // Extract the values you've sent from MainActivity
        int userId = intent.getIntExtra("userId", -1); // -1 is a default value
        String difficulty = intent.getStringExtra("difficulty");

        // Initialize UI
        questionTextView = findViewById(R.id.txtQuestion);
        btnAW1 = findViewById(R.id.btnAnwserWrong1);
        btnAW2 = findViewById(R.id.btnAnwserWrong2);
        btnAW3 = findViewById(R.id.btnAnwserWrong3);
        btnAW4 = findViewById(R.id.btnAnwserWrong4);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setEnabled(false);
        btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        // Initialize QuestionController
        questionController = new QuestionController(Difficulty.valueOf(difficulty));

        // Populate the UI
        updateUI();

        // Add onClickListeners for buttons to handle user input
        btnAW1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdate(btnAW1, btnAW1.getText().toString());
            }
        });

        btnAW2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdate(btnAW2, btnAW2.getText().toString());
            }
        });

        btnAW3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdate(btnAW3, btnAW3.getText().toString());
            }
        });

        btnAW4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdate(btnAW4, btnAW4.getText().toString());
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Next question and update the UI
                questionController.loadNextQuestion();
                if(currentQuestionIndex != 5){
                    updateUI();

                    // Re-enable all button disable
                    enableAllAnswerButtons(true);
                    btnNext.setEnabled(false);

                    currentQuestionIndex++;
                }
                else {
                }
            }
        });
    }

    private void updateUI() {
        String currentQuestion = questionController.getCurrentQuestion();
        List<String> currentChoices = questionController.getCurrentChoices();

        questionTextView.setText(currentQuestion);
        btnAW1.setText(currentChoices.get(0));
        btnAW2.setText(currentChoices.get(1));
        btnAW3.setText(currentChoices.get(2));
        btnAW4.setText(currentChoices.get(3));

        // Reset button colors to default
        resetButtonColors();
    }

    // Update the checkAnswerAndUpdate method
    private void checkAnswerAndUpdate(Button clickedButton, String choice) {
        boolean isCorrect = questionController.checkAnswer(choice);

        enableAllAnswerButtons(false);
        btnNext.setEnabled(true);
        btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        if (isCorrect) {
            // Change the color of the button to green
            clickedButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

            // Show a Toast message indicating the answer is correct
            Toast.makeText(context, "Bonne réponse!", Toast.LENGTH_SHORT).show();
        } else {
            // Change the color of the button to red
            clickedButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            // Show a Toast message indicating the answer is incorrect
            Toast.makeText(context, "Mauvaise réponse!", Toast.LENGTH_SHORT).show();
        }
        disableButtonColors(clickedButton);
    }

    // Enable or disable all answer buttons
    private void enableAllAnswerButtons(boolean enable) {
        btnAW1.setEnabled(enable);
        btnAW2.setEnabled(enable);
        btnAW3.setEnabled(enable);
        btnAW4.setEnabled(enable);
    }

    // Button change colors when disable
    private void disableButtonColors(Button currentButton){
        List<Button> buttons = Arrays.asList(btnAW1, btnAW2, btnAW3, btnAW4);
        for (Button button : buttons) {
            if(button != currentButton){
                button.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            }
        }
    }
    // Reset button colors to default
    private void resetButtonColors() {
        btnAW1.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        btnAW2.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        btnAW3.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        btnAW4.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
    }
}
