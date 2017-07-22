package com.nz.simpleshop.service;

import com.nz.simpleshop.model.Client;
import com.nz.simpleshop.model.Product;
import com.nz.simpleshop.model.Purchase;
import com.nz.simpleshop.repository.ProductRepository;
import com.nz.simpleshop.repository.PurchaseRepository;
import com.nz.simpleshop.service.exception.ApiException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PurchaseServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePurchase() throws ApiException {
        Client client = new Client();
        Long productId = 1L;
        Integer count = 1;
        Integer originalStock = 10;

        Product product = new Product();
        product.setStock(originalStock);
        product.setPrice(100.0);
        product.setId(1L);

        when(productRepository.findOne(product.getId())).thenReturn(product);

        Purchase purchase = purchaseService.createPurchase(client, productId, count);

        Assert.assertNotNull(purchase);
        Assert.assertEquals(purchase.getClient(), client);
        Assert.assertEquals(purchase.getCount(), count);
        Assert.assertEquals(purchase.getPrice(), product.getPrice());
        Assert.assertEquals(purchase.getProduct(), product);

        Assert.assertEquals(product.getStock(), Integer.valueOf(originalStock - count));

        verify(purchaseRepository, times(1)).save(purchase);
        verify(productRepository, times(1)).save(product);
    }

    @Test(expected = ApiException.class)
    public void testCreatePurchaseInvalidCount() throws ApiException {
        purchaseService.createPurchase(new Client(), 1L, -1);
    }

    @Test(expected = ApiException.class)
    public void testCreatePurchaseInvalidClient() throws ApiException {
        purchaseService.createPurchase(null, 1L, -1);
    }

    @Test(expected = ApiException.class)
    public void testCreatePurchaseInvalidStock() throws ApiException {
        Product product = new Product();
        product.setStock(1);
        product.setPrice(100.0);
        product.setId(1L);
        when(productRepository.findOne(product.getId())).thenReturn(product);

        purchaseService.createPurchase(null, 1L, 5);
    }
}