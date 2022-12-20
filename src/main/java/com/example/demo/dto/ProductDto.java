package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private double weight;
}
