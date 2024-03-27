package com.mindhubgrupo2.hlmladorada.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ClientStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClientStoreID;

    private String name;

    private String lastName;

    private String phone;

    private String rut;

    private Double balance;

    @OneToOne(mappedBy = "ClientStoreHolder", fetch = FetchType.EAGER)
    private ClientDoubuts doubut;

    @OneToMany(mappedBy = "clientStore", fetch = FetchType.EAGER)
    private Set<Cart> carts = new HashSet<>();

    public ClientStore() {
    }

    public ClientStore(String name, String lastName, String phone, String rut, Double balance) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.rut = rut;
        this.balance = balance;
    }

    public Long getClientStoreID() {
        return ClientStoreID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ClientDoubuts getDoubut() {
        return doubut;
    }

    public void setDoubut(ClientDoubuts doubut) {
        this.doubut = doubut;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public void addCart(Cart cart) {
        cart.setClientStore(this);
        carts.add(cart);
    }

    @Override
    public String toString() {
        return "ClientStore{" +
                "ClientStoreID=" + ClientStoreID +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", rut='" + rut + '\'' +
                ", balance=" + balance +
                ", doubuts=" + doubut +
                ", carts=" + carts +
                '}';
    }
}
