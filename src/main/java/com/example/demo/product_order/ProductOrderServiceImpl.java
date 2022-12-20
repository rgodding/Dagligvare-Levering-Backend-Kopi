package com.example.demo.product_order;

import com.example.demo.delivery.Delivery;
import com.example.demo.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService{

    private final ProductOrderRepository repository;

    @Override
    public List<ProductOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public ProductOrder create(ProductOrder object) {
        return repository.save(object);
    }

    @Override
    public ProductOrder update(long id, ProductOrder request) {
        ProductOrder object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        object.update(request);
        return repository.save(object);
    }

    @Override
    public void delete(long id) {
        ProductOrder object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        repository.delete(object);
    }

    @Override
    public ProductOrder getById(long id) {
        ProductOrder object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        return object;
    }

    @Override
    public ProductOrder setDelivery(long id, Delivery delivery) {
        ProductOrder object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        object.setDelivery(delivery);
        repository.save(object);
        return object;
    }

    @Override
    public ProductOrder setProduct(long id, Product product) {
        ProductOrder object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        object.setProduct(product);
        repository.save(object);
        return object;
    }

    @Override
    public List<ProductOrder> findByDeliveryId(long id) {
        return  repository.findProductOrderByDeliveryId(id);
    }


}
