package com.mindhubgrupo2.hlmladorada.DTO;

import com.mindhubgrupo2.hlmladorada.models.Cart;
import com.mindhubgrupo2.hlmladorada.models.CartDetails;
import com.mindhubgrupo2.hlmladorada.models.Product;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class CartDetailsDTO {

    private Long cartDetailsID;

    private Integer quantity;

    private Double amount;

    public CartDetailsDTO(CartDetails cartDetails) {
        this.cartDetailsID = cartDetails.getCartDetailsID();
        this.quantity = cartDetails.getQuantity();
        this.amount = cartDetails.getAmount();
    }
    public Long getCartDetailsID() {
        return cartDetailsID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getAmount() {
        return amount;
    }

}
