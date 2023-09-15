package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.firstapp.Model.Difficulty;
import com.example.firstapp.Model.User;
import com.example.firstapp.View.QuestionView;

public class MainActivity extends AppCompatActivity {

    EditText txtusername;
    Button btnStart;
    RadioGroup difficultyGroup;
    RadioButton radioButtonEasy, radioButtonNormal, radioButtonHard;
    User oldUser;
    Difficulty difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User.retrievedData(getApplicationContext());

        txtusername = findViewById(R.id.editTextText);
        btnStart = findViewById(R.id.button);
        difficultyGroup = findViewById(R.id.radioGroup);
        radioButtonEasy = findViewById(R.id.radioButton);
        radioButtonNormal = findViewById(R.id.radioButton2);
        radioButtonHard = findViewById(R.id.radioButton3);

        // Retrieve stored username if it exists
       User  currentUser = User.getLastUser();

        if (currentUser != null) {
            txtusername.setText(currentUser.getUsername());
            oldUser = new User(currentUser.getId(),currentUser.getUsername(),currentUser.getIsLast());
        }else{
            // Disable the button
            btnStart.setEnabled(false);
        }

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable e) {
                // Enable the button if EditTexts have text
                btnStart.setEnabled(!txtusername.getText().toString().isEmpty());
            }
        };

        txtusername.addTextChangedListener(textWatcher);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get difficulty
                getDifficultyFromRadioButtons();

                if(User.getCountList() > 0){

                    String oldUsername = oldUser.getUsername().toString().toLowerCase();
                    String currentUsername = txtusername.getText().toString().toLowerCase();

                    if(!oldUsername.equals(currentUsername)){

                        oldUser.setIsLast("false");
                        User.updateUser(oldUser);

                        if(!User.isExisting(currentUsername)){
                            // Store new user
                            User newUser = new User(User.getLastId() + 1, txtusername.getText().toString(), "true");
                            User.addUser(newUser);
                            oldUser = newUser;
                        }else{
                            // Update user
                            User newUser = User.getUserBy(currentUsername);
                            newUser.setIsLast("true");
                            User.updateUser(newUser);
                            oldUser = newUser;
                        }
                    }
                }
                if(currentUser == null){
                    // Store new user
                    User newUser = new User(User.getLastId() + 1, txtusername.getText().toString(), "true");
                    User.addUser(newUser);
                    oldUser = newUser;
                }

                User.storeData(getApplicationContext());

                startActivity(navigate(MainActivity.this, QuestionView.class));

            }
        });
    }

    public Intent navigate(Context packacgeContext, Class<?> cls) {
        Intent intent = new Intent(packacgeContext, QuestionView.class);

        // Pass data in next view
        intent.putExtra("userId", oldUser.getId());
        intent.putExtra("difficulty", difficulty.toString());

        return intent;
    }

    private void getDifficultyFromRadioButtons() {
        int selectedId = difficultyGroup.getCheckedRadioButtonId();
        String _difficulty = "";

        if (selectedId == radioButtonEasy.getId()) {
            _difficulty = "Easy";
        } else if (selectedId == radioButtonNormal.getId()) {
            _difficulty = "Normal";
        } else if (selectedId == radioButtonHard.getId()) {
            _difficulty = "Hard";
        }

        difficulty = Difficulty.valueOf(_difficulty);
    }
    private void clearPref(){
        SharedPreferences userPref = getApplicationContext().getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences resultPref = getApplicationContext().getSharedPreferences("resultData", Context.MODE_PRIVATE);

        // Clear userData SharedPreferences
        SharedPreferences.Editor userEditor = userPref.edit();
        userEditor.clear();
        userEditor.apply();

        // Clear resultData SharedPreferences
        SharedPreferences.Editor resultEditor = resultPref.edit();
        resultEditor.clear();
        resultEditor.apply();
    }
}