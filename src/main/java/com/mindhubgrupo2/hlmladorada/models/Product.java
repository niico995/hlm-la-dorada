package com.mindhubgrupo2.hlmladorada.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    private int stock;

    private double cost, finalPrice;

    private double revenue;

    private String name,details, brand, category;

    //@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @ElementCollection
    @Column(name="promos")
    private Set<Integer> promos = new HashSet<>();

    @ManyToMany(mappedBy = "providerID")
    private Set<Provider> providerHolder = new HashSet<>();

    public Set<Provider> getProviderID() {
        return providerHolder;
    }

    public void setProviderID(Set<Provider> providerID) {
        this.providerHolder = providerID;
    }

    public void addProvider(Provider provider){
        provider.getProducts().add(this);
        providerHolder.add(provider);
    }


    @ManyToMany(mappedBy = "products")
    private Set<CartDetails> cartDetails = new HashSet<>();

    public void addCartDetail(CartDetails cartDetail){
        cartDetail.getProducts().add(this);
        this.cartDetails.add(cartDetail);
    }




    public Product() {
    }

    public Product(String name,int stock, double cost, double finalPrice, double revenue, String details, String brand, String category, Set<Integer> promos) {
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.finalPrice = finalPrice;
        this.revenue = revenue;
        this.details = details;
        this.brand = brand;
        this.category = category;
        this.promos = promos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Integer> getPromos() {
        return promos;
    }

    public void setPromos(Set<Integer> promos) {
        this.promos = promos;
    }

/*    public void addPromo(Promo promo){
        promo.setProduct(this);
        promos.add(promo);
    }*/

    public Set<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(Set<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", stock=" + stock +
                ", cost=" + cost +
                ", finalPrice=" + finalPrice +
                ", revenue=" + revenue +
                ", details='" + details + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", promos=" + promos +
                ", provider=" + providerHolder + '\'' +
                '}';
    }
}
