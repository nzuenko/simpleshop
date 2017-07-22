package com.nz.simpleshop.service;

import com.nz.simpleshop.model.Client;
import com.nz.simpleshop.model.Product;
import com.nz.simpleshop.model.Purchase;
import com.nz.simpleshop.repository.ProductRepository;
import com.nz.simpleshop.repository.PurchaseRepository;
import com.nz.simpleshop.service.exception.ApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PurchaseService {

    private final static Logger logger = LogManager.getLogger(PurchaseService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getPurchasesForClient(Client client, Integer page, Integer sizePerPage) {
        page = ((page < 1) ? 0 : page - 1);
        return purchaseRepository.getAllByClient(new PageRequest(page, sizePerPage, new Sort(Sort.Direction.DESC, "purchaseDate")), client).getContent();
    }

    public Long countPurchases() {
        return purchaseRepository.count();
    }

    public List<Purchase> getPurchasesPaged(Integer page, Integer sizePerPage) {
        page = ((page < 1) ? 0 : page - 1);
        return purchaseRepository.findAll(new PageRequest(page, sizePerPage, new Sort(Sort.Direction.DESC, "purchaseDate"))).getContent();
    }

    @Transactional
    public Purchase createPurchase(Client client, Long productId, Integer count) throws ApiException {
        Product product = productRepository.findOne(productId);
        if (client == null) {
            throw new ApiException("Не указан пользователь покупуки!");
        }
        if (product == null) {
            throw new ApiException("Такой товар не существует!");
        }
        if (product.getStock() < count) {
            throw new ApiException("Указанное количество больше доступного!");
        }

        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setClient(client);
        purchase.setPrice(product.getPrice());
        purchase.setCount(count);
        purchase.setPurchaseDate(new Date());
        purchaseRepository.save(purchase);

        product.setStock(product.getStock() - count);
        productRepository.save(product);

        logger.info("Creating purchases for Client {} with productId {} and count {}", client.getLogin(), productId, count);
        return purchase;
    }
}

