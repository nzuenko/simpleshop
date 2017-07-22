package com.nz.simpleshop.repository;

import com.nz.simpleshop.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}