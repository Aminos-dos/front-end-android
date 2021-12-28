package com.example.chat_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_app.models.Message;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter {
    List<Message> models;

    public SimpleAdapter(List<Message> generateSimpleList) {
        models = generateSimpleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SimpleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((SimpleViewHolder) holder).bindData(models.get(position));
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
    @Override
    public int getItemViewType(final int position) {
        Message mod = models.get(position);
        if(mod.getUserSrc().getId()==1){
            return R.layout.item_container_sent_message;
        }
        else{
            return R.layout.item_container_received_message;
        }
    }
}
