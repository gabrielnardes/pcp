package com.gabrielnardes.erp.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    long countByCustomerId(Long customerId);

}
