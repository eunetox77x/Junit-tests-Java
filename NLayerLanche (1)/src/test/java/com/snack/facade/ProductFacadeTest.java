package com.snack.facade;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFacadeTest {
    private ProductFacade facade;
    private ProductRepository repo;
    private ProductService servico;
    private ProductApplication app;
    private Product produto;

    @BeforeEach
    public void Setup() {
        repo = new ProductRepository();
        servico = new ProductService();
        app = new ProductApplication(repo, servico);
        facade = new ProductFacade(app);
        produto = new Product(1, "Produto", 10f, "imagem.jpg");
    }

    @Test
    public void validarGetAllRetornaTodosProdutos() {
        repo.append(produto);
        assertEquals(1, facade.getAll().size());
    }

    @Test
    public void validarGetByIdRetornaProdutoCorreto() {
        repo.append(produto);
        assertEquals(1, facade.getById(1).getId());
    }

    @Test
    public void validarExistsParaIdValidoEInvalido() {
        repo.append(produto);
        assertTrue(facade.exists(1));
        assertFalse(facade.exists(99));
    }

    @Test
    public void validarAdicionarProduto() {
        facade.append(produto);
        assertEquals(1, repo.getAll().size());
    }

    @Test
    public void validarRemocaoDeProduto() {
        repo.append(produto);
        facade.remove(1);
        assertEquals(0, repo.getAll().size());
    }
}
