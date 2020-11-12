package com.zzl.demo01.service.impl;

import com.zzl.demo01.nosql.mongo.Product;
import com.zzl.demo01.nosql.mongo.ProductMongoRepository;
import com.zzl.demo01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMongoRepository pmr;

    @Override
    public int create(Product pm) {
        pmr.save(pm);
        return 1;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public List<Product> list() {
        return pmr.findAll();
    }
}
