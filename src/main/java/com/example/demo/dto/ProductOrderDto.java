package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOrderDto {
    private Long id;
    private int quantity;
    private ProductDto product;
    private DeliveryDto delivery;
}
