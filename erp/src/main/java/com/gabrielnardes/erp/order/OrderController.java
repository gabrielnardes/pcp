package com.gabrielnardes.erp.order;

import com.gabrielnardes.erp.customer.Customer;
import com.gabrielnardes.erp.customer.CustomerRepository;
import com.gabrielnardes.erp.product.Product;
import com.gabrielnardes.erp.product.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    public OrderController(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/order")
    List<OrderDTOResponse> all() {
        List<Order> all = orderRepository.findAll();
        List<OrderDTOResponse> response = new ArrayList<>(all.size());

        for (Order order : all) {
            Product product = productRepository.findById(order.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            Customer customer = customerRepository.findById(order.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            OrderDTOResponse orderDTOResponse = new OrderDTOResponse();
            orderDTOResponse.setId(order.getId());
            orderDTOResponse.setStatus(order.getStatus());
            orderDTOResponse.setQuantity(order.getQuantity());
            orderDTOResponse.setPrice(order.getPrice());
            orderDTOResponse.setTotal(order.getPrice().multiply(new BigDecimal(order.getQuantity())));
            orderDTOResponse.setCreationDate(order.getCreationDate());
            orderDTOResponse.setCustomer(customer.getName());
            orderDTOResponse.setProduct(product.getName());

            response.add(orderDTOResponse);
        }

        return response;
    }

    @PostMapping("/order")
    public OrderDTOResponse create(@RequestBody OrderDTO newOrder) {
        Product product = productRepository.findById(newOrder.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Customer customer = customerRepository.findById(newOrder.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setStatus(Status.CREATED);
        order.setQuantity(newOrder.getQuantity());
        order.setPrice(product.getPrice());
        order.setCreationDate(new Date());
        order.setCustomerId(newOrder.getCustomerId());
        order.setProductId(newOrder.getProductId());

        order = orderRepository.save(order);

        OrderDTOResponse orderDTOResponse = new OrderDTOResponse();
        orderDTOResponse.setId(order.getId());
        orderDTOResponse.setStatus(order.getStatus());
        orderDTOResponse.setQuantity(order.getQuantity());
        orderDTOResponse.setPrice(order.getPrice());
        orderDTOResponse.setTotal(order.getPrice().multiply(new BigDecimal(order.getQuantity())));
        orderDTOResponse.setCreationDate(order.getCreationDate());
        orderDTOResponse.setCustomer(customer.getName());
        orderDTOResponse.setProduct(product.getName());

        return orderDTOResponse;
    }

    @PutMapping("/order/{id}")
    List<OrderDTOResponse> cancel(@PathVariable Long id) {
        Optional<Order> byId = orderRepository.findById(id);

        if (byId.isPresent()) {
            Order order  = byId.get();
            order.setStatus(Status.CANCELLED);
            orderRepository.save(order);
        }

        return this.all();
    }

}
