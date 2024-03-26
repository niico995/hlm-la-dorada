package com.mindhubgrupo2.hlmladorada.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ClientOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientOnlineID;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private Double balance;

    @OneToMany(mappedBy = "clientOnline", fetch = FetchType.EAGER)
    private Set<Cart> carts = new HashSet<>();

    public ClientOnline() {
    }

    public ClientOnline(String name, String lastName, String email, String password, Double balance) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public Long getClientOnlineID() {
        return clientOnlineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCart(Set<Cart> carts) {
        this.carts = carts;
    }

    public void addCart(Cart cart) {
        cart.setClientOnline(this);
        carts.add(cart);
    }

    @Override
    public String toString() {
        return "ClientOnline{" +
                "clientOnlineID=" + clientOnlineID +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", carts=" + carts +
                '}';
    }
}
