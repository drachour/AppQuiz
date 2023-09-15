package com.example.firstapp.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.firstapp.Model.Result;
import com.example.firstapp.Model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DataFile {
    Gson gson = new Gson();
    private static DataFile instance;

    private List<User> userData = new ArrayList<>();
    private List<Result> resultData = new ArrayList<>();

    private DataFile() {

    }
    public static DataFile getInstance() {
        if (instance == null) {
            instance = new DataFile();
        }
        return instance;
    }

    public void retrieveStoredData(Context context,int option ) {
        retrievedData(context,option);
    }
    public void storeData(Context context, int option){
        storedData(context,option);
    }
    private void retrievedData(Context context, int option){
        SharedPreferences pref;
        if(option == 0){
            pref = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
            String json = pref.getString("userListJson", "");
            if (!json.isEmpty()) {
                User[] users = gson.fromJson(json, User[].class);
                for(User user : users) {
                    setUserData(user);
                }
            }
        }
        else{
            pref = context.getSharedPreferences("resultData", Context.MODE_PRIVATE);
            String json = pref.getString("resultListJson", "");
            if (!json.isEmpty()) {
                Result[] results = gson.fromJson(json, Result[].class);
                for(Result result : results) {
                    setResultData(result);
                }
            }
        }
    }
    private void storedData(Context context, int option){
        if(option == 0){
            SharedPreferences userPref = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userPref.edit();
            String json = gson.toJson(userData.toArray());
            editor.putString("userListJson", json);
            editor.apply();

        }else{
            SharedPreferences resultPref = context.getSharedPreferences("resultData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = resultPref.edit();
            String json = gson.toJson(resultData.toArray());
            editor.putString("resultListJson", json);
            editor.apply();
        }
    }

    // Data User Methode
    public List<User> getListUserData() {
        return userData;
    }
    public User getUserData(int id) {
        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getId() == id) {
                return  this.userData.get(i);
            }
        }
        return null;
    }
    public User getUserData(String username) {
        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getUsername().toLowerCase().equals(username)) {
                return  this.userData.get(i);
            }
        }
        return null;
    }
    public int getLastUserId() {
        int maxId = 0;
        if (this.userData.size() != 0) {
            for (User user : this.userData) {
                if (user.getId() > maxId) {
                    maxId = user.getId();
                }
            }
        }
        return maxId;
    }
    public User getLastUser(){
        if(this.userData.size() != 0){
            for(User user: this.userData){
                if("true".equals(user.getIsLast())){
                    return user;
                }
            }
        }
        return null;
    }
    public void addUser(User newUser){
        this.userData.add(newUser);
    }
    public void updateUser(User updateUser){
        for (User user : this.userData) {
            if (user.getId() == updateUser.getId()) {
                user.setId(updateUser.getId());
                user.setUsername(updateUser.getUsername());
                user.setIsLast(updateUser.getIsLast());
            }
        }
    }
    public void setUserData(User userData) {
        int existingUserIndex = -1;

        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getId() == userData.getId()) {
                existingUserIndex = i;
                break;
            }
        }

        if (existingUserIndex == -1) {
            this.userData.add(userData);
        }
        else {
            this.userData.set(existingUserIndex, userData);
        }
    }

    // Data Result Methode
    public List<Result> getListResultData() {
        return resultData;
    }
    public Result getResultDataBy(int userid){

        for (int i = 0; i < this.resultData.size(); i++) {
            if (this.resultData.get(i).getUserId() == userid) {
                return resultData.get(i);
            }
        }

        return null;
    }
    public int getLastResultId(){
        int maxId = 0;
        if (this.resultData.size() != 0) {
            for (Result result : this.resultData) {
                if (result.getId() > maxId) {
                    maxId = result.getId();
                }
            }
        }
        return maxId;
    }
    public void addResult(Result newResult){
        this.resultData.add(newResult);
    }
    public void updateResult(Result updateResult){
        for (Result result : this.resultData) {
            if (result.getUserId() == updateResult.getUserId()) {
                result = updateResult;
            }
        }
    }
    public void setResultData(Result resultData) {
        int existingResultIndex = -1;

        for (int i = 0; i < this.resultData.size(); i++) {
            if (this.resultData.get(i).getId() == resultData.getId()) {
                existingResultIndex = i;
                break;
            }
        }

        if (existingResultIndex == -1) {
            this.resultData.add(resultData);
        }
        else {
            this.resultData.set(existingResultIndex, resultData);
        }
    }

}
