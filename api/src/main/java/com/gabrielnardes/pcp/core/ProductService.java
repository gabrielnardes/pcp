package com.gabrielnardes.pcp.core;

public interface ProductService {
    Iterable<Product> findAll();

    void save(Product product);
}