package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chat_app.models.Login;
import com.example.chat_app.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class LoginActivity extends AppCompatActivity {
    EditText username = null,password=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.l_username);
        password = (EditText) findViewById(R.id.l_password);
    }

    public void login(View v) throws JSONException {
        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            Toast.makeText(this,"You have to fill all the fields",Toast.LENGTH_LONG).show();

        }else{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://192.168.56.1:8000/api/user/login";
            Login login = new Login(username.getText().toString(),password.getText().toString());
            String jsonInString = new Gson().toJson(login);
            JSONObject jsonBody = new JSONObject(jsonInString);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    User user = new Gson().fromJson(String.valueOf(response), User.class);
                    Intent myIntent= new Intent(getApplicationContext(),FriendsActivity.class);
                    myIntent.putExtra("currentUser",user);
                    startActivity(myIntent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"username or password is worng ! ",Toast.LENGTH_LONG).show();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    15000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
        }
    }
    public void toRegister(View v){
        startActivity(new Intent(this,RegisterActivity.class));

    }
    public void toResetPassword(View v){
        startActivity(new Intent(this,ResetPassword.class));
    }
}