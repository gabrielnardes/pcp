package com.gabrielnardes.erp;

import com.gabrielnardes.erp.customer.Customer;
import com.gabrielnardes.erp.customer.CustomerRepository;
import com.gabrielnardes.erp.location.Location;
import com.gabrielnardes.erp.location.LocationRepository;
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

import static com.gabrielnardes.erp.location.Status.ACTIVE;
import static com.gabrielnardes.erp.location.Status.INACTIVE;

@Configuration
public class LoadDummyDataset {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            CustomerRepository customerRepository,
            LocationRepository locationRepository
    ) {
        return args -> {
            Product productA = new Product();
            productA.setName("Product A");
            productA.setPrice(new BigDecimal(1));
            System.out.println(productRepository.save(productA));

            Product productB = new Product();
            productB.setName("Product B");
            productB.setPrice(new BigDecimal(5));
            System.out.println(productRepository.save(productB));

            Product productC = new Product();
            productC.setName("Product C");
            productC.setPrice(new BigDecimal(10));
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

            Location location1 = new Location();
            location1.setName("Central Park");
            location1.setStreet("5th Ave");
            location1.setNumber("100");
            location1.setNeighborhood("Manhattan");
            location1.setCity("New York");
            location1.setCountry("USA");
            location1.setLatitude(40.785091);
            location1.setLongitude(-73.968285);
            location1.setStatus(ACTIVE);
            System.out.println(locationRepository.save(location1));

            Location location2 = new Location();
            location2.setName("Eiffel Tower");
            location2.setStreet("Champ de Mars");
            location2.setNumber("5");
            location2.setNeighborhood("7th arrondissement");
            location2.setCity("Paris");
            location2.setCountry("France");
            location2.setLatitude(48.858844);
            location2.setLongitude(2.294351);
            location2.setStatus(INACTIVE);
            System.out.println(locationRepository.save(location2));
        };
    }
}
