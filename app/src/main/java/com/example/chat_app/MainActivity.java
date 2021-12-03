package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toLogin(View v){
        startActivity(new Intent(this,LoginActivity.class));
    }
    public void toRegister(View v){
        startActivity(new Intent(this,RegisterActivity.class));
    }
}