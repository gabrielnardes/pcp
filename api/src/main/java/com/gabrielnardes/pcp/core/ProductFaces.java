package com.gabrielnardes.pcp.core;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.math.BigDecimal;

@Named
@RequestScoped
public class ProductFaces {

    private final ProductService productService;

    public ProductFaces(ProductService productService) {
        this.productService = productService;
    }

    private String name;
    private BigDecimal price;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void save() {
        Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        this.productService.save(product);
    }

}
