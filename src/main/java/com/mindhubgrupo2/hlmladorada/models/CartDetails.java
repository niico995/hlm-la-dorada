package com.mindhubgrupo2.hlmladorada.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetailsID;

    private int cuantity;

    private double amount;

    @ManyToMany
    @JoinTable(name = "cart_cartdetails",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cartdetails_id"))
    private Set<Cart> carts = new HashSet<>();

    @Override
    public String toString() {
        return "CartDetails{" +
                "cuantity=" + cuantity +
                ", amount=" + amount +
                ", products=" + products +
                '}';
    }

    @ManyToMany
    @JoinTable(name = "cart_details_product",
            joinColumns = @JoinColumn(name = "cart_details_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public void addProducts(Product product){
        product.getCartDetails().add(this);
        products.add(product);
    }

    public CartDetails() {
    }

    public CartDetails(int cuantity, double amount) {
        this.cuantity = cuantity;
        this.amount = amount;
    }

    public Long getCartDetailsID() {
        return cartDetailsID;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Set<Cart> getCart() {
        return carts;
    }

    public void setCart(Set<Cart> cart) {
        this.carts = cart;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
