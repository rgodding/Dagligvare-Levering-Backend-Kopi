package com.example.demo;

import com.example.demo.delivery.Delivery;
import com.example.demo.delivery.DeliveryRepository;
import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import com.example.demo.product_order.ProductOrder;
import com.example.demo.product_order.ProductOrderRepository;
import com.example.demo.van.Van;
import com.example.demo.van.VanRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class ExamKopiTilMundtligApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExamKopiTilMundtligApplication.class, args);
    }
    @Bean
    public CommandLineRunner importData(DeliveryRepository deliveryRepository,
                                        VanRepository vanRepository,
                                        ProductOrderRepository productOrderRepository,
                                        ProductRepository productRepository) {
        return (args) -> {
            /*
            Delivery: deliveryDate(String), fromWarehouse(String), destination(String)
            Product: name(String), price(double), wieight(double/float?)
            ProductOrder: quantity(int)
            Van: brand(String), model(String), capacity(Double/Float? Kg ?)
             */
            log.info("Initializing CommandLineRunner");

            log.info("Initializing Deliveries");
            final List<Delivery> deliveries = new ArrayList<>();
            deliveries.add(new Delivery("20-12-2022", "Warehouse 1", "Føtex"));
            deliveries.add(new Delivery("21-12-2022", "Warehouse 2", "Netto"));
            deliveries.add(new Delivery("22-12-2022", "Warehouse 3", "Netto"));
            deliveries.add(new Delivery("23-12-2022", "Warehouse 2", "Bilka"));
            deliveries.add(new Delivery("24-2-2022", "Warehouse 1", "Føtex"));
            deliveries.add(new Delivery("25-12-2022", "Warehouse 1", "Fakta"));
            deliveries.add(new Delivery("25-5-2022", "Warehouse 1", "Fakta"));
            deliveries.add(new Delivery("25-12-2022", "Warehouse 3", "Fakta"));
            deliveries.add(new Delivery("25-12-2012", "Warehouse 3", "Fakta"));
            deliveries.add(new Delivery("5-12-2022", "Warehouse 4", "Fakta"));
            deliveries.add(new Delivery("25-1-2022", "Warehouse 1", "Bilka"));
            deliveries.add(new Delivery("25-7-2022", "Warehouse 1", "Bilka"));
            log.info("Deliveries created");
            deliveryRepository.saveAll(deliveries);
            log.info("Deliveries saved");

            log.info("Initializing Products");
            final List<Product> products = new ArrayList<>();
            products.add(new Product("Product 1",100 ,5));
            products.add(new Product("Product 2",200 ,5));
            products.add(new Product("Product 3",50 ,1));
            products.add(new Product("Product 4",250 ,10));
            products.add(new Product("Product 5",1000 ,50));
            products.add(new Product("Product 6",500 ,25));
            products.add(new Product("Product 7",25 ,30));
            products.add(new Product("Product 8",5000 ,100));
            log.info("Products created");
            productRepository.saveAll(products);
            log.info("Products saved");

            log.info("Initializing ProductOrders");
            final List<ProductOrder> productOrders = new ArrayList<>();
            productOrders.add(new ProductOrder(5, products.get(0)));
            productOrders.add(new ProductOrder(3, products.get(0)));
            productOrders.add(new ProductOrder(2, products.get(0)));
            productOrders.add(new ProductOrder(12, products.get(1)));
            productOrders.add(new ProductOrder(25, products.get(1)));
            productOrders.add(new ProductOrder(10, products.get(1)));
            productOrders.add(new ProductOrder(1000, products.get(3)));
            productOrders.add(new ProductOrder(10, products.get(4)));
            productOrders.add(new ProductOrder(25, products.get(3)));
            productOrders.add(new ProductOrder(15, products.get(5)));
            log.info("ProductOrders created");
            productOrderRepository.saveAll(productOrders);
            log.info("ProductOrders saved");

            log.info("Initializing Vans");
            final List<Van> vans = new ArrayList<>();
            vans.add(new Van("Volvo","Model 1",2000));
            vans.add(new Van("Ford","Model 2",1250));
            vans.add(new Van("Volvo","Model 3",1500));
            vans.add(new Van("BMW","Model 4",750));
            vans.add(new Van("Volvo","Model 5",500));
            vans.add(new Van("Volvo","Model 6",5000));
            vans.add(new Van("Tesla","Model 6",250));
            log.info("Vans created");
            vanRepository.saveAll(vans);
            log.info("Vans saved");

            Delivery tempDelivery1 = deliveries.get(0);
            tempDelivery1.setVan(vans.get(0));
            deliveryRepository.save(tempDelivery1);

        };
    }

}
