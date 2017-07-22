package com.nz.simpleshop.service;

import com.nz.simpleshop.model.Product;
import com.nz.simpleshop.repository.ProductRepository;
import com.nz.simpleshop.repository.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private ProductService productService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();

        productService.deleteProduct(product);

        verify(purchaseRepository, times(1)).removeProductInfo(product);
        verify(productRepository, times(1)).delete(product);
    }
}