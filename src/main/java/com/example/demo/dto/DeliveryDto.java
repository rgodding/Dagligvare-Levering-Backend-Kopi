package com.example.demo.dto;

import lombok.Data;

@Data
public class DeliveryDto {
    private Long id;
    private String deliveryDate;
    private String fromWarehouse;
    private String destination;
    private VanDto van;
}
