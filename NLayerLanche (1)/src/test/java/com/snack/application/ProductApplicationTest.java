package com.snack.application;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductApplicationTest {
    private ProductRepository repo;
    private ProductService servico;
    private ProductApplication app;
    private Product produto;

    @BeforeEach
    public void setup() {
        repo = new ProductRepository();
        servico = new ProductService();
        app = new ProductApplication(repo, servico);
        produto = new Product(1, "Hotdog", 10f, "imagem.jpg");
    }

    @Test
    public void validarListagemDeProdutos() {
        repo.append(produto);
        assertEquals(1, app.getAll().size());
    }

    @Test
    public void validarObterProdutoPorIdValido() {
        repo.append(produto);
        assertNotNull(app.getById(1));
    }

    @Test
    public void validarObterProdutoPorIdInvalido() {
        assertThrows(Exception.class, () -> app.getById(99));
    }

    @Test
    public void validarExistenciaPorIdValido() {
        repo.append(produto);
        assertTrue(app.exists(1));
    }

    @Test
    public void validarExistenciaPorIdInvalido() {
        assertFalse(app.exists(99));
    }

    @Test
    public void validarAdicionarProdutoESalvarImagem() {
        boolean resultado = servico.save(produto);
        assertTrue(resultado);
    }

    @Test
    public void validarRemoverProdutoEImagem() {
        repo.append(produto);
        assertDoesNotThrow(() -> app.remove(1));
    }

    @Test
    public void validarRemoverProdutoInexistenteSemAfetarSistema() {
        assertDoesNotThrow(() -> app.remove(99));
    }

    @Test
    public void validarAtualizarProdutoEImagem() {
        repo.append(produto);
        Product atualizado = new Product(1, "Atualizado", 20f, "img2.jpg");
        assertDoesNotThrow(() -> app.update(1, atualizado));
    }
}
