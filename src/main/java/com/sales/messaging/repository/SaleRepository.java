package com.sales.messaging.repository;

import com.sales.messaging.model.ProductSale;
import com.sales.messaging.model.ProductType;
import com.sales.messaging.model.Sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public class SaleRepository {

    private List<Sale> sales ;
    private Map<ProductType,ProductSale> productSalesMap ;

    public SaleRepository(){
        sales = new ArrayList<Sale>();
        productSalesMap = new HashMap<>();
    }

    public void saveSale(Sale sale){
        sales.add(sale);
    }

    public Stream<Sale> getSalesByProductType (final ProductType productType){
        return  sales.stream().filter(sale -> sale.getProductType().equals(productType));
    }


    public List<Sale> getAllSales() {
        return sales;
    }

    public Map<ProductType,ProductSale> createProductSalesMap() {
        for(Sale sale :sales){
            if(productSalesMap.containsKey(sale.getProductType())){
                //increment number of sales
                productSalesMap.get(sale.getProductType()).setNumberOfSales(productSalesMap.get(sale
                        .getProductType())
                        .getNumberOfSales()+1);
                //add sale value
                productSalesMap.get(sale.getProductType()).setTotalValue(productSalesMap.get(sale.getProductType())
                        .getTotalValue()+sale.getValue());
            }else{
                ProductSale productSale = new ProductSale(1,sale.getValue());
                productSalesMap.put(sale.getProductType(),productSale);
            }
        }
        return productSalesMap;

    }
}
