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

    @ManyToMany
    @JoinTable(name = "cart_cartdetails",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cartdetails_id"))
    private Set<Cart> carts = new HashSet<>();

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
        this.quantity = cuantity;
        this.amount = amount;
    }

    public Long getCartDetailsID() {
        return cartDetailsID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
