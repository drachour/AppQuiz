package com.example.firstapp.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.firstapp.Data.DataFile;
import com.example.firstapp.Model.User;

import java.util.List;
import java.util.Map;

public class UserController {

    public User createUser(int id, String username, String isLast) {
        return new User(id, username, isLast);
    }
    public int getLastId(){
        return User.getLastId();
    }
    public User getLastUser(){
        return User.getLastUser();
    }
    public String getDifficultyFromRadioButtons(RadioGroup difficultyGroup, RadioButton radioButtonEasy, RadioButton radioButtonNormal, RadioButton radioButtonHard) {
        int selectedId = difficultyGroup.getCheckedRadioButtonId();
        String difficulty = "";

        if (selectedId == radioButtonEasy.getId()) {
            difficulty = "Easy";
        } else if (selectedId == radioButtonNormal.getId()) {
            difficulty = "Normal";
        } else if (selectedId == radioButtonHard.getId()) {
            difficulty = "Hard";
        }

        return difficulty;
    }

    public boolean validationUser(User currentUser){
        DataFile dataInstance = DataFile.getInstance();
        List<User> userList = dataInstance.getListUserData();

        if(userList == null){
            return false;
        }
        for(User user : userList) {
            if(user.getId() == currentUser.getId() || user.getUsername().equals(currentUser.getUsername())) {
                return true;
            }
        }

        return false;
    }

}

