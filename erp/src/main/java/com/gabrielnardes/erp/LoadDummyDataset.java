package com.gabrielnardes.erp;

import com.gabrielnardes.erp.customer.Customer;
import com.gabrielnardes.erp.customer.CustomerRepository;
import com.gabrielnardes.erp.order.Order;
import com.gabrielnardes.erp.order.OrderRepository;
import com.gabrielnardes.erp.order.Status;
import com.gabrielnardes.erp.product.Product;
import com.gabrielnardes.erp.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
public class LoadDummyDataset {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            CustomerRepository customerRepository
    ) {
        return args -> {
            Product productA = new Product();
            productA.setName("Product A");
            productA.setPrice(new BigDecimal(1));

            Product productB = new Product();
            productB.setName("Product B");
            productB.setPrice(new BigDecimal(5));

            Product productC = new Product();
            productC.setName("Product C");
            productC.setPrice(new BigDecimal(10));

            System.out.println(productRepository.save(productA));
            System.out.println(productRepository.save(productB));
            System.out.println(productRepository.save(productC));

            Customer customer = new Customer();
            customer.setName("Customer A");
            customer.setEmail("customer@A.com");
            customer.setPhoneNumber("123-123-123");
            customer.setAddress("Av. A - A City");
            System.out.println(customerRepository.save(customer));

            Order order = new Order();
            order.setStatus(Status.CREATED);
            order.setQuantity(3L);
            order.setPrice(productA.getPrice());
            order.setCreationDate(new Date());
            order.setCustomerId(1L);
            order.setProductId(1L);

            System.out.println(orderRepository.save(order));
        };
    }
}
