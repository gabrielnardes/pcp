package com.gabrielnardes.erp.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Configuration
public class ProductLoadDatabase {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(ProductRepository repository) {
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

            System.out.println(repository.save(productA));
            System.out.println(repository.save(productB));
            System.out.println(repository.save(productC));
        };
    }
}
