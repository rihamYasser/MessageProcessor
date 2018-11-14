package com.sales.messaging.repository;

import com.sales.messaging.model.AdjustmentOperation;
import com.sales.messaging.model.ProductType;
import com.sales.messaging.model.Sale;
import com.sales.messaging.model.SaleAdjustment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 * DAO for Sale, currently sale objects are just saved in memory.
 * This class should be changed later to persist Sale in Database.
 * If we will use Spring, interface for this implementation should be created and Singleton pattern will not be
 * needed then as the bean will be managed and injected by Spring.
 */

public class SaleRepository {

    private static final SaleRepository saleRepository = new SaleRepository();

    private static List<Sale> sales = new ArrayList<Sale>();
    private static Map<ProductType,List<Sale>> productSalesMap = new HashMap<>();
    private static Map<ProductType,List<SaleAdjustment>> saleAdjustmentsMap = new HashMap<>();

    private SaleRepository(){
    }

    public static final SaleRepository getInstance(){
        return saleRepository;
    }


    public void saveSale(Sale sale){
        sales.add(sale);
        saveProductSale(sale);
    }

    public void saveProductSale(Sale sale) {
            if(productSalesMap.containsKey(sale.getProductType())){
                productSalesMap.get(sale.getProductType()).add(sale);
            }else{
                List<Sale> salesList = new ArrayList<>();
                salesList.add(sale);
                productSalesMap.put(sale.getProductType(),salesList);
            }
    }

    public void logAdjustment(Sale sale, AdjustmentOperation operation) {
        if(saleAdjustmentsMap.containsKey(sale.getProductType())){
            saleAdjustmentsMap.get(sale.getProductType()).add(new SaleAdjustment(sale.getValue(),
                    operation));
        }else{
            List<SaleAdjustment> adjustments = new ArrayList<>();
            adjustments.add(new SaleAdjustment(sale.getValue(),operation));
            saleAdjustmentsMap.put(sale.getProductType(),adjustments);
        }
    }

    public List<Sale> getSalesByProductType (final ProductType productType){
        return productSalesMap.get(productType);
    }

    public List<Sale> getSales() {
        return sales;
    }

    public Map<ProductType,List<Sale>> getProductSalesMap() {
        return productSalesMap;
    }

    public Map<ProductType, List<SaleAdjustment>> getSaleAdjustmentsMap() {
        return saleAdjustmentsMap;
    }

    public void clearAllSales() {
        sales = new ArrayList<Sale>();
        productSalesMap = new HashMap<>();
        saleAdjustmentsMap = new HashMap<>();
    }
}
