package com.sales.messaging.service;

import com.sales.messaging.exception.InvalidInputException;
import com.sales.messaging.model.AdjustmentOperation;
import com.sales.messaging.model.ProductType;
import com.sales.messaging.model.Sale;
import com.sales.messaging.repository.SaleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class MessageProcessorTest {

    MessageProcessor messageProcessor = new MessageProcessorImpl();
    SaleRepository saleRepository = new SaleRepository();

    @Before
    public void initializeSales(){
        saleRepository.saveSale(new Sale(ProductType.APPLE,20));
        saleRepository.saveSale(new Sale(ProductType.APPLE,20));
        saleRepository.saveSale(new Sale(ProductType.ORANGE,30));
        saleRepository.saveSale(new Sale(ProductType.ORANGE,30));
        saleRepository.saveSale(new Sale(ProductType.ORANGE,30));
    }
    @Test
    public void testAdjustSales_AddOperation(){
        Sale adjustmentSale = new Sale(ProductType.ORANGE,5);
        try {
            messageProcessor.adjustSales(adjustmentSale, AdjustmentOperation.ADD);
            List<Sale> orangeSales = saleRepository.getSalesByProductType(adjustmentSale.getProductType());
            orangeSales.stream().forEach(sale -> Assert.assertEquals(sale.getValue(),35.0,0.000001));
        } catch (InvalidInputException e) {
            Assert.fail("Exception should not be thrown");
        }
    }
    @Test
    public void testAdjustSales_SubtractOperation(){
        Sale adjustmentSale = new Sale(ProductType.ORANGE,5);
        try {
            messageProcessor.adjustSales(adjustmentSale, AdjustmentOperation.SUBTRACT);
            List<Sale> orangeSales = saleRepository.getSalesByProductType(adjustmentSale.getProductType());
            orangeSales.stream().forEach(sale -> Assert.assertEquals(sale.getValue(),25.0,0.000001));
        } catch (InvalidInputException e) {
            Assert.fail("Exception should not be thrown");
        }
    }
    @Test(expected = InvalidInputException.class)
    public void testAdjustSales_InvalidSubtractOperation()throws InvalidInputException {
        Sale adjustmentSale = new Sale(ProductType.APPLE,25);
        messageProcessor.adjustSales(adjustmentSale, AdjustmentOperation.SUBTRACT);
    }
    @Test
    public void testAdjustSales_MultiplyOperation(){
        Sale adjustmentSale = new Sale(ProductType.APPLE,0.5);
        try {
            messageProcessor.adjustSales(adjustmentSale, AdjustmentOperation.MULTIPLY);
            List<Sale> orangeSales = saleRepository.getSalesByProductType(adjustmentSale.getProductType());
            orangeSales.stream().forEach(sale -> Assert.assertEquals(sale.getValue(),10.0,0.000001));
        } catch (InvalidInputException e) {
            Assert.fail("Exception should not be thrown");
        }
    }

    @Test(expected = InvalidInputException.class)
    public void testAdjustSales_InvalidOperation() throws InvalidInputException {
        Sale adjustmentSale = new Sale(ProductType.APPLE,0.5);
        messageProcessor.adjustSales(adjustmentSale, null);
    }
}
