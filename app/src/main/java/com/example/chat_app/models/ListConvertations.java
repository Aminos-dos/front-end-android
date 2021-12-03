package com.example.chat_app.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.chat_app.R;
import java.util.ArrayList;

public class ListConvertations extends ArrayAdapter<Convertation> {


    public ListConvertations(Context context, ArrayList<Convertation> convertations) {
        super(context, R.layout.list_item,convertations);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Convertation convertation = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.image_profil);
        TextView userName = convertView.findViewById(R.id.userName);
        TextView descUser = convertView.findViewById(R.id.descUser);
        TextView status = convertView.findViewById(R.id.isFriends);

        imageView.setImageResource(R.drawable.said);
        userName.setText(convertation.getUser().getName());
        descUser.setText(convertation.getLastMessage());
        status.setText(convertation.getTime());
        return convertView;
    }
}
