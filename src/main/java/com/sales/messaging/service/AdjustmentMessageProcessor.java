package com.sales.messaging.service;

import com.sales.messaging.exception.InvalidInputException;
import com.sales.messaging.model.AdjustmentOperation;
import com.sales.messaging.model.Message;
import com.sales.messaging.model.Sale;
import com.sales.messaging.repository.MessageRepository;
import com.sales.messaging.repository.SaleRepository;

import java.util.List;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class AdjustmentMessageProcessor extends MessageProcessorAbstract {
    @Override
    public void processMessage(Message message) throws Exception {
        adjustSales(message.getSale(),message.getAdjustmentOperation());
        MessageRepository.getInstance().saveMessage(message);
        printReport();
    }


    public void adjustSales(Sale adjustmentSale, AdjustmentOperation operation) throws InvalidInputException {

        if(operation == null){
            throw new InvalidInputException("Operation Type should be provided in Adjustment Message Type");
        }
        List<Sale> productSales = SaleRepository.getInstance().getSalesByProductType(adjustmentSale.getProductType());
        switch(operation)
        {
            case ADD:
                productSales.stream().forEach(sale -> sale.setValue(sale.getValue()+adjustmentSale.getValue()));
                break;
            case SUBTRACT:
                for(Sale sale: productSales) {
                    if (adjustmentSale.getValue() > sale.getValue()) {
                        throw new InvalidInputException("The value provided for adjustment will produce negative " +
                                "value for product: "+adjustmentSale.getProductType());
                    }
                    sale.setValue(sale.getValue() - adjustmentSale.getValue());
                }
                break;
            case MULTIPLY:
                productSales.stream().forEach(sale -> sale.setValue(sale.getValue()*adjustmentSale.getValue()));
                break;
            default:
                throw new InvalidInputException("Unsupported Operation Type");
        }
        SaleRepository.getInstance().logAdjustment(adjustmentSale, operation);
    }

}
