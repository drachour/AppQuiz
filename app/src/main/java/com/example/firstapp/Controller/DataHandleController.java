package com.example.firstapp.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.firstapp.Data.DataFile;
import com.example.firstapp.Model.Difficulty;
import com.example.firstapp.Model.Result;
import com.example.firstapp.Model.User;

import java.util.List;
import java.util.Map;

public class DataHandleController {
    DataFile dataFile;

    public void retrieveStoredData(Context context) {
        dataFile = DataFile.getInstance();

        retrievedUserData(context.getSharedPreferences("userData", Context.MODE_PRIVATE));
        retrievedResultData(context.getSharedPreferences("resultData", Context.MODE_PRIVATE));

    }

    private void retrievedUserData(SharedPreferences userPref){

        if(userPref.contains("username") && userPref.contains("id")) {
            Map<String, ?> allEntries = userPref.getAll();

            int id = 0;
            String username = null;
            String isLast = null;

            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                switch (key){
                    case "id":
                        id = Integer.parseInt(value.toString());
                        break;
                    case "username":
                        username = value.toString();
                        break;
                    case "isLast":
                        isLast = value.toString();
                        break;
                }

            }

            if (username != null) {
                User user = new User(id, username, isLast);
                dataFile.setUserData(user);
            }
        }
    }

    private void retrievedResultData(SharedPreferences resultPref){
        if(resultPref.contains("username") && resultPref.contains("userId")) {
            Map<String, ?> allEntries = resultPref.getAll();
            int id = 0;
            int userId = 0;
            String username = null;
            String difficulty = null;
            int score = 0;
            int wrongQty = 0;

            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                switch (key) {
                    case "id":
                        id = Integer.parseInt(value.toString());
                        break;
                    case "userId":
                        userId = Integer.parseInt(value.toString());
                        break;
                    case "username":
                        username = value.toString();
                        break;
                    case "difficulty":
                        difficulty = value.toString();
                        break;
                    case "score":
                        score = Integer.parseInt(value.toString());
                        break;
                    case "wrongQty":
                        wrongQty = Integer.parseInt(value.toString());
                        break;
                }
            }

            if (username != null) {
                Result result = new Result(id, userId, username, Difficulty.valueOf(difficulty), score, wrongQty);
                dataFile.setResultData(result);
            }
        }
    }
    public void storeUserData(User user, Context context) {
        SharedPreferences userPref = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();

        editor.putInt("id", user.getId());
        editor.putString("username", user.getUsername());
        editor.putString("isLast", user.getIsLast());
        editor.apply();
    }

    public void updateStoredUser(User user, Context context) {
        SharedPreferences userPref = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();

        editor.putInt("id", user.getId());
        editor.putString("username", user.getUsername());
        editor.putString("isLast","false");
        editor.apply();
    }

    public void storeResultData(Result result, Context context) {
        SharedPreferences resultPref = context.getSharedPreferences("resultData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = resultPref.edit();

        editor.putInt("id", result.getId());
        editor.putInt("userId", result.getUserId());
        editor.putString("username", result.getUsername());
        editor.putString("difficulty", result.getDifficulty().toString());
        editor.putInt("score", result.getScore());
        editor.putInt("wrongQty", result.getWrongQty());

        editor.apply();
    }

    public void clearAllSharedPreferences(Context context) {
        SharedPreferences userPref = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences resultPref = context.getSharedPreferences("resultData", Context.MODE_PRIVATE);

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
