package com.mindhubgrupo2.hlmladorada.DTO;

import com.mindhubgrupo2.hlmladorada.models.Sales;

import java.util.ArrayList;
import java.util.List;

public class SalesDTO {

    private Long id;

    private double finalAmount;

    private String paidMethod;

    private List<Double> taxes;


    public SalesDTO(Sales sale) {
        this.id = sale.getSalesID();
        this.finalAmount = sale.getFinalAmount();
        this.paidMethod = sale.getPaidMethod();
        this.taxes = sale.getTaxes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getPaidMethod() {
        return paidMethod;
    }

    public void setPaidMethod(String paidMethod) {
        this.paidMethod = paidMethod;
    }

    public List<Double> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Double> taxes) {
        this.taxes = taxes;
    }
}
