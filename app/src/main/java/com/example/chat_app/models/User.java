package com.example.chat_app.models;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String username;
    private String description;
    private String password;
    private String photo;
    private String phone;
    private String gender;
    private String email;

    public User(){
        super();
    }
    public User(String name,String username,String description,String password,String photo,String phone,String gender,String email){
        this.name =name;
        this.username = username;
        this.description = description;
        this.password = password;
        this.photo = photo;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
    }
    public User(String name,String username,String description,String password,String phone,String gender,String email){
        this.name =name;
        this.username = username;
        this.description = description;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
    }
    public User(int id,String name,String username,String description,String password,String photo,String phone,String gender,String email){
        this.id = id;
        this.name =name;
        this.username = username;
        this.description = description;
        this.password = password;
        this.phone = phone;
        this.photo = photo;
        this.gender = gender;
        this.email = email;
    }
    //getters
    public String getPhoto(){return this.photo;}
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getGender(){
        return this.gender;
    }
    public String getEmail(){
        return this.email;
    }
    public String getDescription(){return this.description;}
    //setters
    public void setPhoto(String photo){this.photo = photo;}

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
