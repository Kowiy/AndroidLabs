package com.cst2335.profileactivity;

public class Message {
    private String text;
    private MessageType type;

    // Constructors, getters, and setters

    public Message(String text, MessageType type) {
        this.text = text;
        this.type = type;
    }
    public String getText() {
        return text;
    }

    public MessageType getType() {
        return type;
    }
    public enum MessageType {
        SEND, RECEIVE
    }
}
