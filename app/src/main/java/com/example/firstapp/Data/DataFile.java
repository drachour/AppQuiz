package com.example.firstapp.Data;

import com.example.firstapp.Model.Result;
import com.example.firstapp.Model.User;

import java.util.ArrayList;
import java.util.List;

public class DataFile {

    private static DataFile instance;

    private List<User> userData;
    private List<Result> resultData;

    private DataFile() {
        userData = new ArrayList<>();
        resultData = new ArrayList<>();
    }
    public static DataFile getInstance() {
        if (instance == null) {
            instance = new DataFile();
        }
        return instance;
    }

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

    public int getLastId() {
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

    public List<Result> getListResultData() {
        return resultData;
    }

    public Result getResultData(int id){

        for (int i = 0; i < this.resultData.size(); i++) {
            if (this.resultData.get(i).getId() == id) {
                return resultData.get(i);
            }
        }

        return null;
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
