package com.sales.messaging.service;

import com.sales.messaging.model.Message;
import com.sales.messaging.repository.MessageRepository;
import com.sales.messaging.repository.SaleRepository;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class SingleSaleMessageProcessor extends MessageProcessorAbstract {

    @Override
    public void processMessage(Message message) throws Exception {
        SaleRepository.getInstance().saveSale(message.getSale());
        MessageRepository.getInstance().saveMessage(message);
        printReport();
    }
}
