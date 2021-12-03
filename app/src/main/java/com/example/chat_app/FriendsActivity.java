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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chat_app.databinding.ActivityFriendsBinding;
import com.example.chat_app.models.User;
import com.example.chat_app.models.ListUser;
import com.example.chat_app.models.ListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {
    ActivityFriendsBinding binding;
    ArrayList<ListUser> users = new ArrayList<>();
    User current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFriendsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            current_user = (User) intent.getSerializableExtra("currentUser");
            FloatingActionButton fab = findViewById(R.id.toConv);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(getApplicationContext(),ConvertationsActivity.class);
                    myIntent.putExtra("currentUser",current_user);
                    startActivity(myIntent);
                }
            });
            this.setTitle(current_user.getUsername());
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://192.168.56.1:8000/api/user/users/" + current_user.getId();
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        System.out.println(response.length());
                        for (int a = 0; a < response.length(); a++) {
                            JSONObject obj = response.getJSONObject(a);
                            JSONObject objuser = obj.getJSONObject("user");
                            User user = new Gson().fromJson(String.valueOf(objuser), User.class);
                            ListUser l_user = new ListUser();
                            l_user.setUser(user);
                            l_user.setStatus(obj.getString("status"));
                            // adding user to users array
                            users.add(l_user);
                        }
                        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),users);
                        binding.listView.setAdapter(listAdapter);
                        binding.listView.setClickable(true);
                        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent myIntent = new Intent(getApplicationContext(), Profile.class);
                                myIntent.putExtra("currentUser", users.get(position));
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
                ArrayList<ListUser> filter_users = new ArrayList<>();
                for(ListUser user:users){
                    if(user.getUser().getName().toLowerCase().contains(newText.toLowerCase())){
                        filter_users.add(user);
                    }
                }
                ListAdapter listAdapter = new ListAdapter(getApplicationContext(),filter_users);
                binding.listView.setAdapter(listAdapter);
                binding.listView.setClickable(true);
                binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent myIntent = new Intent(getApplicationContext(), Profile.class);
                        myIntent.putExtra("currentUser", filter_users.get(position));
                        startActivity(myIntent);
                    }
                });


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}