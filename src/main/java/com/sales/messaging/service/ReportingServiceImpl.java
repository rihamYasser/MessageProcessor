package com.sales.messaging.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sales.messaging.model.Sale;
import com.sales.messaging.model.SaleAdjustment;

import java.util.List;
import java.util.Map;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class ReportingServiceImpl implements ReportingService {

    @Override
    public String reportProductSales(Map<String, List<Sale>> productSalesMap){
        System.out.println("--------- Products Sales Report----------");
        StringBuilder report = new StringBuilder();
        productSalesMap.entrySet().stream().forEach(entry -> {
            report.append("Product Type: ");
            report.append(entry.getKey());
            report.append(", #Sales: ");
            report.append(entry.getValue().size());
            report.append(", Total Value: ");
            report.append(entry.getValue().stream().mapToDouble(Sale::getValue).sum());
            report.append("\n");
        });
        System.out.println(report);
        return report.toString();
    }

    @Override
    public String reportSalesAdjustments(Map<String, List<SaleAdjustment>> salesAdjustment){
        System.out.println("Application is Pausing...");
        System.out.println("--------------- Sales Adjustments ---------------");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String report = gson.toJson(salesAdjustment);
        System.out.println(report);
        return report;
    }
}
