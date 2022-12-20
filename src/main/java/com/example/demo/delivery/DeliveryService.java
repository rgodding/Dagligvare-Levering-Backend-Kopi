package com.example.demo.delivery;

import com.example.demo.van.Van;

import java.util.List;

public interface DeliveryService {
    List<Delivery> getAll();
    Delivery create(Delivery object);
    Delivery update(long id, Delivery object);
    void delete(long id);
    Delivery getById(long id);

    // Independent Methods
    Delivery setVan(long id, Van van);

    List<Delivery> findByVanId(long id);

}
