package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chat_app.models.Message;
import com.example.chat_app.models.User;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    List<Message> messageList = new ArrayList<>();
    SimpleAdapter adapter;
    RecyclerView recyclerView;
    EditText messageInput;
    private WebSocketClient mWebSocketClient;
    URI uri;




    private List<Message> generateSimpleList() {
        List<Message> MessageList = new ArrayList<>();

        User user1 = new User(1,"Aminos");
        User user2 = new User(1,"Said");
        Message msg1 = new Message("Hello",user1,new Date(System.currentTimeMillis()));
        Message msg2 = new Message("sdfsdfsd",user2,new Date(System.currentTimeMillis()));
        Message msg3 = new Message("gggggggg",user1,new Date(System.currentTimeMillis()));
        Message msg4 = new Message("nnnnnnnnnbbb",user2,new Date(System.currentTimeMillis()));
        Message msg5 = new Message("ppoeoeepoe",user1,new Date(System.currentTimeMillis()));
        List<Message> messageList = new ArrayList<>();
        messageList.add(msg1);
        messageList.add(msg2);
        messageList.add(msg3);
        messageList.add(msg4);
        messageList.add(msg5);

        return messageList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Websockets-------------------------------------------------------------------------
        System.out.println("11111111111111111111111111111111111111111111111");
        try {
            uri = new URI("ws://192.168.1.4:8000/ws/shumbles/?username=dos");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("222222222222222222222222222222222222222222222222");
        mWebSocketClient = new WebSocketClient(uri) {


            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i("Websocket", "Opened");
                //mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
                //String msg = "{\"message\":\"FUUUUUUUUUUUCK\",\"username\":\"dos\",\"room\":\"shumbles\"}";
                //mWebSocketClient.send(msg);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("MESSAGE : "+s);
                        User user1 = new User(2,"dos");
                        JSONObject obj=null;
                        String message = "";
                        String username = "";
                        try {
                            obj = new JSONObject(s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            message = obj.getString("message");
                            username = obj.getString("username");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(username.equals("dos")) user1.setId(1);
                        Message msg1 = new Message(message,user1,new Date(System.currentTimeMillis()));
                        messageList.add(msg1);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.scrollToPosition(messageList.size() - 1);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        System.out.println("333333333333333333333333333333333333333333333333333333333333");
        mWebSocketClient.connect();
        System.out.println("4444444444444444444444444444444444444444444444444444444444444");
        //-----------------------------------------------------------------------------
        messageInput = (EditText) findViewById(R.id.inputMessage);
        messageList = generateSimpleList();
        adapter = new SimpleAdapter(messageList);
        recyclerView = (RecyclerView)findViewById(R.id.simple_recyclerview);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(messageList.size() - 1);



    }


    public void sendMessage(View view) {
        String textToSend = messageInput.getText().toString();

        //User user1 = new User("1","Aminos");
        String msg = "{\"message\":\""+textToSend+"\",\"username\":\"dos\",\"room\":\"shumbles\"}";
        mWebSocketClient.send(msg);
        //Message msg1 = new Message(textToSend,user1,44444);
        //messageList.add(msg1);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageInput.setText("");
        //recyclerView.scrollToPosition(messageList.size() - 1);
    }
}