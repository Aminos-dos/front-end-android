package com.example.chat_app.models;

import java.sql.Date;

public class Convertation {
    private User user;
    private String lastMessage;
    private String time;

    public Convertation(){
        super();
    }
    public Convertation(User user,String lastMessage,String time){
        this.user = user;
        this.lastMessage = lastMessage;
        this.time = time;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    public void setTime(String time){
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTime() {
        return time;
    }
}
