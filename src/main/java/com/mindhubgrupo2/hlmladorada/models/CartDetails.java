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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CartDetailsProduct")
    private Product productHolder;

    public CartDetails() {
    }

    public CartDetails(int quantity, double amount) {
        this.quantity = quantity;
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

    public Cart getCartHolder() {
        return cartHolder;
    }

    public void setCartHolder(Cart cartHolder) {
        this.cartHolder = cartHolder;
    }

    public Product getProductHolder() {
        return productHolder;
    }

    public void setProductHolder(Product productHolder) {
        this.productHolder = productHolder;
    }

    @Override
    public String toString() {
        return "CartDetails{" +
                "cartDetailsID=" + cartDetailsID +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
