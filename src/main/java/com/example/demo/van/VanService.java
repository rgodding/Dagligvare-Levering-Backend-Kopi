package com.example.demo.van;

import java.util.List;

public interface VanService {
    List<Van> getAll();
    Van create(Van object);
    Van update(long id, Van object);
    void delete(long id);
    Van getById(long id);

}
