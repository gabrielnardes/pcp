package com.gabrielnardes.pcp;

import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository repository;

    @Test
    public void debugTest() throws Exception {
        Produto produto1 = new Produto();
        produto1.setNome("produto1");
        repository.save(produto1);

        Produto produto2 = new Produto();
        produto2.setNome("produto2");
        repository.save(produto2);

        Iterable<Produto> produtos = repository.findAll();
        Produto produto1Db = produtos.iterator().next();

        assertEquals(produto1Db.getNome(), produto1.getNome());
        assertEquals(IterableUtils.size(produtos), 2);
    }
}
