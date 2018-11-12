package com.sales.messaging.service;

import com.sales.messaging.model.ProductSale;
import com.sales.messaging.model.ProductType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class ReportingServiceTest {

    ReportingServiceImp reportingService = new ReportingServiceImp();

    @Test
    public void testReportProductSales(){
        Map<ProductType,ProductSale> productSalesMap = new HashMap<>();
        productSalesMap.put(ProductType.TOMATOES,new ProductSale(10,20.0));
        productSalesMap.put(ProductType.APPLE,new ProductSale(2,10.0));
        productSalesMap.put(ProductType.BANANA,new ProductSale(5,50.0));
        reportingService.reportProductSales(productSalesMap);

    }
}
