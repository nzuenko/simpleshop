package com.nz.simpleshop.domain;

import com.nz.simpleshop.model.Client;
import com.nz.simpleshop.model.Purchase;

import java.util.Date;

public class PurchaseDTO {

    private Long id;

    private Client client;

    private ProductDTO product;

    private Date purchaseDate;

    private Double price;

    private Integer count;

    public PurchaseDTO() {
    }

    public PurchaseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.client = purchase.getClient();
        this.purchaseDate = purchase.getPurchaseDate();
        this.product = purchase.getProduct() == null ? null : new ProductDTO(purchase.getProduct());
        this.price = purchase.getPrice();
        this.count = purchase.getCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
