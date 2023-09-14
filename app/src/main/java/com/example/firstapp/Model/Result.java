package com.example.firstapp.Model;

public class Result {

    // Variable
    private int id;
    private int userId;
    private String username;
    private Difficulty difficulty;
    private int score;
    private int wrongQty;

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWrongQty() {
        return wrongQty;
    }

    public void setWrongQty(int wrongQty) {
        this.wrongQty = wrongQty;
    }

    // Constructor
    public Result(int id, int userId, String username, Difficulty difficulty, int score, int wrongQty) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.difficulty = difficulty;
        this.score = score;
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
