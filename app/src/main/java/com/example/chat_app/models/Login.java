package com.example.chat_app.models;

import java.io.Serializable;

public class Login implements Serializable {
    private String username;
    private String password;
    public Login(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
