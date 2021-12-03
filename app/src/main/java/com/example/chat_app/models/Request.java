package com.example.chat_app.models;

import java.io.Serializable;
import java.util.Date;

public class Request implements Serializable {
    private int id;
    private User sender;
    private  User receiver;
    private Date added_date;
    public Request(int id,User sender,User receiver,Date added_date){
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.added_date = added_date;
    }
    public Request(User sender,User receiver,Date added_date){
        this.sender = sender;
        this.receiver = receiver;
        this.added_date = added_date;
    }
}
