package com.mindhubgrupo2.hlmladorada.DTO;

import com.mindhubgrupo2.hlmladorada.models.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class CartDTO {

    private int cartID;

    private ClientStore clientStoreHolder;

    private ClientOnline clientOnlineHolder;

    private Set<CartDetails> cartDetails = new HashSet<>();

    private Sales salesHolder;

    public CartDTO(Cart cart) {
        this.cartID = cart.getCartID();
        this.clientStoreHolder = cart.getClientStoreHolder();
        this.clientOnlineHolder = cart.getClientOnlineHolder();
        this.cartDetails = cart.getCartDetails();
        this.salesHolder = cart.getSalesHolder();
    }

    public int getCartID() {
        return cartID;
    }

    public ClientStore getClientStoreHolder() {
        return clientStoreHolder;
    }

    public ClientOnline getClientOnlineHolder() {
        return clientOnlineHolder;
    }

    public Set<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public Sales getSalesHolder() {
        return salesHolder;
    }
}
