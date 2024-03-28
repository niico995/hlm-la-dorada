package com.mindhubgrupo2.hlmladorada.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetailsID;

    private int quantity;

    private double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CartID")
    private Cart cartHolder;

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

    public CartDetails(int quantity, double amount) {
        this.quantity = quantity;
        this.amount = amount;
    }

    public Long getCartDetailsID() {
        return cartDetailsID;
    }

    public int getCuantity() {
        return quantity;
    }

    public void setCuantity(int cuantity) {
        this.quantity = cuantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Cart getCartHolder() {
        return cartHolder;
    }

    public void setCartHolder(Cart cartHolder) {
        this.cartHolder = cartHolder;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CartDetails{" +
                "cartDetailsID=" + cartDetailsID +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", products=" + products +
                '}';
    }
}
