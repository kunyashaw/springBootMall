package com.zzl.demo01.nosql.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String name;
    private int price;
}
