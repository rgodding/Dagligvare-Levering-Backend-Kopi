package com.example.demo.product;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product create(Product object) {
        return repository.save(object);
    }

    @Override
    public Product update(long id, Product request) {
        Product object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        object.update(request);
        return repository.save(object);
    }

    @Override
    public void delete(long id) {
        Product object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        repository.delete(object);
    }

    @Override
    public Product getById(long id) {
        Product object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        return object;
    }

    @Override
    public Product getByName(String name){
        return repository.findByName(name);
    }

    @Override
    public List<Product> findAllByName(String input) {
        return repository.findAllByNameContainingIgnoreCase(input);
    }

}
