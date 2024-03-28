package com.mindhubgrupo2.hlmladorada.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesID;

    private String details;

    private double finalAmount;

    private List<String> paidMethod = new ArrayList<>();

    private List<Double> taxes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employeeHolderID")
    private Employee employeeHolder;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartSale")
    private Cart cartHolder;

    public Sales() {
    }

    public Sales(String details, double finalAmount, List<String> paidMethod, List<Double> taxes) {
        this.details = details;
        this.finalAmount = finalAmount;
        this.paidMethod = paidMethod;
        this.taxes = taxes;
    }

    public Long getSalesID() {
        return salesID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public List<String> getPaidMethod() {
        return paidMethod;
    }

    public void setPaidMethod(List<String> paidMethod) {
        this.paidMethod = paidMethod;
    }

    public List<Double> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Double> taxes) {
        this.taxes = taxes;
    }

    public Employee getEmployeeHolder() {
        return employeeHolder;
    }

    public void setEmployeeHolder(Employee employeeHolder) {
        this.employeeHolder = employeeHolder;
    }

    public Cart getCartHolder() {
        return cartHolder;
    }

    public void setCartHolder(Cart cart) {
        this.cartHolder = cart;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "salesID=" + salesID +
                ", details='" + details + '\'' +
                ", finalAmount=" + finalAmount +
                ", paidMethod=" + paidMethod +
                ", taxes=" + taxes +
                ", cartHolder=" + cartHolder +
                '}';
    }
}
