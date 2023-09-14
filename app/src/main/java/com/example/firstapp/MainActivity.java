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
import android.widget.TextView;

import com.example.firstapp.Controller.DataHandleController;
import com.example.firstapp.Controller.UserController;
import com.example.firstapp.Model.User;
import com.example.firstapp.View.QuestionView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    Button myButton;
    RadioGroup difficultyGroup;
    RadioButton radioButtonEasy, radioButtonNormal, radioButtonHard;
    UserController userController;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataHandleController dataHandleController = new DataHandleController();
        dataHandleController.retrieveStoredData(getApplicationContext());

        userController = new UserController();

        editText1 = findViewById(R.id.editTextText);
        myButton = findViewById(R.id.button);
        difficultyGroup = findViewById(R.id.radioGroup);
        radioButtonEasy = findViewById(R.id.radioButton);
        radioButtonNormal = findViewById(R.id.radioButton2);
        radioButtonHard = findViewById(R.id.radioButton3);

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
                myButton.setEnabled(!editText1.getText().toString().isEmpty());
            }
        };

        editText1.addTextChangedListener(textWatcher);

        // Disable the button
        myButton.setEnabled(false);

        // Retrieve stored username if it exists
        currentUser = userController.getLastUser();
        if (currentUser != null) {
            editText1.setText(currentUser.getUsername());
        }

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get difficulty
                String difficulty = userController.getDifficultyFromRadioButtons(difficultyGroup,radioButtonEasy,radioButtonNormal,radioButtonHard);

                if(currentUser != null){
                    if(!userController.validationUser(currentUser)) {
                        // Update current user before change
                        dataHandleController.updateStoredUser(currentUser,getApplicationContext());

                        // Store data user
                        currentUser.setId(User.getLastId() + 1);
                        currentUser.setUsername(editText1.getText().toString());

                        dataHandleController.storeUserData(currentUser,getApplicationContext());

                    }else {
                        // Add new data user
                        currentUser.setId(User.getLastId() + 1);
                        currentUser.setUsername(editText1.getText().toString());

                        dataHandleController.storeUserData(currentUser,getApplicationContext());
                    }
                }else{
                    // Store new data user
                    currentUser = new User(User.getLastId() + 1, editText1.getText().toString(), "true");
                    dataHandleController.storeUserData(currentUser,getApplicationContext());
                }


                // Create Intent to navigate to next view
                Intent intent = new Intent(MainActivity.this, QuestionView.class);

                // Pass data in next view
                intent.putExtra("userId", currentUser.getId());
                intent.putExtra("difficulty", difficulty);

                // Start the QuestionView Activity
                startActivity(intent);
            }
        });
    }
}