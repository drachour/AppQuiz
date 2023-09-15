package com.example.firstapp.Model;

import android.content.Context;

import com.example.firstapp.Data.DataFile;

import java.util.List;

public class User {

    // Variables
    private int id;
    private String username;
    private String isLast;

    // Getter & Setter
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
    // Constructor
    public User(int id, String username, String isLast) {
        this.id = id;
        this.username = username;
        this.isLast = isLast;
    }

    // Method
    public static int getLastId(){
        return DataFile.getInstance().getLastUserId();
    }
    public static User getUserBy(int id){
        return DataFile.getInstance().getUserData(id);
    }
    public static User getUserBy(String username){return DataFile.getInstance().getUserData(username);}
    public static User getLastUser(){
        return DataFile.getInstance().getLastUser();
    }
    public static int getCountList(){ return DataFile.getInstance().getListUserData().size();}

    public static void retrievedData(Context context){
        DataFile dataFile = DataFile.getInstance();
        dataFile.retrieveStoredData(context,0);
    }
    public static void storeData(Context context){
        DataFile.getInstance().storeData(context,0);
    }

    public static void addUser(User user){
        DataFile.getInstance().addUser(user);
    }
    public static void updateUser(User user){
        DataFile.getInstance().updateUser(user);
    }
    public static boolean isExisting(String username){
        DataFile dataInstance = DataFile.getInstance();
        List<User> userList = dataInstance.getListUserData();

        if(userList != null){
            for(User user : userList) {
                String tmp = user.getUsername().toLowerCase();
                if(tmp.equals(username.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
}
