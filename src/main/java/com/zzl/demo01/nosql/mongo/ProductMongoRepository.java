package com.zzl.demo01.nosql.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<Product,String> {

}
