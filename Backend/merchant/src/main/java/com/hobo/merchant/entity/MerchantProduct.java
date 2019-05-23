package com.hobo.merchant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchantproduct")
public class MerchantProduct {
    @Id
    int indexx;
    int merchantId;
    int productId;
    int stock;
    float price;
    float productRating;
    int productsSold;

    public int getIndexx() {
        return indexx;
    }

    public void setIndexx(int indexx) {
        this.indexx = indexx;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getProductRating() {
        return productRating;
    }

    public void setProductRating(float productRating) {
        this.productRating = productRating;
    }

    public int getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(int productsSold) {
        this.productsSold = productsSold;
    }

    @Override
    public String toString() {
        return "MerchantProduct{" +
                "indexx=" + indexx +
                ", merchantId=" + merchantId +
                ", productId=" + productId +
                ", stock=" + stock +
                ", price=" + price +
                ", productRating=" + productRating +
                ", productsSold=" + productsSold +
                '}';
    }
}
