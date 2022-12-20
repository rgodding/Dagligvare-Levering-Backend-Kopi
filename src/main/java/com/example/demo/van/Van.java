package com.example.demo.van;

import com.example.demo.delivery.Delivery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VAN")
public class Van {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "CAPACITY")
    private double capacity;

    @OneToMany(mappedBy = "van", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Delivery> deliveries = new ArrayList<>();

    public Van(String brand, String model, double capacity) {
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
    }

    public Van(String brand, String model, double capacity, List<Delivery> deliveries) {
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.deliveries = deliveries;
    }

    public Van update(Van object){
        if(object.brand != null) {this.brand = object.brand;}
        if(object.model != null) {this.model = object.model;}
        if(object.capacity != 0) {this.capacity = object.capacity;}
        if(object.deliveries != null) {this.deliveries = object.deliveries;}
        return this;
    }
}
