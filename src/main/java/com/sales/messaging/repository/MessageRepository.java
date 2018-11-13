package com.sales.messaging.repository;

import com.sales.messaging.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 * DAO for messages objects, currently the messages is just saved in memory in ArrayList
 * This class should be changed if we change the way of storing messages
 */
public class MessageRepository {

    private static final MessageRepository messageRepository = new MessageRepository();
    private static final List<Message> messagesList = new ArrayList<Message>();

    private MessageRepository(){

    }
    public static MessageRepository getInstance(){
        return messageRepository;
    }

    public void saveMessage(Message message){
        messagesList.add(message);
    }

    public int getMessagesListSize(){
        return messagesList.size();
    }

    public List<Message> getMessagesList() {
        return messagesList;
    }
}
