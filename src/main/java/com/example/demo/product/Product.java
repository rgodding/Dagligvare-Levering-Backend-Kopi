package com.example.demo.product;

import com.example.demo.product_order.ProductOrder;
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
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "WEIGHT")
    private double weight;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductOrder> productOrders = new ArrayList<>();

    public Product(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public Product update(Product object){
        if(object.name != null) {this.name = object.name;}
        if(object.price != 0) {this.price = object.price;}
        if(object.weight != 0) {this.weight = object.weight;}
        return this;
    }
}
