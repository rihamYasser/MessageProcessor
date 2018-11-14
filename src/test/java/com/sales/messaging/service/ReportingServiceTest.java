package com.sales.messaging.service;

import com.sales.messaging.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class ReportingServiceTest {

    ReportingService reportingService = new ReportingServiceImpl();

    @Test
    public void testReportProductSales(){
        Map<String,List<Sale>> productSalesMap = new HashMap<>();
        productSalesMap.put("TOMATOES",Arrays.asList(new Sale("TOMATOES",10),new Sale("TOMATOES",20)));
        productSalesMap.put("APPLE",Arrays.asList(new Sale("APPLE",5),new Sale("APPLE",20),new Sale("APPLE",10)));
        productSalesMap.put("BANANA",Arrays.asList(new Sale("BANANA",5)));
        String report = reportingService.reportProductSales(productSalesMap);
        Assert.assertTrue(report.contains("Product Type: TOMATOES, #Sales: 2, Total Value: 30.0"));
        Assert.assertTrue(report.contains("Product Type: APPLE, #Sales: 3, Total Value: 35.0"));
        Assert.assertTrue(report.contains("Product Type: BANANA, #Sales: 1, Total Value: 5.0"));
    }
    @Test
    public void testReportSalesAdjustments(){
        Map<String,List<SaleAdjustment>> saleAdjustmentHashMap = new HashMap<>();
        List<SaleAdjustment> tomatoesAdjustment = Arrays.asList(new SaleAdjustment(10,
                AdjustmentOperation.ADD),new SaleAdjustment(5, AdjustmentOperation.SUBTRACT));
        saleAdjustmentHashMap.put("TOMATOES",tomatoesAdjustment);
        List<SaleAdjustment> bananaAdjustment = Arrays.asList(new SaleAdjustment(5,
                AdjustmentOperation.SUBTRACT),new SaleAdjustment(2, AdjustmentOperation.MULTIPLY));
        saleAdjustmentHashMap.put("BANANA",bananaAdjustment);
        List<SaleAdjustment> appleAdjustment = Arrays.asList(new SaleAdjustment(10,
                AdjustmentOperation.ADD));
        saleAdjustmentHashMap.put("APPLE",appleAdjustment);
        String report = reportingService.reportSalesAdjustments(saleAdjustmentHashMap);
        Assert.assertTrue(report.contains("TOMATOES"));
        Assert.assertTrue(report.contains("BANANA"));
        Assert.assertTrue(report.contains("APPLE"));
    }
}
