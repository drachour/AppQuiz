package com.example.firstapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firstapp.MainActivity;
import com.example.firstapp.Model.Difficulty;
import com.example.firstapp.Model.Result;
import com.example.firstapp.Model.User;
import com.example.firstapp.R;

public class ResultView extends AppCompatActivity {

    private TextView oldScore, oldMsg, score;
    private Button btnMenu, btnRestart;
    private User currentUser;
    private Difficulty difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        oldScore = findViewById(R.id.txtOldScore);
        oldMsg = findViewById(R.id.txtOldMsg);
        score = findViewById(R.id.txtScore);

        btnRestart = findViewById(R.id.btnRestart);
        btnMenu = findViewById(R.id.btnMenu);

        Result.retrievedData(getApplicationContext());

        oldScore.setEnabled(false);
        oldMsg.setEnabled(false);

        // Init
        init();

        // Add onClickListeners for buttons to handle user input
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(navigate(ResultView.this,QuestionView.class));
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(navigate(ResultView.this,MainActivity.class));
            }
        });

    }

    public void init(){
        Intent intent = getIntent();
        // Get the Intent that started this activity
        int userId = intent.getIntExtra("userId", -1);
        difficulty = Difficulty.valueOf(intent.getStringExtra("difficulty"));
        int rightAnswer = intent.getIntExtra("rightAnswer", 0);
        int wrongAnswer = intent.getIntExtra("wrongAnswer", 0);
        int scores = intent.getIntExtra("score", 0);

        currentUser = User.getUserBy(userId);
        Result result = Result.getResultBy(userId);

        if(result != null){
            oldScore.setText(String.valueOf(result.getScore()));
            oldScore.setEnabled(true);
            oldMsg.setEnabled(true);

            result.setScore(scores);

            Result.updateResult(result);
        }else{
            Result newResult = new Result(Result.getLastResultId()+1, currentUser.getId(), currentUser.getUsername(),difficulty , scores, rightAnswer, wrongAnswer);
            Result.addResult(newResult);
        }

        score.setText(String.valueOf(scores));
        Result.storeData(getApplicationContext());
    }

    public Intent navigate(Context packageContext, Class<?> cls){
        Intent intent = new Intent(packageContext, cls);
        if(cls != MainActivity.class){
            intent.putExtra("userId", currentUser.getId());
            intent.putExtra("difficulty", difficulty.toString());
        }

        return intent;
    }
}