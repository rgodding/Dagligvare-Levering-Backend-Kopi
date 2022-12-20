package com.example.demo.product;


import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product create(Product object);
    Product update(long id, Product object);
    void delete(long id);
    Product getById(long id);
    Product getByName(String name);

}

