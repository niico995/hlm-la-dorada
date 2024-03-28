package com.mindhubgrupo2.hlmladorada.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseID;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purcheseProviderHolder")
    private Provider providerHolder;


    private int quantity;

    private String details;

    private double unitCost, finalCost;

    private LocalDateTime purchaseDate;


    private Set<Product> products = new HashSet<>();


    public Purchase() {
    }

    public Purchase(int quantity, String details, double unitCost, double finalCost, LocalDateTime purchaseDate) {
        this.quantity = quantity;
        this.details = details;
        this.unitCost = unitCost;
        this.finalCost = finalCost;
        this.purchaseDate = purchaseDate;
    }

    public Provider getProviderHolder() {
        return providerHolder;
    }

    public void setProviderHolder(Provider providerHolder) {
        this.providerHolder = providerHolder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
