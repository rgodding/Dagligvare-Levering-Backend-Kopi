package com.example.demo.product_order;

import com.example.demo.delivery.Delivery;
import com.example.demo.dto.DeliveryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductOrderDto;
import com.example.demo.product.Product;
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
@RequestMapping("/api/v1/productorder")
public class ProductOrderController {

    @Autowired
    private ModelMapper modelMapper;


    private final ProductOrderService service;

    @GetMapping("/find-order-by-delivery-id/{id}")
    public ResponseEntity<List<ProductOrderDto>> findDeliveryProductOrder(@PathVariable(name = "id") Long id){
        List<ProductOrder> request = service.findByDeliveryId(id);

        List<ProductOrderDto> result = request.stream().map(object -> modelMapper.map(object, ProductOrderDto.class))
                .toList();
        return ResponseEntity.ok().body(result);
    }
    @PostMapping("/add-delivery/{id}")
    public ResponseEntity<ProductOrderDto> addDeliveryToProductOrder(@PathVariable(name = "id") Long id, @Valid @RequestBody DeliveryDto dto){
        Delivery object = modelMapper.map(dto, Delivery.class);
        ProductOrder temp = service.setDelivery(id, object);
        ProductOrderDto response = modelMapper.map(temp, ProductOrderDto.class);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/add-product/{id}")
    public ResponseEntity<ProductOrderDto> addProductToProductOrder(@PathVariable(name = "id") Long id, @Valid @RequestBody ProductDto dto){
        Product object = modelMapper.map(dto, Product.class);
        ProductOrder temp = service.setProduct(id, object);
        ProductOrderDto response = modelMapper.map(temp, ProductOrderDto.class);
        return ResponseEntity.ok().body(response);
    }


    //Core Methods
    @GetMapping
    public ResponseEntity<List<ProductOrderDto>> findAll() {
        List<ProductOrderDto> objects = service.getAll().stream().map(object -> modelMapper.map(object, ProductOrderDto.class))
                .toList();
        return ResponseEntity.ok().body(objects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOrderDto> getById(@PathVariable(name = "id") Long id){
        ProductOrder object = service.getById(id);

        // convert entity to DTO
        ProductOrderDto response = modelMapper.map(object, ProductOrderDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ProductOrderDto> create(@Valid @RequestBody ProductOrderDto dto){

        // convert DTO to entity
        ProductOrder request = modelMapper.map(dto, ProductOrder.class);

        ProductOrder object = service.create(request);

        // convert entity to DTO
        ProductOrderDto response = modelMapper.map(object, ProductOrderDto.class);

        return new ResponseEntity<ProductOrderDto>(response, HttpStatus.CREATED);
    }

    // change request for DTO
    // change the response for DTO

    @PutMapping("/{id}")
    public ResponseEntity<ProductOrderDto> update(@PathVariable long id, @RequestBody ProductOrderDto dto){
        // concert DTO to Entity
        ProductOrder request = modelMapper.map(dto, ProductOrder.class);

        ProductOrder object = service.update(id, request);

        // entity to DTO
        ProductOrderDto response = modelMapper.map(object, ProductOrderDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductOrderDto> patch(@PathVariable Long id, @Valid @RequestBody ProductOrder object){
        ProductOrder result = service.update(id, object);
        return ResponseEntity.ok().body(modelMapper.map(result, ProductOrderDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<String>("OBJECT(id = " + id + ") deleted from database.", HttpStatus.OK);
    }
}
