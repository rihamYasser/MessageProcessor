package com.sales.messaging;

import com.google.gson.Gson;
import com.sales.messaging.model.Message;
import com.sales.messaging.service.MessageProcessor;
import com.sales.messaging.service.MessageProcessorAbstract;
import com.sales.messaging.service.MessageProcessorFactory;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class MessageReceiver {


    public MessageReceiver(){

    }

    public String receiveMessage(String messageJson){
        try {
            Gson jsonObj = new Gson();
            Message message = jsonObj.fromJson(messageJson, Message.class);
            MessageProcessor messageProcessor = MessageProcessorFactory.createMessageProcessor(message.getType());
            messageProcessor.processMessage(message);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error Occurred during message processing:"+e.getMessage();
        }
    }

}
