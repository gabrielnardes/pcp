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
            productB = productRepository.save(productB);
            System.out.println(productB);

            Product productC = new Product();
            productC.setName("Product C");
            productC.setPrice(new BigDecimal(10));
            System.out.println(productRepository.save(productC));

            Customer customerA = new Customer();
            customerA.setName("Customer A");
            customerA.setEmail("customer@A.com");
            customerA.setPhoneNumber("123-123-123");
            customerA.setAddress("Av. A - A City");
            customerA = customerRepository.save(customerA);
            System.out.println(customerA);

            Customer customerB = new Customer();
            customerB.setName("Customer B");
            customerB.setEmail("customer@B.com");
            customerB.setPhoneNumber("456-456-456");
            customerB.setAddress("Av. B - B City");
            customerB = customerRepository.save(customerB);
            System.out.println(customerB);

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
            location1 = locationRepository.save(location1);
            System.out.println(location1);

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
            location2 = locationRepository.save(location2);
            System.out.println(location2);

            Order order1 = new Order();
            order1.setStatus(Status.CREATED);
            order1.setQuantity(3L);
            order1.setPrice(productA.getPrice());
            order1.setCreationDate(new Date());
            order1.setCustomerId(customerA.getId());
            order1.setProductId(productB.getId());
            order1.setOriginId(location1.getId());
            order1.setDestinationId(location2.getId());
            System.out.println(orderRepository.save(order1));

            Order order2 = new Order();
            order2.setStatus(Status.CANCELLED);
            order2.setQuantity(100L);
            order2.setPrice(productC.getPrice());
            order2.setCreationDate(new Date(new Date().getTime()-3_070_200_080L));
            order2.setCustomerId(customerB.getId());
            order2.setProductId(productC.getId());
            order2.setOriginId(location2.getId());
            order2.setDestinationId(location1.getId());
            System.out.println(orderRepository.save(order2));
        };
    }

}
