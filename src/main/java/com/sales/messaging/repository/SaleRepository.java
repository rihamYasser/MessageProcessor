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
 */
public class SaleRepository {

    private static final List<Sale> sales = new ArrayList<Sale>();
    private static final Map<ProductType,List<Sale>> productSalesMap = new HashMap<>();;
    private static final Map<ProductType,List<SaleAdjustment>> saleAdjustmentsMap = new HashMap<>();

    public SaleRepository(){
    }

    public void saveSale(Sale sale){
        sales.add(sale);
    }

    public List<Sale> getSalesByProductType (final ProductType productType){
        return productSalesMap.get(productType);
    }


    public List<Sale> getAllSales() {
        return sales;
    }

    public void saveProductSale(Sale sale) {
            if(productSalesMap.containsKey(sale.getProductType())){
                productSalesMap.get(sale.getProductType()).add(sale);
                //increment number of sales
//                productSalesMap.get(sale.getProductType()).setNumberOfSales(productSalesMap.get(sale
//                        .getProductType())
//                        .getNumberOfSales()+1);
//                //add sale value
//                productSalesMap.get(sale.getProductType()).setTotalValue(productSalesMap.get(sale.getProductType())
//                        .getTotalValue()+sale.getValue());
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

    public List<Sale> getSales() {
        return sales;
    }

    public Map<ProductType,List<Sale>> getProductSalesMap() {
        return productSalesMap;
    }

    public Map<ProductType, List<SaleAdjustment>> getSaleAdjustmentsMap() {
        return saleAdjustmentsMap;
    }
}
