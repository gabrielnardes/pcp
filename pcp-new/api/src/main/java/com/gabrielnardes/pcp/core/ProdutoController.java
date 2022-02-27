package com.gabrielnardes.pcp.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produto")
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoController {

    @GetMapping("/test")
    public @ResponseBody
    ResponseEntity test() {
        String test = "00000";
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
