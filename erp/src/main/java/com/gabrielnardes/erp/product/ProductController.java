package com.gabrielnardes.erp.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/product")
    List<Product> all() {
        LOGGER.info("all()");
        return repository.findAll();
    }

    @PostMapping("/product")
    Product newProduct(@RequestBody Product newProduct) {
        LOGGER.info("newProduct()");
        return repository.save(newProduct);
    }

    @GetMapping("/product/{id}")
    Product one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PutMapping("/product/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    return repository.save(product);
                })
                .orElseGet(() -> repository.save(newProduct));
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
