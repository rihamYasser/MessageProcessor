package com.sales.messaging.service;

import com.sales.messaging.exception.InvalidInputException;
import com.sales.messaging.model.AdjustmentOperation;
import com.sales.messaging.model.Message;
import com.sales.messaging.model.MessageType;
import com.sales.messaging.model.Sale;
import com.sales.messaging.repository.SaleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class MultipleSalesMessageProcessorTest {

    MultipleSalesMessageProcessor messageProcessor = new MultipleSalesMessageProcessor();
    SaleRepository saleRepository = SaleRepository.getInstance();

    @Before
    public void clear(){
        saleRepository.clearAllSales();
    }

    @Test
    public void testProcessMessage(){
        Message message = new Message();
        message.setType(MessageType.MULTIPLE_SALES_MESSAGE);
        message.setSale(new Sale("ORANGE",5));
        message.setOccurrences(10);
        try {
            messageProcessor.processMessage(message);
            List<Sale> orangeSales = saleRepository.getSalesByProductType("Orange");
            Assert.assertEquals(orangeSales.size(), 10);
            orangeSales.stream().forEach(sale -> Assert.assertEquals(sale.getValue(),5.0,0.000001));
        } catch (Exception e) {
            Assert.fail("Exception should not be thrown");
        }
    }

}
