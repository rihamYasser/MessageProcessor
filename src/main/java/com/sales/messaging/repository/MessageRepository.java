package com.sales.messaging.repository;

import com.sales.messaging.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 *
 * DAO for message, currently messages objects are just saved in memory.
 * This class should be changed later to persist Message in Database.
 * If we will use Spring, interface for this implementation should be created and Singleton pattern will not be
 * needed then as the bean will be managed and injected by Spring.
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
