package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.List;

@Data
public class VanDto {
    private Long id;
    private String brand;
    private String model;
    private double capacity;
    @JsonBackReference
    private List<DeliveryDto> deliveries;
}
