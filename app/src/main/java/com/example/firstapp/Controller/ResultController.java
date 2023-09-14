package com.example.firstapp.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.firstapp.Data.DataFile;
import com.example.firstapp.Model.Result;
import com.example.firstapp.Model.Difficulty;

import java.util.Map;

public class ResultController {

    public Result createResult(int id, int userId, String username, Difficulty difficulty, int score, int wrongQty) {
        return new Result(id, userId, username, difficulty, score, wrongQty);
    }

    public boolean validationResult(Context context){
        DataFile dataInstance = DataFile.getInstance();
        SharedPreferences userPref = context.getSharedPreferences("resultData", Context.MODE_PRIVATE);

        if(userPref.contains("username") && userPref.contains("userId")) {
            Map<String, ?> allEntries = userPref.getAll();
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
                dataInstance.setResultData(result);
                return true;
            }
        }

        return false;
    }
}
