package com.sales.messaging.service;

import com.sales.messaging.model.Message;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public interface MessageProcessor {
    void processMessage(Message message) throws Exception;
    void printReport();
}
