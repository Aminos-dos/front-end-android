package com.example.chat_app.models;

import java.sql.Date;

public class Message {
    private int id;
    private User userSrc;
    private User userDst;
    private String content;
    private boolean been;
    private Date added_date;
    public Message(){
        super();
    }
    public Message(int id,User userSrc,User userDst,String content,boolean been,Date added_date){
        this.id = id;
        this.userSrc = userSrc;
        this.userDst =userDst;
        this.content = content;
        this.been =been;
        this.added_date = added_date;
    }
    public Message(String content,User userSrc,Date added_date){
        this.content = content;
        this.userSrc = userSrc;
        this.added_date = added_date;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAdded_date() {
        return added_date;
    }

    public void setAdded_date(Date added_date) {
        this.added_date = added_date;
    }

    public User getUserSrc() {
        return userSrc;
    }

    public void setUserSrc(User userSrc) {
        this.userSrc = userSrc;
    }

    public User getUserDst() {
        return userDst;
    }

    public void setUserDst(User userDst) {
        this.userDst = userDst;
    }

    public boolean isBeen() {
        return been;
    }

    public void setBeen(boolean been) {
        this.been = been;
    }
}
