package com.example.demo.product;

import com.example.demo.dto.ProductDto;
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
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ModelMapper modelMapper;


    private final ProductService service;


    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<ProductDto> getByName(@PathVariable(name = "name") String name){
        Product object = service.getByName(name);
        // convert entity to DTO
        ProductDto response = modelMapper.map(object, ProductDto.class);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/find-all-by-name/{input}")
    public ResponseEntity<List<ProductDto>> getByNameContainingInput(@PathVariable(name = "input") String input){
        System.out.println("FINDING ALL BY(" + input + ")");
        List<ProductDto> objects = service.findAllByName(input).stream().map(object -> modelMapper.map(object, ProductDto.class))
                .toList();
        return ResponseEntity.ok().body(objects);
    }

    //Core Methods
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> objects = service.getAll().stream().map(object -> modelMapper.map(object, ProductDto.class))
                .toList();
        return ResponseEntity.ok().body(objects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable(name = "id") Long id){
        Product object = service.getById(id);

        // convert entity to DTO
        ProductDto response = modelMapper.map(object, ProductDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto){
        // convert DTO to entity
        Product request = modelMapper.map(dto, Product.class);

        Product object = service.create(request);

        // convert entity to DTO
        ProductDto response = modelMapper.map(object, ProductDto.class);

        return new ResponseEntity<ProductDto>(response, HttpStatus.CREATED);
    }

    // change request for DTO
    // change the response for DTO

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable long id, @RequestBody ProductDto dto){
        // concert DTO to Entity
        Product request = modelMapper.map(dto, Product.class);

        Product object = service.update(id, request);

        // entity to DTO
        ProductDto response = modelMapper.map(object, ProductDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patch(@PathVariable Long id, @Valid @RequestBody Product object){
        Product result = service.update(id, object);
        System.out.println(result);
        return ResponseEntity.ok().body(modelMapper.map(result, ProductDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<String>("OBJECT(id = " + id + ") deleted from database.", HttpStatus.OK);
    }
}
