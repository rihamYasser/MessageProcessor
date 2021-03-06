package com.sales.messaging.service;

import com.sales.messaging.exception.InvalidInputException;
import com.sales.messaging.model.AdjustmentOperation;
import com.sales.messaging.model.Message;
import com.sales.messaging.model.Sale;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public interface MessageProcessor {
    boolean processMessage(Message message) throws Exception;
    boolean printReport();
}
