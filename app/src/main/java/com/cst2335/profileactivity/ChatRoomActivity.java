package com.cst2335.profileactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    private ArrayList<Message> messageList;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        // Initialize messageList and adapter
        messageList = new ArrayList<>();
        adapter = new ChatAdapter(this, messageList);

        // Set up ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Set up EditText, Send, and Receive buttons
        EditText editTextTypeHere = findViewById(R.id.editTextTypeHere);
        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(editTextTypeHere.getText().toString(), Message.MessageType.SEND);
                editTextTypeHere.setText("");
            }
        });

        findViewById(R.id.receiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(editTextTypeHere.getText().toString(), Message.MessageType.RECEIVE);
                editTextTypeHere.setText("");
            }
        });

        // Set up onItemLongClick listener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                showDeleteDialog(position, id);
                return true;
            }
        });
    }

    private void sendMessage(String text, Message.MessageType type) {
        Message message = new Message(text, type);
        messageList.add(message);
        adapter.notifyDataSetChanged();
    }

    private void showDeleteDialog(final int position, final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to delete this?")
                .setMessage("The selected row is: " + position + "\n" +
                        "The database id is: " + id)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteMessage(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing or dismiss the dialog
                    }
                })
                .show();
    }

    private void deleteMessage(int position) {
        if (position >= 0 && position < messageList.size()) {
            messageList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Message deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
