package com.gabrielnardes.pcp.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public @ResponseBody
    ResponseEntity findAll() {
        Iterable<Product> products = service.findAll();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/test")
    public @ResponseBody
    ResponseEntity test() {
        String test = "00000";
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
