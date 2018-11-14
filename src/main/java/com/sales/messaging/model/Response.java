package com.sales.messaging.model;

/**
 * Created by riham.y.abdelmaksoud on 11/14/2018.
 */
public class Response {
    private ResponseStatus status;
    private String message;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
