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
        Map<ProductType,List<Sale>> productSalesMap = new HashMap<>();
        productSalesMap.put(ProductType.TOMATOES,Arrays.asList(new Sale(ProductType.TOMATOES,10),new Sale(ProductType
                .TOMATOES,20)));
        productSalesMap.put(ProductType.APPLE,Arrays.asList(new Sale(ProductType.APPLE,5),new Sale(ProductType
                .APPLE,20),new Sale(ProductType.APPLE,10)));
        productSalesMap.put(ProductType.BANANA,Arrays.asList(new Sale(ProductType.BANANA,5)));
        String report = reportingService.reportProductSales(productSalesMap);
        Assert.assertEquals(report, "Product Type: TOMATOES, #Sales: 2, Total Value: 30.0\n" +
                "Product Type: APPLE, #Sales: 3, Total Value: 35.0\n" +
                "Product Type: BANANA, #Sales: 1, Total Value: 5.0\n");
    }
    @Test
    public void testReportSalesAdjustments(){
        Map<ProductType,List<SaleAdjustment>> saleAdjustmentHashMap = new HashMap<>();
        List<SaleAdjustment> tomatoesAdjustment = Arrays.asList(new SaleAdjustment(10,
                AdjustmentOperation.ADD),new SaleAdjustment(5, AdjustmentOperation.SUBTRACT));
        saleAdjustmentHashMap.put(ProductType.TOMATOES,tomatoesAdjustment);
        List<SaleAdjustment> bananaAdjustment = Arrays.asList(new SaleAdjustment(5,
                AdjustmentOperation.SUBTRACT),new SaleAdjustment(2, AdjustmentOperation.MULTIPLY));
        saleAdjustmentHashMap.put(ProductType.BANANA,bananaAdjustment);
        List<SaleAdjustment> appleAdjustment = Arrays.asList(new SaleAdjustment(10,
                AdjustmentOperation.ADD));
        saleAdjustmentHashMap.put(ProductType.APPLE,appleAdjustment);
        Assert.assertEquals(reportingService.reportSalesAdjustments(saleAdjustmentHashMap),"{\n" +
                "  \"TOMATOES\": [\n" +
                "    {\n" +
                "      \"operation\": \"ADD\",\n" +
                "      \"value\": 10.0\n" +
                "    },\n" +
                "    {\n" +
                "      \"operation\": \"SUBTRACT\",\n" +
                "      \"value\": 5.0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"BANANA\": [\n" +
                "    {\n" +
                "      \"operation\": \"SUBTRACT\",\n" +
                "      \"value\": 5.0\n" +
                "    },\n" +
                "    {\n" +
                "      \"operation\": \"MULTIPLY\",\n" +
                "      \"value\": 2.0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"APPLE\": [\n" +
                "    {\n" +
                "      \"operation\": \"ADD\",\n" +
                "      \"value\": 10.0\n" +
                "    }\n" +
                "  ]\n" +
                "}");
    }
}
