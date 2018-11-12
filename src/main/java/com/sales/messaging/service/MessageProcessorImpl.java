package com.sales.messaging.service;

import com.sales.messaging.repository.MessageRepository;
import com.sales.messaging.repository.SaleRepository;
import com.sales.messaging.model.AdjustmentOperation;
import com.sales.messaging.model.Message;
import com.sales.messaging.model.MessageType;
import com.sales.messaging.model.Sale;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class MessageProcessorImpl implements MessageProcessor{

    private MessageRepository messageRepository;
    private SaleRepository saleRepository;
    private ReportingServiceImp reportingService;

    private static int PRODUCT_SALE_REPORT_THRESHOLD = 10;
    private static int SALE_ADJUSTMENT_REPORT_THRESHOLD = 50;

    public MessageProcessorImpl(){
        messageRepository = new MessageRepository();
        saleRepository = new SaleRepository();
        reportingService = new ReportingServiceImp();
    }

    public void processMessage(Message message) throws Exception {

        if(MessageType.MESSAGE_TYPE_1.equals(message.getType())){
            saleRepository.saveSale(message.getSale());
        }else if(MessageType.MESSAGE_TYPE_2.equals(message.getType())){
            int numberOfSales = message.getOccurrences();
            while(numberOfSales > 0){
                Sale sale = new Sale(message.getSale());
                saleRepository.saveSale(sale);
                numberOfSales--;
            }
        }else if(MessageType.MESSAGE_TYPE_3.equals(message.getType())){
            adjustSales(message.getSale(),message.getOperation());

        }else{
            throw new Exception("Invalid message Type");
        }
        printReport();
        messageRepository.saveMessage(message);
    }

    private void adjustSales(Sale sale, AdjustmentOperation operation) {
        //TODO do adjustment to all sales
    }

    public void printReport() {
        if(messageRepository.getMessagesListSize() == PRODUCT_SALE_REPORT_THRESHOLD){
            reportingService.reportProductSales(saleRepository.createProductSalesMap());
        }else if(messageRepository.getMessagesListSize() == SALE_ADJUSTMENT_REPORT_THRESHOLD){

        }
    }

}
