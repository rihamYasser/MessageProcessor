package com.sales.messaging.service;

import com.sales.messaging.exception.InvalidInputException;
import com.sales.messaging.repository.MessageRepository;
import com.sales.messaging.repository.SaleRepository;
import com.sales.messaging.model.AdjustmentOperation;
import com.sales.messaging.model.Message;
import com.sales.messaging.model.MessageType;
import com.sales.messaging.model.Sale;

import java.util.List;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class MessageProcessorImpl implements MessageProcessor{

    private MessageRepository messageRepository;
    private SaleRepository saleRepository;
    private ReportingService reportingService;

    private static int PRODUCT_SALE_REPORT_THRESHOLD = 5;
    private static int SALE_ADJUSTMENT_REPORT_THRESHOLD = 10;

    public MessageProcessorImpl(){
        messageRepository = new MessageRepository();
        saleRepository = new SaleRepository();
        reportingService = new ReportingServiceImp();
    }

    public void processMessage(Message message) throws Exception {

        if(MessageType.MESSAGE_TYPE_1.equals(message.getType())){
            saleRepository.saveSale(message.getSale());
            saleRepository.saveProductSale(message.getSale());
        }else if(MessageType.MESSAGE_TYPE_2.equals(message.getType())){
            int numberOfSales = message.getOccurrences();
            while(numberOfSales > 0){
                Sale sale = new Sale(message.getSale());
                saleRepository.saveSale(sale);
                numberOfSales--;
                saleRepository.saveProductSale(sale);
            }
        }else if(MessageType.MESSAGE_TYPE_3.equals(message.getType())){
            adjustSales(message.getSale(),message.getAdjustmentOperation());

        }else{
            throw new Exception("Invalid message Type");
        }
        messageRepository.saveMessage(message);
        printReport();

    }

    public void adjustSales(Sale adjustmentSale, AdjustmentOperation operation) throws InvalidInputException {

        if(operation == null){
            throw new InvalidInputException("Operation Type should be provided in Adjustment Message Type");
        }
        List<Sale> productSales = saleRepository.getSalesByProductType(adjustmentSale.getProductType());
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
        saleRepository.logAdjustment(adjustmentSale, operation);
    }

    public void printReport() {
        if(messageRepository.getMessagesListSize() % PRODUCT_SALE_REPORT_THRESHOLD == 0){
            reportingService.reportProductSales(saleRepository.getProductSalesMap());
        }
        if(messageRepository.getMessagesListSize() == SALE_ADJUSTMENT_REPORT_THRESHOLD){
            reportingService.reportSalesAdjustments(saleRepository.getSaleAdjustmentsMap());
        }
    }

}
