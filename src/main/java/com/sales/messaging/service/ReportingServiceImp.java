package com.sales.messaging.service;

import com.sales.messaging.model.Message;
import com.sales.messaging.model.ProductSale;
import com.sales.messaging.model.ProductType;

import java.util.List;
import java.util.Map;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class ReportingServiceImp implements ReportingService {

    @Override
    public void reportProductSales(Map<ProductType, ProductSale> productSalesMap){
        System.out.println("++++++++++++++ 10 Messages Report +++++++++++++++");
        System.out.println("Product     | # Sales | Value");
        System.out.println("------------|---------|------");
        productSalesMap.forEach((productType,productSale) -> System.out.println(productType.getDisplayName()+"  | " +
                ""+productSale
                .getNumberOfSales()+"       | "+productSale.getTotalValue()));

    }

    @Override
    public void reportSalesAdjustment(List<Message> messages){
        System.out.println("Application is Pausing");
        //TODO log adjustments
    }
}
