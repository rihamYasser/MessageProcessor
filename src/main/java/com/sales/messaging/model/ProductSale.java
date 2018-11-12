package com.sales.messaging.model;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 * This class is used to represent sales details of each product while reporting
 */
public class ProductSale {
    private int numberOfSales;
    private double totalValue;

    public ProductSale(int numberOfSales, double totalValue) {
        this.numberOfSales = numberOfSales;
        this.totalValue = totalValue;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
