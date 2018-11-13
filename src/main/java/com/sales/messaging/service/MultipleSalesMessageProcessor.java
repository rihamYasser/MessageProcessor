package com.sales.messaging.service;

import com.sales.messaging.model.Message;
import com.sales.messaging.model.Sale;
import com.sales.messaging.repository.MessageRepository;
import com.sales.messaging.repository.SaleRepository;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class MultipleSalesMessageProcessor extends MessageProcessorAbstract {

    @Override
    public void processMessage(Message message) throws Exception {

        int numberOfSales = message.getOccurrences();
        while (numberOfSales > 0) {
            Sale sale = new Sale(message.getSale());
            SaleRepository.getInstance().saveSale(sale);
            numberOfSales--;
        }
        MessageRepository.getInstance().saveMessage(message);
        printReport();
    }


}
