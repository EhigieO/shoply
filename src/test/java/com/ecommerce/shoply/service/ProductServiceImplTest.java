package com.ecommerce.shoply.service;

import com.ecommerce.shoply.data.dto.ProductUpdateForm;
import com.ecommerce.shoply.data.model.Currency;
import com.ecommerce.shoply.data.model.Product;
import com.ecommerce.shoply.data.repository.ProductRepository;
import com.ecommerce.shoply.web.exceptions.ProductDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
@SpringBootTest
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productServiceImpl = new ProductServiceImpl();
    @BeforeEach
    void setUp() {
        productServiceImpl = new ProductServiceImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProduct() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);
        productServiceImpl.save(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void findAll() {
    }

//    @Test
//    void updateProduct() throws ProductDoesNotExistException {
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Telephone");
//        product.setPrice(500.00);
//        product.setCurrency(Currency.NGN);
//        product.setDetails("Telephone is good");
//        when(productRepository.save(any())).thenReturn(product);
//        productServiceImpl.save(product);
//
//        ProductUpdateForm productUpdate = new ProductUpdateForm();
//        productUpdate.setName("Nokia");
//        productUpdate.setPrice(600.00);
//        productUpdate.setDetails("Telephone is not good");
//        productUpdate.setCurrency(Currency.FRC);
//
//        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
//        when(productRepository.save(any())).thenReturn(product);
//        productServiceImpl.update(1L, productUpdate);
//        System.out.println(product);
//        verify(productRepository, times(2)).save(any());
//    }
}