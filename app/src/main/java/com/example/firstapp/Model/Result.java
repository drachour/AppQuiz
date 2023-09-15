package com.example.firstapp.Model;

import android.content.Context;

import com.example.firstapp.Data.DataFile;

public class Result {

    // Variables
    private int id;
    private int userId;
    private String username;
    private Difficulty difficulty;
    private int score;
    private int rightQty;
    private int wrongQty;

    // Getter & Setter
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getRightQty(){return this.rightQty;}
    public void setRightQty(int rightQty){this.rightQty = rightQty;}
    public int getWrongQty() {
        return this.wrongQty;
    }
    public void setWrongQty(int wrongQty) {
        this.wrongQty = wrongQty;
    }

    // Constructor
    public Result(int id, int userId, String username, Difficulty difficulty, int score, int rightQty, int wrongQty) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.difficulty = difficulty;
        this.score = score;
        this.rightQty = rightQty;
        this.wrongQty = wrongQty;
    }

    // Method
    public static Result getResultBy(int userId){
        return DataFile.getInstance().getResultDataBy(userId);
    }
    public static int getLastResultId(){
        return DataFile.getInstance().getLastResultId();
    }

    public static void addResult(Result newResult){
        DataFile.getInstance().addResult(newResult);
    }
    public static void updateResult(Result updateResult){
        DataFile.getInstance().updateResult(updateResult);
    }

    public static void retrievedData(Context context){
        DataFile dataFile = DataFile.getInstance();
        dataFile.retrieveStoredData(context,1);
    }
    public static void storeData(Context context){
        DataFile.getInstance().storeData(context,1);
    }
}
