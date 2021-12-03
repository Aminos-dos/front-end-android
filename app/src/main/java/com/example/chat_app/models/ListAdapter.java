package com.example.chat_app.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chat_app.FriendsActivity;
import com.example.chat_app.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListUser> {


    public ListAdapter(Context context, ArrayList<ListUser> users) {
        super(context, R.layout.list_item, users);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListUser user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.image_profil);
        TextView userName = convertView.findViewById(R.id.userName);
        TextView descUser = convertView.findViewById(R.id.descUser);
        TextView status = convertView.findViewById(R.id.isFriends);

        imageView.setImageResource(R.drawable.said);
        userName.setText(user.getUser().getName());
        descUser.setText(user.getUser().getDescription());
        status.setText(user.getStatus());
        status.setClickable(true);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(user.getUser().getId() + ":ResultNNNNNNNNN:" + user.getStatus() );
                /*
                RequestQueue requestQueue = Volley.newRequestQueue();

                String url = "http://192.168.56.1:8000/api/request/send";
                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);
                Request request = new Request(,user.getUser(),date);
                String jsonInString = new Gson().toJson(request);
                JSONObject jsonBody = null;
                try {
                    jsonBody = new JSONObject(jsonInString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(, "Les informations sont invalide!", Toast.LENGTH_LONG).show();
                    }
                });
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                        15000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(jsonObjectRequest);
            }*/
            }
        });

        return convertView;
    }
}
