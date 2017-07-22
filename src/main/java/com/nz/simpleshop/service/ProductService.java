package com.nz.simpleshop.service;

import com.nz.simpleshop.model.Product;
import com.nz.simpleshop.repository.ProductRepository;
import com.nz.simpleshop.repository.PurchaseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final static Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional
    public void deleteProduct(Product product) {
        productRepository.delete(product);
        purchaseRepository.removeProductInfo(product);
        logger.info("Deleted product {}", product);
    }

    public Product getProductById(Long id) {
        return productRepository.findOne(id);
    }

    public Long countProducts() {
        return productRepository.count();
    }

    public List<Product> listProductsPaged(Integer page, Integer sizePerPage) {
        page = ((page < 1) ? 0 : page - 1);

        List<Product> target = new ArrayList<>();
        productRepository.findAll(new PageRequest(page, sizePerPage)).forEach(target::add);
        return target;
    }

    @Transactional
    public void addProduct(Product product) {
        productRepository.save(product);
        logger.info("Added product {}", product);
    }

    @Transactional
    public void editProduct(Product product) {
        productRepository.save(product);
        logger.info("Edited product {}", product);
    }
}
