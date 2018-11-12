package com.sales.messaging.model;

/**
 * Created by riham.y.abdelmaksoud on 11/11/2018.
 */
public enum ProductType {
    APPLE("Apple     "),BANANA("Banana    "),ORANGE("Orange    "),MANGO("Mango     "), LEMON("Lemon     "), TOMATOES
            ("Tomatoes  ");

    private String displayName ;

    ProductType(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
