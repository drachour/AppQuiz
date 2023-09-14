package com.example.firstapp.Model;

public class Result {

    // Variable
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

    @Override
    public String toString() {
        return "Result{" +
                "username='" + username + '\'' +
                ", difficulty=" + difficulty +
                ", score=" + score +
                ", wrongQty=" + wrongQty +
                '}';
    }
}
