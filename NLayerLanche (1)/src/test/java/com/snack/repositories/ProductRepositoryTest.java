package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    @Test
    public void deveInstanciarRepositorio() {
        ProductRepository repo = new ProductRepository();
        assertNotNull(repo);
    }

    @Test
    public void listaInicialNaoDeveSerNula() {
        ProductRepository repo = new ProductRepository();
        assertNotNull(repo.getAll());
    }

    @Test
    public void removerNaoDeveLancarErro() {
        ProductRepository repo = new ProductRepository();
        assertDoesNotThrow(() -> repo.remove(10));
    }

    @Test
    public void atualizarNaoDeveLancarErro() {
        ProductRepository repo = new ProductRepository();
        Product p = new Product(1, "Teste", 10f, "");
        assertDoesNotThrow(() -> repo.update(1, p));
    }

    @Test
    public void existsNaoDeveLancarErro() {
        ProductRepository repo = new ProductRepository();
        assertDoesNotThrow(() -> repo.exists(1));
    }
}
