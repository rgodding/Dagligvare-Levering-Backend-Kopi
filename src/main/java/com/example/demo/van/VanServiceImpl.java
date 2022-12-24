package com.example.demo.van;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VanServiceImpl implements VanService{

    private final VanRepository repository;

    @Override
    public List<Van> getAll() {
        return repository.findAll();
    }

    @Override
    public Van create(Van object) {
        return repository.save(object);
    }

    @Override
    public Van update(long id, Van request) {
        Van object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        object.update(request);
        return repository.save(object);
    }

    @Override
    public void delete(long id) {
        Van object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        repository.delete(object);
    }

    @Override
    public Van getById(long id) {
        Van object = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID %d not found."));
        return object;
    }

}
