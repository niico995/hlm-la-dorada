package com.mindhubgrupo2.hlmladorada.DTO;

import com.mindhubgrupo2.hlmladorada.models.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CartDTO {

    private int cartID;
    private Set<CartDetailsDTO> cartDetails = new HashSet<>();

    public CartDTO(Cart cart) {
        this.cartID = cart.getCartID();

        this.cartDetails = cart.getCartDetails().stream().map(CartDetailsDTO::new).collect(Collectors.toSet());

    }

    public int getCartID() {
        return cartID;
    }

    public Set<CartDetailsDTO> getCartDetails() {
        return cartDetails;
    }
}
