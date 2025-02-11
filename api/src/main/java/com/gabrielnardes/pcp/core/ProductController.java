package com.gabrielnardes.pcp.core;

import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll() {
        List<Product> products = this.productService.findAll();
        return JsonbBuilder.create().toJson(products);
    }

    @POST
    public void create(Product product) {
        this.productService.save(product);
    }

}
