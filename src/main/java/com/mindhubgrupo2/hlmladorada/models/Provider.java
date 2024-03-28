package com.mindhubgrupo2.hlmladorada.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerID;

    private String phone, email, adress, cuit;


    @OneToMany(mappedBy = "providerHolder")
    private Set<Purchase> purchases = new HashSet<>();


    public void addPurchase(Purchase purchase){
        purchase.setProviderHolder(this);
        purchases.add(purchase);
    }


    @ManyToMany
    @JoinTable(name = "providerProducts",
            joinColumns = @JoinColumn(name = "providerProductID"),
            inverseJoinColumns = @JoinColumn(name = "productsOfProviders"))
    private Set<Product> products = new HashSet<>();

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providerID=" + providerID +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", cuit='" + cuit + '\'' +
                ", purchases=" + purchases +
                '}';
    }
}
