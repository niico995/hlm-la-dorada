package com.mindhubgrupo2.hlmladorada.DTO;

import com.mindhubgrupo2.hlmladorada.models.Product;

import java.util.List;
import java.util.Set;

public class ProductDTO {

    private Long productoDTOID;

    private double finalPrice;

    private Integer promos;

    private String  name,details, brand, category, image;

    public ProductDTO() {}

    public ProductDTO(Product product){
        this.productoDTOID = product.getProductID();
        this.finalPrice = product.getFinalPrice();
        this.promos = product.getPromos();
        this.name = product.getName();
        this.details = product.getDetails();
        this.brand = product.getBrand();
        this.category = product.getCategory();
        this.image = product.getImage();
    }

    public String getImage() {
        return image;
    }

    public Long getProductoDTOID() {
        return productoDTOID;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public Integer getPromos() {
        return promos;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }
}
