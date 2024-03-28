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

    private String adress;

    private String phone;

    private String role = "USER";

    private String password;

    private Double balance = 0.00;

    @OneToOne(mappedBy = "clientOnlineHolder", fetch = FetchType.EAGER)
    private Cart cartHolder;

    public ClientOnline() {
    }

    public ClientOnline(String name, String lastName, String email, String password, String phone, String adress) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.adress = adress;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Cart getCartHolder() {
        return cartHolder;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCartHolder(Cart cartHolder) {
        this.cartHolder = cartHolder;
    }

    public void addCart(Cart cart) {
        cart.setClientOnlineHolder(this);
        this.setCartHolder(cart);
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
                ", cartHolder=" + cartHolder +
                ", adress=" + adress +
                ", phone=" + phone +
                '}';
    }
}
