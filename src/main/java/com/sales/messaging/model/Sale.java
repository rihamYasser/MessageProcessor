package com.sales.messaging.model;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class Sale {

    //For simplicity we have product type directly in Sale model, however it is better to have model for
    //Product and product type will be one of product properties/fields
    private ProductType productType;
    private int value;

    public Sale(Sale sale) {
        this.productType = sale.getProductType();
        this.value = sale.getValue();
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
