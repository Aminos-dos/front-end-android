package com.example.chat_app;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_app.models.Message;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView simpleTextView;
    private TextView simpleDateView;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        simpleTextView = (TextView) itemView.findViewById(R.id.textMessage);
        simpleDateView = (TextView) itemView.findViewById(R.id.textDateTime);
    }
    public void bindData(final Message viewModel) {
        simpleTextView.setText(viewModel.getContent());
        simpleDateView.setText(viewModel.getAdded_date()+"");
    }
}
