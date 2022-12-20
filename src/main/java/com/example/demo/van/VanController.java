package com.example.demo.van;

import com.example.demo.dto.VanDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/van")
public class VanController {

    @Autowired
    private ModelMapper modelMapper;

    private final VanService service;

    @GetMapping
    public ResponseEntity<List<VanDto>> findAll() {
        List<VanDto> objects = service.getAll().stream().map(object -> modelMapper.map(object, VanDto.class))
                .toList();
        return ResponseEntity.ok().body(objects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VanDto> getById(@PathVariable(name = "id") Long id){
        Van object = service.getById(id);

        // convert entity to DTO
        VanDto response = modelMapper.map(object, VanDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<VanDto> create(@Valid @RequestBody VanDto dto){

        // convert DTO to entity
        Van request = modelMapper.map(dto, Van.class);

        Van object = service.create(request);

        // convert entity to DTO
        VanDto response = modelMapper.map(object, VanDto.class);

        return new ResponseEntity<VanDto>(response, HttpStatus.CREATED);
    }

    // change request for DTO
    // change the response for DTO

    @PutMapping("/{id}")
    public ResponseEntity<VanDto> update(@PathVariable long id, @RequestBody VanDto dto){
        // concert DTO to Entity
        Van request = modelMapper.map(dto, Van.class);

        Van object = service.update(id, request);

        // entity to DTO
        VanDto response = modelMapper.map(object, VanDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VanDto> patch(@PathVariable Long id, @Valid @RequestBody Van object){
        Van result = service.update(id, object);
        return ResponseEntity.ok().body(modelMapper.map(result, VanDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<String>("OBJECT(id = " + id + ") deleted from database.", HttpStatus.OK);
    }

}
