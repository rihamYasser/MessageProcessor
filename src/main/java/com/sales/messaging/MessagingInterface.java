package com.sales.messaging;

import com.google.gson.Gson;
import com.sales.messaging.model.Message;
import com.sales.messaging.service.MessageProcessor;
import com.sales.messaging.service.MessageProcessorImpl;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class MessagingInterface {
    MessageProcessor messageProcessor;

    public MessagingInterface(){
        messageProcessor = new MessageProcessorImpl();
    }
    public String sendMessage(String messageJson){
        try {
            Gson jsonObj = new Gson();
            Message message = jsonObj.fromJson(messageJson, Message.class);
            messageProcessor.processMessage(message);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error Occurred during message processing:"+e.getMessage();
        }
    }

}
