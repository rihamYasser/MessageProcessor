package com.sales.messaging.service;

import com.sales.messaging.repository.MessageRepository;
import com.sales.messaging.repository.SaleRepository;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public abstract class MessageProcessorAbstract implements MessageProcessor{

    private static int PRODUCT_SALE_REPORT_THRESHOLD = 1;
    private static int SALE_ADJUSTMENT_REPORT_THRESHOLD = 10;


    protected ReportingService reportingService =  new ReportingServiceImpl();



    public void printReport() {
        if(MessageRepository.getInstance().getMessagesListSize() % PRODUCT_SALE_REPORT_THRESHOLD == 0){
            reportingService.reportProductSales(SaleRepository.getInstance().getProductSalesMap());
        }
        if(MessageRepository.getInstance().getMessagesListSize() == SALE_ADJUSTMENT_REPORT_THRESHOLD){
            reportingService.reportSalesAdjustments(SaleRepository.getInstance().getSaleAdjustmentsMap());
        }
    }

}
