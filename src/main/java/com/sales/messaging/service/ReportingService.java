package com.sales.messaging.service;

import com.sales.messaging.model.ProductSale;
import com.sales.messaging.model.ProductType;
import com.sales.messaging.model.Sale;
import com.sales.messaging.model.SaleAdjustment;

import java.util.List;
import java.util.Map;

/**
 * Created by riham.y.abdelmaksoud on 11/12/2018.
 */
public interface ReportingService {
    String reportProductSales(Map<ProductType, List<Sale>> productSalesMap);
    String reportSalesAdjustments(Map<ProductType, List<SaleAdjustment>> messages);
}

