package com.gabrielnardes.pcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Override
    public Iterable<Produto> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Produto produto) {
        repository.save(produto);
    }

}
