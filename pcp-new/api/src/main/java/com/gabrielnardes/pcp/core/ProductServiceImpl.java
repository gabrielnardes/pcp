package com.gabrielnardes.pcp.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Iterable<Product> findAll() {
        return repository.findAll();
    }
}