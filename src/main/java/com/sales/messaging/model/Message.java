package com.sales.messaging.model;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class Message {
    private String id;
    private MessageType type;
    private Sale sale;
    private int occurrences;
    private AdjustmentOperation operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public AdjustmentOperation getOperation() {
        return operation;
    }

    public void setOperation(AdjustmentOperation operation) {
        this.operation = operation;
    }
}
