package com.snack.services;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    private ProductService productService;
    private Product product;
    private Path tempImage;

    @BeforeEach
    public void setup() throws Exception {
        productService = new ProductService();
        tempImage = Files.createTempFile("imagem", ".jpg");
        product = new Product(1, "Teste", 10f, tempImage.toString());
    }

    @Test
    public void validarSalvarProdutoComImagemValida() {
        boolean resultado = productService.save(product);
        assertTrue(resultado);
    }

    @Test
    public void validarSalvarProdutoComImagemInexistente() {
        Product p2 = new Product(2, "X", 10, "caminho/naoExiste.jpg");
        boolean resultado = productService.save(p2);
        assertFalse(resultado);
    }

    @Test
    public void validarAtualizarProdutoExistente() {
        productService.save(product);
        boolean existeAntes = new File(product.getImage()).exists();
        productService.update(product);
        boolean existeDepois = new File(product.getImage()).exists();
        assertTrue(existeAntes && existeDepois);
    }

    @Test
    public void validarRemoverProdutoExistente() {
        productService.save(product);
        productService.remove(1);
        assertThrows(Exception.class, () -> productService.getImagePathById(1));
    }

    @Test
    public void validarObterCaminhoDaImagemPorId() {
        productService.save(product);
        String caminho = productService.getImagePathById(1);
        assertNotNull(caminho);
    }
}
