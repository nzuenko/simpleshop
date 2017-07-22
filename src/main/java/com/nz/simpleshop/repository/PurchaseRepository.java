package com.nz.simpleshop.repository;

import com.nz.simpleshop.model.Client;
import com.nz.simpleshop.model.Product;
import com.nz.simpleshop.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Long> {
    Page<Purchase> getAllByClient(Pageable page, Client client);

    @Modifying
    @Query("update Purchase p set p.product = null where p.product = ?1")
    void removeProductInfo(Product product);
}