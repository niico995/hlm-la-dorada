package com.mindhubgrupo2.hlmladorada.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartID;

    private Set<ClientStore> clientStore = new HashSet<>();


    private Set<ClientOnline> clientOnline = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartDetailsID")
    private Set<CartDetails> cartDetails = new HashSet<>();

    public void addCartDetails(CartDetails cartDetail){
        cartDetail.getCart().add(this);
        cartDetails.add(cartDetail);
    }


    public Cart() {
    }

    /*public Cart(Set<ClientStore> clientStore, Set<ClientOnline> clientOnline, Set<CartDetails> cartDetails) {
        this.clientStore = clientStore;
        this.clientOnline = clientOnline;
        this.cartDetails = cartDetails;
    }*/

    public Cart(Set<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public int getCartID() {
        return cartID;
    }

    public Set<ClientStore> getClientStore() {
        return clientStore;
    }

    public void setClientStore(Set<ClientStore> clientStore) {
        this.clientStore = clientStore;
    }

    public Set<ClientOnline> getClientOnline() {
        return clientOnline;
    }

    public void setClientOnline(Set<ClientOnline> clientOnline) {
        this.clientOnline = clientOnline;
    }

    public Set<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(Set<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }


}
