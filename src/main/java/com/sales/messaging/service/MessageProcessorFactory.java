package com.sales.messaging.service;

import com.sales.messaging.exception.InvalidInputException;
import com.sales.messaging.model.MessageType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class MessageProcessorFactory {

    private static Map<MessageType,MessageProcessor> processorsMap = new HashMap<>();

    /**Factory method for creating instance of MessageProcessor depending on the message type.
     * It saves the created instance in processorMap so it can returned directly next time MessageProcessor of same
     * type is needed
     * @param messageType
     * @return instance of MessageProcessor
     * @throws InvalidInputException
     */
    public static MessageProcessor createMessageProcessor(MessageType messageType) throws InvalidInputException {
        if(messageType == null){
            throw new InvalidInputException("Message Type should be provided");
        }
        MessageProcessor messageProcessor;
        switch (messageType){
            case SINGLE_SALE_MESSAGE:
                if(processorsMap.containsKey(MessageType.SINGLE_SALE_MESSAGE)){
                    messageProcessor = processorsMap.get(MessageType.SINGLE_SALE_MESSAGE);
                }else{
                    messageProcessor = new SingleSaleMessageProcessor();
                    processorsMap.put(MessageType.SINGLE_SALE_MESSAGE,messageProcessor);
                }
                break;
            case MULTIPLE_SALES_MESSAGE:
                if(processorsMap.containsKey(MessageType.MULTIPLE_SALES_MESSAGE)){
                    messageProcessor = processorsMap.get(MessageType.MULTIPLE_SALES_MESSAGE);
                }else{
                    messageProcessor = new MultipleSalesMessageProcessor();
                    processorsMap.put(MessageType.MULTIPLE_SALES_MESSAGE,messageProcessor);
                }
                break;
            case ADJUSTMENT_MESSAGE:
                if(processorsMap.containsKey(MessageType.ADJUSTMENT_MESSAGE)){
                    messageProcessor = processorsMap.get(MessageType.ADJUSTMENT_MESSAGE);
                }else{
                    messageProcessor = new AdjustmentMessageProcessor();
                    processorsMap.put(MessageType.ADJUSTMENT_MESSAGE,messageProcessor);
                }
                break;
            default:
                throw new InvalidInputException("Invalid Message Type");
        }
        return messageProcessor;
    }
}
