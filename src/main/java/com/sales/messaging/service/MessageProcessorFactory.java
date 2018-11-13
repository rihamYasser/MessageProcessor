package com.sales.messaging.service;

import com.sales.messaging.exception.InvalidInputException;
import com.sales.messaging.model.MessageType;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class MessageProcessorFactory {

    public static MessageProcessor createMessageProcessor(MessageType messageType) throws InvalidInputException {
        if(messageType == null){
            throw new InvalidInputException("Message Type should be provided");
        }
        MessageProcessor messageProcessor;
        switch (messageType){
            case SINGLE_SALE_MESSAGE:
                messageProcessor = new SingleSaleMessageProcessor();
                break;
            case MULTIPLE_SALES_MESSAGE:
                messageProcessor = new MultipleSalesMessageProcessor();
                break;
            case ADJUSTMENT_MESSAGE:
                messageProcessor = new AdjustmentMessageProcessor();
                break;
            default:
                throw new InvalidInputException("Invalid Message Type");
        }
        return messageProcessor;
    }
}
