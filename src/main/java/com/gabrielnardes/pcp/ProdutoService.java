package com.gabrielnardes.pcp;

public interface ProdutoService {
    Iterable<Produto> findAll();

    void save(Produto produto);
}
