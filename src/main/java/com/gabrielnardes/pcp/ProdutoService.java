package com.gabrielnardes.pcp;

public interface ProdutoService {
    Iterable<Produto> findAll();

    void save(Produto produto);
    
    Produto findById(Long id);
    
    void delete(Long id);

    void update(Produto produto);
}
