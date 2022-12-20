package com.example.demo.delivery;

import com.example.demo.product_order.ProductOrder;
import com.example.demo.van.Van;
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
@Table(name = "DELIVERY")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DELIVERY_DATE")
    private String deliveryDate;

    @Column(name = "FROM_WAREHOUSE")
    private String fromWarehouse;

    @Column(name = "DESTINATION")
    private String destination;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductOrder> productOrders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "van_id")
    Van van;


    public Delivery(String deliveryDate, String fromWarehouse, String destination) {
        this.deliveryDate = deliveryDate;
        this.fromWarehouse = fromWarehouse;
        this.destination = destination;
    }

    public Delivery(String deliveryDate, String fromWarehouse, String destination, List<ProductOrder> productOrders, Van van) {
        this.deliveryDate = deliveryDate;
        this.fromWarehouse = fromWarehouse;
        this.destination = destination;
        this.productOrders = productOrders;
        this.van = van;
    }

    public Delivery update(Delivery object){
        if(object.deliveryDate != null) {this.deliveryDate = object.deliveryDate;}
        if(object.fromWarehouse != null) {this.fromWarehouse = object.fromWarehouse;}
        if(object.destination != null) {this.destination = object.destination;}
        if(object.productOrders != null) {this.productOrders = object.productOrders;}
        if(object.van != null) {this.van = object.van;}
        return this;
    }





}
