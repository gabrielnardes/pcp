package com.gabrielnardes.pcp.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@ApplicationScoped
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Product> findAll() {
        Query query = this.em.createQuery("select p from com.gabrielnardes.pcp.core.Product p");
        return (List<Product>) query.getResultList();
    }

    public void save(Product product) {
        this.em.persist(product);
    }

}
