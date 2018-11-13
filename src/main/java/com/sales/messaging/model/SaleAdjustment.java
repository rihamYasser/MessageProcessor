package com.sales.messaging.model;

/**
 * Created by riham.y.abdelmaksoud on 11/12/2018.
 */
public class SaleAdjustment {

    private AdjustmentOperation operation;
    private double value;


    public SaleAdjustment(double value, AdjustmentOperation operation) {
        this.value = value;
        this.operation = operation;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public AdjustmentOperation getOperation() {
        return operation;
    }

    public void setOperation(AdjustmentOperation operation) {
        this.operation = operation;
    }
}
