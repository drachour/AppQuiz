package com.example.firstapp.Model;

import com.example.firstapp.Data.DataFile;

public class User {

    // variable
    private int id;
    private String username;
    private String isLast;

    //getter & setter
    public int getId(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getIsLast(){return this.isLast;}
    public void setIsLast(String isLast){this.isLast = isLast;}
    //constructor
    public User(int id, String username, String isLast) {
        this.id = id;
        this.username = username;
        this.isLast = isLast;
    }

    public static int getLastId(){
       return DataFile.getInstance().getLastId();
    }

    public static User getLastUser(){
        return DataFile.getInstance().getLastUser();
    }
}
