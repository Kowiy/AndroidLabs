package com.cst2335.profileactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<Message> {

    private Context context;
    private ArrayList<Message> messages;

    public ChatAdapter(@NonNull Context context, ArrayList<Message> messages) {
        super(context, 0, messages);
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message message = getItem(position);
        if (message != null) {
            viewHolder.bind(message);
        }

        return convertView;
    }

    static class ViewHolder {
        private TextView itemText;
        private TextView subItemText;

        ViewHolder(View view) {
            itemText = view.findViewById(R.id.itemText1);
            subItemText = view.findViewById(R.id.subItemText1);
        }

        void bind(Message message) {
            itemText.setText(message.getText());
            subItemText.setText(message.getType().toString());
        }
    }
}
