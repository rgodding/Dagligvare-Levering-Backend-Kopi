package com.example.demo.delivery;

import com.example.demo.dto.DeliveryDto;
import com.example.demo.dto.VanDto;
import com.example.demo.van.Van;
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
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    @Autowired
    private ModelMapper modelMapper;


    private final DeliveryService service;

    @GetMapping("/find-deliveries-by-van-id/{id}")
    public ResponseEntity<List<DeliveryDto>> findVanDeliveries(@PathVariable(name = "id") Long id){
        List<Delivery> request = service.findByVanId(id);

        List<DeliveryDto> result = request.stream().map(object -> modelMapper.map(object, DeliveryDto.class))
                .toList();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/add-van/{id}")
    public ResponseEntity<DeliveryDto> addVanToDelivery(@PathVariable(name = "id") Long id, @Valid @RequestBody VanDto dto){
        Van object = modelMapper.map(dto, Van.class);
        Delivery temp = service.setVan(id, object);
        DeliveryDto response = modelMapper.map(temp, DeliveryDto.class);
        return ResponseEntity.ok().body(response);
    }

    //Core Methods
    @GetMapping
    public ResponseEntity<List<DeliveryDto>> findAll() {
        List<DeliveryDto> objects = service.getAll().stream().map(object -> modelMapper.map(object, DeliveryDto.class))
                .toList();
        return ResponseEntity.ok().body(objects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDto> getById(@PathVariable(name = "id") Long id){
        Delivery object = service.getById(id);

        // convert entity to DTO
        DeliveryDto response = modelMapper.map(object, DeliveryDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<DeliveryDto> create(@Valid @RequestBody DeliveryDto dto){

        // convert DTO to entity
        Delivery request = modelMapper.map(dto, Delivery.class);

        Delivery object = service.create(request);

        // convert entity to DTO
        DeliveryDto response = modelMapper.map(object, DeliveryDto.class);

        return new ResponseEntity<DeliveryDto>(response, HttpStatus.CREATED);
    }

    // change request for DTO
    // change the response for DTO

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryDto> update(@PathVariable long id, @RequestBody DeliveryDto dto){
        // concert DTO to Entity
        Delivery request = modelMapper.map(dto, Delivery.class);

        Delivery object = service.update(id, request);

        // entity to DTO
        DeliveryDto response = modelMapper.map(object, DeliveryDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeliveryDto> patch(@PathVariable Long id, @Valid @RequestBody Delivery object){
        Delivery result = service.update(id, object);
        return ResponseEntity.ok().body(modelMapper.map(result, DeliveryDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<String>("OBJECT(id = " + id + ") deleted from database.", HttpStatus.OK);
    }
}
