package com.sales.messaging.service;

import com.sales.messaging.exception.InvalidInputException;
import com.sales.messaging.model.MessageType;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by riham.y.abdelmaksoud on 11/14/2018.
 */
public class MessageProcessorFactoryTest {

    @Test(expected = InvalidInputException.class)
    public void testCreateMessageProcessor_InvalidMessageType() throws InvalidInputException {
        MessageProcessorFactory.createMessageProcessor(null);
    }
    @Test
    public void testCreateMessageProcessor_SinlgeSaleMessageType() throws InvalidInputException {
        MessageProcessor messageProcessor = MessageProcessorFactory.createMessageProcessor(MessageType
                .SINGLE_SALE_MESSAGE);
        Assert.assertThat(messageProcessor, CoreMatchers.instanceOf(SingleSaleMessageProcessor.class));
    }

    @Test
    public void testCreateMessageProcessor_MultiSaleMessageType() throws InvalidInputException {
        MessageProcessor messageProcessor = MessageProcessorFactory.createMessageProcessor(MessageType
                .MULTIPLE_SALES_MESSAGE);
        Assert.assertThat(messageProcessor, CoreMatchers.instanceOf(MultipleSalesMessageProcessor.class));
    }

    @Test
    public void testCreateMessageProcessor_AdjustMessageType() throws InvalidInputException {
        MessageProcessor messageProcessor = MessageProcessorFactory.createMessageProcessor(MessageType
                .ADJUSTMENT_MESSAGE);
        Assert.assertThat(messageProcessor, CoreMatchers.instanceOf(AdjustmentMessageProcessor.class));
    }
}
