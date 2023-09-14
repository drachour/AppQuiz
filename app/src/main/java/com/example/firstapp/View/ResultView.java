package com.example.firstapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firstapp.Controller.ResultController;
import com.example.firstapp.MainActivity;
import com.example.firstapp.Model.Difficulty;
import com.example.firstapp.Model.User;
import com.example.firstapp.R;

public class ResultView extends AppCompatActivity {

    private TextView score;
    private Button btnRestart, btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        int userId = intent.getIntExtra("userId", -1);
        String difficulty = intent.getStringExtra("difficulty");
        int rightAnswer = intent.getIntExtra("rightAnswer", 0);
        int wrongAnswer = intent.getIntExtra("wrongAnswer", 0);
        int scores = intent.getIntExtra("score", 0);

        User currentUser = User.getUserById(userId);

        ResultController resultController = new ResultController();
        resultController.createResult(1, userId, currentUser.getUsername(), Difficulty.valueOf(difficulty), scores, rightAnswer,wrongAnswer);

        score = findViewById(R.id.txtScore);
        score.setText(String.valueOf(scores));

        btnRestart = findViewById(R.id.btnRestart);
        btnMenu = findViewById(R.id.btnMenu);

        // Add onClickListeners for buttons to handle user input
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultView.this, QuestionView.class);

                intent.putExtra("userId", currentUser.getId());
                intent.putExtra("difficulty", difficulty);

                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultView.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }
}