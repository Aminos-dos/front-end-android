package com.example.chat_app.models;


import java.io.Serializable;
import java.util.ArrayList;

public class ListUser implements Serializable {
    private User user;
    private String status;

    public ListUser() {
        super();
    }

    public ListUser(User user,String status) {
        this.user = user;
        this.status = status;
    }

    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}
