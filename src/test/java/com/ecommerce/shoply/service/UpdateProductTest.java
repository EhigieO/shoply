package com.ecommerce.shoply.service;

import com.ecommerce.shoply.data.dto.ProductUpdateForm;
import com.ecommerce.shoply.data.model.Product;
import com.ecommerce.shoply.data.repository.ProductRepository;
import com.ecommerce.shoply.web.exceptions.ProductDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.ecommerce.shoply.data.model.Currency.NGN;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
//@Sql(scripts = {"/db/insert.sql"})
public class UpdateProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    ProductRepository productRepositoryImpl;

    @Test
    void updateProductTest() throws ProductDoesNotExistException {
        Product mainProduct = new Product();
        mainProduct.setName("Luxury Sofa");
        mainProduct.setPrice(400D);
        mainProduct.setCurrency(NGN);
        mainProduct.setDetails("Lorem Ipsum is slechts een proeftekst" +
                " uit het drukkerij- en zetterijwezen. Lorem Ipsum " +
                "is de standaard proeftekst in deze bedrijfstak sinds " +
                "de 16e eeuw, toen een onbekende drukker een zethaak met " +
                "letters nam en ze door elkaar husselde om een font-catalogus " +
                "te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, " +
                "vrijwel onveranderd, overgenomen in elektronische letterzetting. " +
                "Het is in de jaren '60 populair geworden met de introductie van Letraset.");

        Product savedProduct = productRepositoryImpl.save(mainProduct);



        Product product = productRepository.findById(1L).orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("Luxury Sofa");

        ProductUpdateForm productDto = new ProductUpdateForm();
        productDto.setName("Mexican chair");

        product = productServiceImpl.update(1L, productDto);
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getPrice()).isEqualTo(400);

    }
}