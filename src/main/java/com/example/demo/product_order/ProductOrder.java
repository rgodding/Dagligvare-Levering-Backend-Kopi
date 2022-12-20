package com.example.demo.product_order;

import com.example.demo.delivery.Delivery;
import com.example.demo.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_ORDER")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id")
    Delivery delivery;

    public ProductOrder(int quantity) {
        this.quantity = quantity;
    }

    public ProductOrder(int quantity, Product product){
        this.quantity = quantity;
        this.product = product;
    }


    public ProductOrder(int quantity, Product product, Delivery delivery){
        this.quantity = quantity;
        this.product = product;
        this.delivery = delivery;
    }

    public ProductOrder update(ProductOrder object){
        if(object.quantity != 0) {this.quantity = object.quantity;}
        if(object.product != null) {this.product = object.product;}
        if(object.delivery != null) {this.delivery = object.delivery;}
        return this;
    }

}
