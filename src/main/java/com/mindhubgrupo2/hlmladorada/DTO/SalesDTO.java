package com.mindhubgrupo2.hlmladorada.DTO;

import com.mindhubgrupo2.hlmladorada.models.Sales;

import java.util.ArrayList;
import java.util.List;

public class SalesDTO {

    private Long id;

    private double finalAmount;

    private List<String> paidMethod = new ArrayList<>();

    public SalesDTO(Sales sale) {
        this.id = sale.getSalesID();
        this.finalAmount = sale.getFinalAmount();
        this.paidMethod = sale.getPaidMethod();
    }

    public Long getId() {
        return id;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public List<String> getPaidMethod() {
        return paidMethod;
    }
}
