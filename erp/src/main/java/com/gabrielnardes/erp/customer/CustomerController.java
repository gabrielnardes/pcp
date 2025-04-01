package com.gabrielnardes.erp.customer;

import com.gabrielnardes.erp.order.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    public CustomerController(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/customer")
    List<Customer> all() {
        return customerRepository.findAll();
    }

    @PostMapping("/customer")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @PutMapping("/customer/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setEmail(newCustomer.getEmail());
                    customer.setPhoneNumber(newCustomer.getPhoneNumber());
                    customer.setAddress(newCustomer.getAddress());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> customerRepository.save(newCustomer));
    }

    @DeleteMapping("/customer/{id}")
    void deleteCustomer(@PathVariable Long id) {
        long count = orderRepository.countByCustomerId(id);
        if (count > 0) {
            throw new RuntimeException("countByCustomerId");
        }
        customerRepository.deleteById(id);
    }

}
