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

}
