package com.example.demo.product_order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findProductOrderByDeliveryId(long id);
}
