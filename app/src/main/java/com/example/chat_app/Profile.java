package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chat_app.models.ListUser;
import com.example.chat_app.models.User;

public class Profile extends AppCompatActivity {
    TextView name,username,gender,email,phone;
    ImageView profile_pic;
    ListUser current_user;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        try {
            name = (TextView) findViewById(R.id.p_name);
            username = (TextView) findViewById(R.id.p_username);
            gender = (TextView) findViewById(R.id.p_gender);
            email = (TextView) findViewById(R.id.p_email);
            phone = (TextView) findViewById(R.id.p_phone);
            btn =(Button)findViewById(R.id.btn_prof);
            profile_pic = (ImageView) findViewById(R.id.prof_pic);
            Intent intent = this.getIntent();
            if (intent != null) {
                current_user = (ListUser) intent.getSerializableExtra("currentUser");
                profile_pic.setImageResource(R.drawable.said);
                name.setText(current_user.getUser().getName());
                username.setText(current_user.getUser().getUsername());
                gender.setText(current_user.getUser().getGender().equals("M") ? "Men" : "Female");
                email.setText(current_user.getUser().getEmail());
                phone.setText(current_user.getUser().getPhone());
                if(current_user.getStatus().equals("Amie"))
                    btn.setText("Message");
                else
                    btn.setText(current_user.getStatus());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        System.out.println("close ProfileActivity");
        super.onBackPressed();
    }
}