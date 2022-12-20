package com.example.demo.delivery;

import com.example.demo.van.Van;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository repository;

    @Override
    public List<Delivery> getAll() {
        return repository.findAll();
    }

    @Override
    public Delivery create(Delivery object) {
        return repository.save(object);
    }

    @Override
    public Delivery update(long id, Delivery request) {
        Delivery object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        object.update(request);
        return repository.save(object);
    }

    @Override
    public void delete(long id) {
        Delivery object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        repository.delete(object);
    }

    @Override
    public Delivery getById(long id) {
        Delivery object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        return object;
    }

    @Override
    public Delivery setVan(long id, Van van) {
        Delivery object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        object.setVan(van);
        repository.save(object);
        return object;
    }

    @Override
    public List<Delivery> findByVanId(long id) {
        return  repository.findDeliveriesByVanId(id);
    }
}
