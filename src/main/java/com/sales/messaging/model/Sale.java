package com.sales.messaging.model;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class Sale {

    //For simplicity we define product type directly in Sale model, however it is better to have separate POJO for
    //Product and then product type will be one of product properties/fields
    private ProductType productType;
    private double value;

    public Sale(Sale sale) {
        this.productType = sale.getProductType();
        this.value = sale.getValue();
    }

    public Sale(ProductType type,double value) {
        this.productType = type;
        this.value = value;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
