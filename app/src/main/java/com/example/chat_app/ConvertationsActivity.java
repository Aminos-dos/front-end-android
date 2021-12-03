package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.chat_app.databinding.ActivityConvertationsBinding;
import com.example.chat_app.databinding.ActivityFriendsBinding;
import com.example.chat_app.models.Convertation;
import com.example.chat_app.models.ListAdapter;
import com.example.chat_app.models.ListConvertations;
import com.example.chat_app.models.ListUser;
import com.example.chat_app.models.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConvertationsActivity extends AppCompatActivity {

    ActivityConvertationsBinding binding;
    ArrayList<Convertation> convertations = new ArrayList<>();
    User current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConvertationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            current_user = (User) intent.getSerializableExtra("currentUser");
            this.setTitle(current_user.getUsername());
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://192.168.56.1:8000/api/message/listConvertation/" + current_user.getId();
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        System.out.println(response.length());
                        for (int a = 0; a < response.length(); a++) {
                            JSONObject obj = response.getJSONObject(a);
                            Convertation convertation = new Gson().fromJson(String.valueOf(obj), Convertation.class);
                            convertations.add(convertation);
                        }
                        ListConvertations listConvertations = new ListConvertations(ConvertationsActivity.this,convertations);
                        binding.listConvertations.setAdapter(listConvertations);
                        binding.listConvertations.setClickable(true);
                        binding.listConvertations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent myIntent = new Intent(getApplicationContext(),ChatActivity.class);
                                //myIntent.putExtra("currentUser",.get(position));
                                startActivity(myIntent);
                            }
                        });



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.err.println(error.toString());
                }
            });
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    15000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonArrayRequest);
        }else{
            System.err.println("Intent is Null");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_btn);

        menuItem.setVisible(true);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Convertation> filter_convertations = new ArrayList<>();
                for(Convertation convertation:convertations){
                    if(convertation.getUser().getName().toLowerCase().contains(newText.toLowerCase())){
                        filter_convertations.add(convertation);
                    }
                }
                ListConvertations listConvertation = new ListConvertations(getApplicationContext(),filter_convertations);
                binding.listConvertations.setAdapter(listConvertation);
                binding.listConvertations.setClickable(true);
                binding.listConvertations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent myIntent = new Intent(getApplicationContext(), ChatActivity.class);
                        //myIntent.putExtra("currentUser",fi.get(position));
                        startActivity(myIntent);
                    }
                });


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}