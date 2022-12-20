package com.example.demo.product_order;

import com.example.demo.delivery.Delivery;
import com.example.demo.product.Product;

import java.util.List;

public interface ProductOrderService {
    List<ProductOrder> getAll();
    ProductOrder create(ProductOrder object);
    ProductOrder update(long id, ProductOrder object);
    void delete(long id);
    ProductOrder getById(long id);

    ProductOrder setDelivery(long id, Delivery delivery);
    ProductOrder setProduct(long id, Product product);

    List<ProductOrder> findByDeliveryId(long id);


}
