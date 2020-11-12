package com.zzl.demo01.service;

import com.zzl.demo01.nosql.mongo.Product;

import java.util.List;

public interface ProductService {
    int create(Product pm);
    int delete(String id);
    List<Product> list();
}
