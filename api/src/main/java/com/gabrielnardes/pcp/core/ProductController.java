package com.gabrielnardes.pcp.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity findAll() {
        Iterable<Product> products = service.findAll();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void create(@RequestBody Product product) {
        System.out.println(product);
        service.save(product);
    }

}
