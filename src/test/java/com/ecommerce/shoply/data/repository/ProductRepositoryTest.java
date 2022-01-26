package com.ecommerce.shoply.data.repository;

import com.ecommerce.shoply.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ecommerce.shoply.data.model.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
//@Sql(scripts={"/db/insert.sql"})
public
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepositoryImpl;

    @BeforeEach
    void setUp() {
    }


    @Test
    void createProductTest() {
        Product product = new Product();
        product.setName("Luxury Sofa");
        product.setPrice(400D);
        product.setCurrency(NGN);
        product.setDetails("Lorem Ipsum is slechts een proeftekst" +
                " uit het drukkerij- en zetterijwezen. Lorem Ipsum " +
                "is de standaard proeftekst in deze bedrijfstak sinds " +
                "de 16e eeuw, toen een onbekende drukker een zethaak met " +
                "letters nam en ze door elkaar husselde om een font-catalogus " +
                "te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, " +
                "vrijwel onveranderd, overgenomen in elektronische letterzetting. " +
                "Het is in de jaren '60 populair geworden met de introductie van Letraset.");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNull();
        log.info("Product before saving -> {}", product);
        Product savedProduct = productRepositoryImpl.save(product);
        assertThat(product.getId()).isNotNull();
        log.info("Product after saving -> {}", product);
    }

    @Test
    //@Transactional
    public void whenFallAllProductIsCalledThenProductListIsReturnedTest(){
        List<Product> products = productRepositoryImpl.findAll();
        assertThat(products).hasSize(3);
        log.info("Product returned from database-> {}", products);
    }

    @Test
    void findExistingProductById(){
        Product product = new Product();
        product.setName("Luxury Sofa");
        product.setPrice(400D);
        product.setCurrency(NGN);
        product.setDetails("Lorem Ipsum is slechts een proeftekst" +
                " uit het drukkerij- en zetterijwezen. Lorem Ipsum " +
                "is de standaard proeftekst in deze bedrijfstak sinds " +
                "de 16e eeuw, toen een onbekende drukker een zethaak met " +
                "letters nam en ze door elkaar husselde om een font-catalogus " +
                "te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, " +
                "vrijwel onveranderd, overgenomen in elektronische letterzetting. " +
                "Het is in de jaren '60 populair geworden met de introductie van Letraset.");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNull();
        log.info("Product before saving -> {}", product);
        Product savedProduct = productRepositoryImpl.save(product);
        assertThat(product.getId()).isNotNull();
        log.info("Product after saving -> {}", product);

        Product existingProduct = productRepositoryImpl.findById(1L).orElse(null);
        assertThat(existingProduct).isNotNull();
        log.info("Product --> {}", existingProduct);
    }

    @Test
    public void deleteExistingProductById(){
        Product product = new Product();
        product.setName("Luxury Sofa");
        product.setPrice(400D);
        product.setCurrency(NGN);
        product.setDetails("Lorem Ipsum is slechts een proeftekst" +
                " uit het drukkerij- en zetterijwezen. Lorem Ipsum " +
                "is de standaard proeftekst in deze bedrijfstak sinds " +
                "de 16e eeuw, toen een onbekende drukker een zethaak met " +
                "letters nam en ze door elkaar husselde om een font-catalogus " +
                "te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, " +
                "vrijwel onveranderd, overgenomen in elektronische letterzetting. " +
                "Het is in de jaren '60 populair geworden met de introductie van Letraset.");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNull();
        log.info("Product before saving -> {}", product);
        Product savedProduct = productRepositoryImpl.save(product);
        assertThat(product.getId()).isNotNull();
        log.info("Product after saving -> {}", product);
        assertThat(productRepositoryImpl.findById(1L).orElse(null)).isNotNull();
        productRepositoryImpl.deleteById(1L);
        assertThat(productRepositoryImpl.findById(1L).orElse(null)).isNull();
    }
}