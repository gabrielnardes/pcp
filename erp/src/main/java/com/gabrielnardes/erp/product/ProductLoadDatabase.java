package com.gabrielnardes.erp.product;

import com.gabrielnardes.erp.order.Order;
import com.gabrielnardes.erp.order.OrderRepository;
import com.gabrielnardes.erp.order.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
public class ProductLoadDatabase {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(
            ProductRepository productRepository,
            OrderRepository orderRepository
    ) {
        return args -> {
            Product productA = new Product();
            productA.setName("Product A");
            productA.setPrice(new BigDecimal(23));

            Product productB = new Product();
            productB.setName("Product B");
            productB.setPrice(new BigDecimal(123));

            Product productC = new Product();
            productC.setName("Product C");
            productC.setPrice(new BigDecimal(999));

            System.out.println(productRepository.save(productA));
            System.out.println(productRepository.save(productB));
            System.out.println(productRepository.save(productC));

            Order order = new Order();
            order.setStatus(Status.CREATED);
            order.setQuantity(2L);
            order.setPrice(productA.getPrice());
            order.setCreationDate(new Date());
            order.setClient("myClient");
            order.setProduct(1L);

            System.out.println(orderRepository.save(order));
        };
    }
}
