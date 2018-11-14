package com.sales.messaging;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sales.messaging.model.Message;
import com.sales.messaging.model.Response;
import com.sales.messaging.model.ResponseStatus;
import com.sales.messaging.service.MessageProcessor;
import com.sales.messaging.service.MessageProcessorAbstract;
import com.sales.messaging.service.MessageProcessorFactory;

import java.util.List;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 * This class acts as the messaging application interface, if we will use Spring this can be Rest Controller and
 */
public class MessageReceiver {


    public MessageReceiver(){

    }

    /**
     * Receive message in json format and get MessageProcessor instance from MessageProcessorFactory to process the
     * message.
     * @param messageJson
     * @return json represents Response object
     */

    public String receiveMessage(String messageJson){

        Gson jsonObj = new Gson();
        Response response = new Response();
        if(messageJson == null || messageJson.equals("")){
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("No Message is provided!");
        }
        try {
            Message message = jsonObj.fromJson(messageJson, Message.class);
            MessageProcessor messageProcessor = MessageProcessorFactory.createMessageProcessor(message.getType());
            messageProcessor.processMessage(message);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("Message has been processed successfully");
        } catch (Exception e) {
            //e.printStackTrace(); //Uncomment for debugging purpose, it should be replaced by proper logging
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(e.getMessage());
        }
        return jsonObj.toJson(response);
    }

    /**
     * Receive list of messages in json format and process each message
     * the
     * message.
     * @param messagesJson
     * @return json represents Response object
     */
    public String receiveBulkMessage(String messagesJson) {
        Gson jsonObj = new Gson();
        Response response = new Response();
        if(messagesJson == null || messagesJson.equals("")){
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("No Message is provided!");
        }
        try {

            List<Message> messages = jsonObj.fromJson(messagesJson, new TypeToken<List<Message>>(){}.getType());
            for (Message message : messages) {
                MessageProcessor messageProcessor = MessageProcessorFactory.createMessageProcessor(message.getType());
                messageProcessor.processMessage(message);
            }
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("Bulk Message has been processed successfully");
        } catch (Exception e) {
            //e.printStackTrace(); //Uncomment for debugging purpose, it should be replaced by proper logging
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(e.getMessage());
        }
        return jsonObj.toJson(response);
    }
}
