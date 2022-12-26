package com.example.demo.delivery;

import com.example.demo.product_order.ProductOrder;
import com.example.demo.van.Van;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL,orphanRemoval = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(deliveryDate, delivery.deliveryDate) && Objects.equals(fromWarehouse, delivery.fromWarehouse) && Objects.equals(destination, delivery.destination) && Objects.equals(productOrders, delivery.productOrders) && Objects.equals(van, delivery.van);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryDate, fromWarehouse, destination, productOrders, van);
    }
}
