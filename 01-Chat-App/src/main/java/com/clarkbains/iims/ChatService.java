package com.clarkbains.iims;

import java.util.HashMap;


public class ChatService {
    HashMap<String,ChatRoom> messages;

    public ChatService(){
        messages = new HashMap<String,ChatRoom>();
    }

    public void addMessage(String chat, String from, String msg){

        String out = from + ": " + msg;

        if (!messages.containsKey(chat)){
            messages.put(chat, new ChatRoom());
        }

        messages.get(chat).msgs.add(out);
    }
    
    public ChatRoom getMessages(String chat){
        return messages.get(chat);
    }
    
}