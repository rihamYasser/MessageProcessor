package com.sales.messaging.repository;

import com.sales.messaging.model.AdjustmentOperation;
import com.sales.messaging.model.Sale;
import com.sales.messaging.model.SaleAdjustment;

import java.util.*;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 * DAO for Sale, currently sale objects are just saved in memory.
 * This class should be changed later to persist Sale in Database.
 * If we will use Spring, interface for this implementation should be created and Singleton pattern will not be
 * needed then as the bean will be managed and injected by Spring.
 */

public class SaleRepository {

    private static final SaleRepository saleRepository = new SaleRepository();

    private static List<Sale> sales = new ArrayList<>();
    private static Map<String, List<Sale>> productSalesMap = new HashMap<>();
    private static Map<String, List<SaleAdjustment>> saleAdjustmentsMap = new HashMap<>();

    private SaleRepository() {
    }

    public static final SaleRepository getInstance() {
        return saleRepository;
    }


    public void saveSale(Sale sale) {
        sales.add(sale);
        saveProductSale(sale);
    }

    public void saveProductSale(Sale sale) {
        if (sale== null || sale.getProductType() == null) {
            return;
        }
        if (productSalesMap.containsKey(sale.getProductType().toUpperCase())) {
            productSalesMap.get(sale.getProductType().toUpperCase()).add(sale);
        } else {
            List<Sale> salesList = new ArrayList<>();
            salesList.add(sale);
            productSalesMap.put(sale.getProductType().toUpperCase(), salesList);
        }
    }

    public void logAdjustment(Sale sale, AdjustmentOperation operation) {
        if (sale == null || sale.getProductType() == null) {
            return;
        }
        if (saleAdjustmentsMap.containsKey(sale.getProductType().toUpperCase())) {
            saleAdjustmentsMap.get(sale.getProductType().toUpperCase()).add(new SaleAdjustment(sale.getValue(),
                    operation));
        } else {
            List<SaleAdjustment> adjustments = new ArrayList<>();
            adjustments.add(new SaleAdjustment(sale.getValue(), operation));
            saleAdjustmentsMap.put(sale.getProductType().toUpperCase(), adjustments);
        }
    }

    public List<Sale> getSalesByProductType(final String productType) {
        if (productType == null) {
            return Collections.emptyList();
        }

        return productSalesMap.get(productType.toUpperCase());
    }

    public List<Sale> getSales() {
        return sales;
    }

    public Map<String, List<Sale>> getProductSalesMap() {
        return productSalesMap;
    }

    public Map<String, List<SaleAdjustment>> getSaleAdjustmentsMap() {
        return saleAdjustmentsMap;
    }

    public void clearAllSales() {
        sales = new ArrayList<Sale>();
        productSalesMap = new HashMap<>();
        saleAdjustmentsMap = new HashMap<>();
    }
}
