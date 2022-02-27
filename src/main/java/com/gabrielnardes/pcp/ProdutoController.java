package com.gabrielnardes.pcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produto")
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public @ResponseBody
    Iterable<Produto> findAll() {
        return produtoService.findAll();
    }

//    @GetMapping("/{id}")
//    public @ResponseBody
//    Produto findById(@PathVariable Long id) {
//        return produtoService.findById(id);
//    }
//
//    @PostMapping
//    public void save(Produto produto) {
//        produtoService.save(produto);
//    }
//
//    @PutMapping
//    public void update(Produto produto) {
//        produtoService.update(produto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        produtoService.delete(id);
//    }

    @GetMapping("/test")
    public @ResponseBody ResponseEntity test() {
        return new ResponseEntity<>("Hello!", HttpStatus.OK);
    }
}
