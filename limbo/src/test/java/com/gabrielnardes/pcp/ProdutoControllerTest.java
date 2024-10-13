package com.gabrielnardes.pcp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private ProdutoController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProdutoRepository repository;

    @Test
    public void test() throws Exception {
        ResultActions response = this.mockMvc.perform(
                get("/api/v1/produto/test")).
                andExpect(content().string("ola mundo"));
    }

    @Test
    public void test1() throws Exception {
        Produto produto1 = new Produto();
        produto1.setNome("produto1");
        repository.save(produto1);

        Produto produto2 = new Produto();
        produto2.setNome("produto2");
        repository.save(produto2);

        ResultActions response = this.mockMvc.perform(
                        get("/api/v1/produto")).
                andExpect(content().string("[{\"id\":1,\"nome\":\"produto1\"},{\"id\":2,\"nome\":\"produto2\"}]"));
    }
}
