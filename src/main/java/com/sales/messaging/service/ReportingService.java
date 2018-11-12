package com.sales.messaging.service;

import com.sales.messaging.model.Message;
import com.sales.messaging.model.ProductSale;
import com.sales.messaging.model.ProductType;

import java.util.List;
import java.util.Map;

/**
 * Created by riham.y.abdelmaksoud on 11/12/2018.
 */
public interface ReportingService {
    void reportProductSales(Map<ProductType, ProductSale> productSalesMap);
    void reportSalesAdjustment(List<Message> messages);
}

